package com.retellmobile.iot.rest.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.retellmobile.iot.rest.model.Action;
import com.retellmobile.iot.rest.model.SupportedDevice;
import com.retellmobile.iot.rest.model.TokenMapper;
import com.retellmobile.iot.rest.model.UserDevice;
import com.retellmobile.iot.rest.services.DeviceService;
import com.retellmobile.iot.rest.services.EndpointInfoService;
import com.retellmobile.iot.rest.services.UserService;

@Controller
public class APIIoTController {

    @Autowired
    protected MappingJacksonJsonView jsonView;
    @Autowired
    protected EndpointInfoService endpointSrv;
    @Autowired
    protected UserService userSrv;
    @Autowired
    protected DeviceService deviceSrv;

    private static final String VIEW_INDEX = "index";
    private final static org.slf4j.Logger logger = LoggerFactory
	    .getLogger(APIIoTController.class);
    private static final String RESULT_FIELD = "result";
    private static final String STATUS_FIELD = "status";
    private static final String MSG_FIELD = "message";

    @RequestMapping(value = "/entrypoint", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getEntryPointInfo(
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	Hashtable<String, String> endpointInfo = new Hashtable<String, String>();
	try {
	    endpointInfo = this.endpointSrv.getEndpointInfo();
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, endpointInfo);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;

    }

    @RequestMapping(value = "/authorize/{code}", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getUserInformation(
	    @PathVariable("code") String code,
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	TokenMapper tm = null;
	try {
	    tm = this.userSrv.getTokenMapperByTempToken(code);
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, tm);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;

    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getSupportedDevices(
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	List<SupportedDevice> devices = new ArrayList<SupportedDevice>();
	try {
	    devices = this.deviceSrv.getSupportedDevices();
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, devices);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;

    }

    @RequestMapping(value = "/devices/{device_id}/actions", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getActionsAvailableForDevice(
	    @PathVariable("device_id") int deviceId,
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	List<Action> actions = new ArrayList<Action>();
	try {
	    actions = this.deviceSrv.getActionsForDevice(deviceId);
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, actions);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;

    }

    // TODO execute the request based on the body.
    @RequestMapping(value = "/devices/{device_id}/actions/{action_id}/test", method = RequestMethod.POST)
    public @ResponseBody ModelAndView validateActions(
	    @PathVariable("device_id") int deviceId,
	    @PathVariable("action_id") int actionId,
	    @RequestBody JSONObject requestBody, HttpServletRequest request,
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	List<Action> actions = new ArrayList<Action>();
	try {
	    // TODO validate that the body of the request is correct
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, actions);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;
    }

    // TODO execute the request based on the body. Do we need this call to be
    // async? We can have a call back url or rest method
    // @return returns instance information
    @RequestMapping(value = "/devices/{device_id}/actions/{action_id}", method = RequestMethod.POST)
    public @ResponseBody ModelAndView executeActions(
	    @PathVariable("device_id") int deviceId,
	    @PathVariable("action_id") int actionId,
	    @RequestBody JSONObject requestBody, HttpServletRequest request,
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	List<Action> actions = new ArrayList<Action>();
	try {
	    // TODO call validate, and then execute the request
	    // Is this call async?
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, actions);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;
    }

    // TODO returns instance information
    @RequestMapping(value = "/devices/{device_id}/actions/{action_id}/instance/{instance_id}", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getInformationOnActionInstance(
	    @PathVariable("device_id") int deviceId,
	    @PathVariable("action_id") int actionId,
	    @PathVariable("instance_id") int instanceId,
	    @RequestBody JSONObject requestBody, HttpServletRequest request,
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	List<Action> actions = new ArrayList<Action>();
	try {
	    // TODO return information regarding specific instance of action
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, actions);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;
    }

    @RequestMapping(value = "/users/{token}/devices", method = RequestMethod.GET)
    public @ResponseBody ModelAndView getUserDevices(
	    @PathVariable("token") String token,
	    HttpServletResponse httpResponse_p, WebRequest request_p) {
	boolean status = false;
	String msg = null;
	List<UserDevice> actions = new ArrayList<UserDevice>();
	try {
	    actions = this.deviceSrv.getUserDevices(token);
	    status = true;
	} catch (Exception ex) {
	    msg = ex.getLocalizedMessage();
	}

	ModelAndView mav = new ModelAndView();
	mav.setView(jsonView);
	mav.addObject(RESULT_FIELD, actions);
	mav.addObject(STATUS_FIELD, status);
	mav.addObject(MSG_FIELD, msg);
	return mav;

    }

}