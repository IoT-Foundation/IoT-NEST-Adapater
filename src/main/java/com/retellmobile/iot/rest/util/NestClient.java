package com.retellmobile.iot.rest.util;

import java.util.Iterator;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.retellmobile.iot.rest.model.UserDevice;
import com.retellmobile.iot.rest.model.UserDevice.DeviceType;
import com.retellmobile.iot.rest.services.DeviceService;

public class NestClient implements Callable<String> {

    public enum UrlType {
	ALL_DEVICES
    }

    private final String NEST_BASE_URL = "https://developer-api.nest.com";
    private String accessToken;
    private String token;
    private DeviceService deviceSrv;
    private UrlType urlType;

    // Use this to create a JavaMailClient (or essentially one email). Not
    // memory efficient though...
    public NestClient(UrlType urlType, String accessToken, String token,
	    DeviceService deviceSrv) {
	this.deviceSrv = deviceSrv;
	this.accessToken = accessToken;
	this.token = token;
	this.urlType = urlType;
    }

    @Override
    public String call() throws Exception {
	try {
	    RestTemplate restTemplate = new RestTemplate();
	    String nResp = restTemplate.getForObject(this.getURLForCall(),
		    String.class);
	    processReturnObj(new JSONObject(nResp));
	    return nResp.toString();
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
		storeDeviceInfo(deviceId, DeviceType.smoke_co_alarms,
			structInfo, data);
	    }
	}
	if (thermostats != null) {
	    for (int i = 0; i < thermostats.length(); ++i) {
		String deviceId = thermostats.getString(i);
		storeDeviceInfo(deviceId, DeviceType.thermostats, structInfo,
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
	JSONObject userDevices = devices.getJSONObject(deviceType.name());
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
	    break;
	default:
	    break;
	}
	return url;
    }
}