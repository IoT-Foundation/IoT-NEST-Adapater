package com.retellmobile.iot.rest.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.retellmobile.iot.rest.model.UserDevice;
import com.retellmobile.iot.rest.model.UserDevice.DeviceType;
import com.retellmobile.iot.rest.services.DeviceService;

public class NestClient implements Callable<JSONObject> {

    public enum UrlType {
	ALL_DEVICES, MY_DEVICE, INFO_UPDATE;
    }

    public enum RequestType {
	GET, PUT;
    }

    private final String NEST_BASE_URL = "https://developer-api.nest.com";
    private String accessToken;
    private String token;
    private DeviceService deviceSrv;
    private UrlType urlType;
    private RequestType reqType;
    private String appendURL;
    private JSONObject body;

    // Use this to create a JavaMailClient (or essentially one email). Not
    // memory efficient though...
    public NestClient(UrlType urlType, String accessToken, String token,
	    String appendURL, DeviceService deviceSrv, JSONObject body) {
	this.deviceSrv = deviceSrv;
	this.accessToken = accessToken;
	this.token = token;
	this.urlType = urlType;
	this.appendURL = appendURL;
	this.body = body;
    }

    @Override
    public JSONObject call() throws Exception {
	try {
	    JSONObject result = new JSONObject();
	    SimpleClientHttpRequestFactory simpleFac = new SimpleClientHttpRequestFactory() {
		@Override
		protected void prepareConnection(HttpURLConnection connection,
			String httpMethod) throws IOException {
		    super.prepareConnection(connection, httpMethod);
		    connection.setInstanceFollowRedirects(true);
		}
	    };

	    RestTemplate restTemplate = new RestTemplate(simpleFac);

	    String nResp;
	    // dummy call to get request type
	    this.getURLForCall();
	    if (this.reqType.equals(RequestType.GET)) {
		nResp = restTemplate.getForObject(this.getURLForCall(),
			String.class);
		processReturnObj(new JSONObject(nResp));
		if (nResp != null) {
		    result = new JSONObject(nResp);
		}
	    } else {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(
			this.body.toString(), headers);

		String targetURL = this.getURLForCall();
		ResponseEntity<String> data = restTemplate.exchange(targetURL,
			HttpMethod.PUT, entity, String.class);
		System.out.println("Response: " + data);
		if (data.getStatusCode().value() >= 300
			&& data.getStatusCode().value() < 400) {
		    HttpHeaders respHeaders = data.getHeaders();
		    URI redirectUrl = respHeaders.getLocation();
		    data = restTemplate.exchange(redirectUrl, HttpMethod.PUT,
			    entity, String.class);
		    System.out.println("Response: " + data);
		}

		result = new JSONObject(
			"{\"message\":\"successfully updated the endpoint.\"}");
		JSONArray rData = new JSONArray();
		rData.put(data.getBody());
		result.put("result", rData);
	    }
	    return result;
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }

    private void processReturnObj(JSONObject nResp) throws JSONException {
	switch (this.urlType) {
	case ALL_DEVICES:
	    storeDevices(nResp);
	    break;
	case MY_DEVICE:
	    // Do nothing for now
	    break;
	case INFO_UPDATE:
	    break;
	default:
	    break;
	}
    }

    private void storeDevices(JSONObject nResp) throws JSONException {
	// TODO Auto-generated method stub
	JSONObject structs = nResp.getJSONObject(Keys.STRUCTURES);
	@SuppressWarnings("unchecked")
	Iterator<String> iterator = structs.keys();
	while (iterator.hasNext()) {
	    String structId = iterator.next();
	    JSONObject structInfo = (JSONObject) structs.get(structId);
	    processStructInfo(structInfo, nResp);
	}
	System.out.println(nResp.toString());
    }

    private void processStructInfo(JSONObject structInfo, JSONObject data)
	    throws JSONException {
	JSONArray smokeAlarms = structInfo.getJSONArray(Keys.SMOKE_CO_ALARMS);
	JSONArray thermostats = structInfo.getJSONArray(Keys.THERMOSTATS);
	if (smokeAlarms != null) {
	    for (int i = 0; i < smokeAlarms.length(); ++i) {
		String deviceId = smokeAlarms.getString(i);
		storeDeviceInfo(deviceId, DeviceType.SMOKE_CO_ALARMS,
			structInfo, data);
	    }
	}
	if (thermostats != null) {
	    for (int i = 0; i < thermostats.length(); ++i) {
		String deviceId = thermostats.getString(i);
		storeDeviceInfo(deviceId, DeviceType.THERMOSTATS, structInfo,
			data);
	    }
	}
    }

    private void storeDeviceInfo(String deviceId,
	    UserDevice.DeviceType deviceType, JSONObject structInfo,
	    JSONObject data) throws JSONException {
	UserDevice userDevice = new UserDevice();
	userDevice.setDeviceId(deviceId);
	userDevice.setUserDeviceType(deviceType);
	userDevice.setStructureId(structInfo.getString(Keys.STRUCTURE_ID));
	userDevice.setStructName(structInfo.getString(Keys.STRUCTURE.NAME));
	userDevice.setToken(this.token);
	userDevice.setZipCode(structInfo.getString(Keys.STRUCTURE.POSTAL_CODE));
	JSONObject devices = data.getJSONObject(Keys.DEVICES);
	JSONObject userDevices = new JSONObject();
	if (devices.has(deviceType.name())) {
	    userDevices = devices.getJSONObject(deviceType.name());
	} else {
	    userDevices = devices
		    .getJSONObject(deviceType.name().toLowerCase());
	}
	JSONObject device = userDevices.getJSONObject(deviceId);
	userDevice.setDeviceShortName(device.getString(Keys.NAME));
	userDevice.setDeviceLongName(device.getString(Keys.NAME_LONG));
	this.deviceSrv.addUserDevice(userDevice);
    }

    private String getURLForCall() {
	String url = NEST_BASE_URL;
	switch (this.urlType) {
	case ALL_DEVICES:
	    url = NEST_BASE_URL + "?auth=" + this.accessToken;
	    reqType = RequestType.GET;
	    break;
	case MY_DEVICE:
	    url = NEST_BASE_URL + this.appendURL + "?auth=" + this.accessToken;
	    reqType = RequestType.GET;
	    break;
	case INFO_UPDATE:
	    url = NEST_BASE_URL + this.appendURL + "?auth=" + this.accessToken;
	    reqType = RequestType.PUT;
	    break;
	default:
	    break;
	}
	return url;
    }

}