package com.retellmobile.iot.web.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.retellmobile.iot.rest.model.SessionTokenMapper;
import com.retellmobile.iot.rest.model.TokenMapper;
import com.retellmobile.iot.rest.model.User;
import com.retellmobile.iot.rest.services.DeviceService;
import com.retellmobile.iot.rest.services.EndpointInfoService;
import com.retellmobile.iot.rest.services.UserService;
import com.retellmobile.iot.rest.util.NestClient;
import com.retellmobile.iot.rest.util.NestClient.UrlType;

@Controller
public class WebIoTController {

    @Autowired
    protected MappingJacksonJsonView jsonView;
    @Autowired
    protected EndpointInfoService endpointSrv;
    @Autowired
    protected UserService userSrv;
    @Autowired
    protected DeviceService deviceSrv;
    @Autowired
    private RestTemplate restTemplate;

    ExecutorService eSrv = Executors.newFixedThreadPool(10);

    private static final String VIEW_INDEX = "index";
    private final static org.slf4j.Logger logger = LoggerFactory
	    .getLogger(WebIoTController.class);

    private static final String AUTH_URL = "https://home.nest.com/login/oauth2?client_id=6ab83612-6372-48f8-8736-c24b4587d46a&state=";
    private static final String ACCESS_TOKEN_URL = "https://api.home.nest.com/oauth2/access_token?client_id=6ab83612-6372-48f8-8736-c24b4587d46a&code={0}&client_secret=ovvJhfiPOyGn8eprslRdvaXEh&grant_type=authorization_code";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(ModelMap model) {

	model.addAttribute("message",
		"Welcome to IoT.  Please select one of the active service");
	logger.debug("[/] call");

	// Spring uses InternalResourceViewResolver and return back index.jsp
	return VIEW_INDEX;

    }

    // Incoming call from the IoT fabric
    @RequestMapping(value = "/{name}/login", method = RequestMethod.GET)
    public String welcomeName(
	    @PathVariable String name,
	    @RequestParam(value = "client_id", required = true) String clientId,
	    @RequestParam(value = "state", required = true) String state,
	    @RequestParam(value = "redirect_uri", required = true) String returnURL,
	    ModelMap model) {
	String loginPage = "login";
	if ("NEST".equalsIgnoreCase(name.trim())) {
	    // TODO validate the caller
	    // save info from caller
	    try {
		SessionTokenMapper stm = new SessionTokenMapper();
		stm.setClientId(clientId);
		stm.setReturnURL(returnURL);
		stm.setState(state);
		this.userSrv.upsertSessionData(stm);
		model.addAttribute("name", name);
		model.addAttribute("OAuthURL", AUTH_URL + clientId);
		model.addAttribute("message", name + " Login");
	    } catch (Exception ex) {
		if (ex instanceof com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException) {
		    return "Session has already been used.";
		}
	    }
	}
	logger.debug("[/name] for : {}", name);
	return loginPage;

    }

    // callback from NEST.
    @RequestMapping(value = "/callback/{name}", method = RequestMethod.GET)
    public String callbackName(@PathVariable String name, ModelMap model,
	    @RequestParam(value = "state", required = false) String clientId,
	    @RequestParam(value = "code", required = false) String code,
	    HttpServletResponse httpServletResponse, WebRequest request_p) {
	String returnURL = "http://www.retellhome.com";
	try {
	    if ("NEST".equalsIgnoreCase(name.trim())) {
		String accessToken;
		accessToken = getAuthToken(code);

		SessionTokenMapper initiatorInfo = this.userSrv
			.getInitiatorSessionTokens(clientId);

		// upsert new user
		String token = UUID.randomUUID().toString();
		String tempToken = UUID.randomUUID().toString();
		User user = new User();
		user.setToken(token);
		user.setNestAuthToken(accessToken);
		user.setUpdatedTimestamp(new Date());
		this.userSrv.addUser(user);

		// create temp token mapper to hold the temp code for rest api
		TokenMapper tm = new TokenMapper();
		tm.setTempToken(tempToken);
		tm.setToken(token);
		this.userSrv.addTokenMapper(tm);

		// set user's devices
		eSrv.submit(new NestClient(UrlType.ALL_DEVICES, accessToken,
			token, null, this.deviceSrv, null));

		// redirect the caller
		StringBuffer sb = new StringBuffer(initiatorInfo.getReturnURL());
		sb.append("?state=" + initiatorInfo.getState());
		sb.append("&client_id=" + initiatorInfo.getSessionId());
		sb.append("&code=" + tempToken);

		return "redirect:" + sb.toString();
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	logger.debug("[/name] for : {}", name);
	return "redirect: " + returnURL;

    }

    protected String getAuthToken(String code) throws Exception {
	String authToken = null;
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> entity = new HttpEntity<String>(headers);
	String url = MessageFormat.format(ACCESS_TOKEN_URL, code);
	ResponseEntity<?> response = restTemplate.exchange(url,
		HttpMethod.POST, entity, Object.class);
	if (response != null && response.getBody() != null) {
	    @SuppressWarnings("unchecked")
	    LinkedHashMap<String, ?> data = (LinkedHashMap<String, ?>) response
	    .getBody();
	    authToken = String.valueOf(data.get("access_token"));
	    String expiresIn = String.valueOf(data.get("expires_in"));
	    System.out.println("Expires in:  " + expiresIn);
	}
	return authToken;
    }
}