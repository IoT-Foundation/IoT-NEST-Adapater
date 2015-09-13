package com.retellmobile.iot.rest.resp.model;

import org.json.JSONObject;

import com.retellmobile.iot.rest.util.Keys;

public class DeviceData {

    public String device_id;
    public String locale;
    public String software_version;
    public String structure_id;
    public String name;
    public String name_long;
    public String last_connection;
    public boolean is_online;
    public int device_type;

    public DeviceData(JSONObject jObj) {
	try {
	    this.device_id = jObj.getString(Keys.DEVICE_ID);
	    this.locale = jObj.getString(Keys.LOCALE);
	    this.software_version = jObj.getString(Keys.SOFTWARE_VERSION);
	    this.structure_id = jObj.getString(Keys.STRUCTURE_ID);
	    this.name = jObj.getString(Keys.NAME);
	    this.name_long = jObj.getString(Keys.NAME_LONG);
	    if (jObj.has(Keys.LAST_CONNECTION)) {
		this.last_connection = jObj.getString(Keys.LAST_CONNECTION);
	    }
	    this.is_online = jObj.getBoolean(Keys.IS_ONLINE);
	} catch (Throwable th) {
	    throw new IllegalStateException();
	}
    }

    public String getDevice_id() {
	return device_id;
    }

    public void setDevice_id(String device_id) {
	this.device_id = device_id;
    }

    public String getLocale() {
	return locale;
    }

    public void setLocale(String locale) {
	this.locale = locale;
    }

    public String getSoftware_version() {
	return software_version;
    }

    public void setSoftware_version(String software_version) {
	this.software_version = software_version;
    }

    public String getStructure_id() {
	return structure_id;
    }

    public void setStructure_id(String structure_id) {
	this.structure_id = structure_id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getName_long() {
	return name_long;
    }

    public void setName_long(String name_long) {
	this.name_long = name_long;
    }

    public String getLast_connection() {
	return last_connection;
    }

    public void setLast_connection(String last_connection) {
	this.last_connection = last_connection;
    }

    public boolean isIs_online() {
	return is_online;
    }

    public void setIs_online(boolean is_online) {
	this.is_online = is_online;
    }

    public int getDevice_type() {
	return device_type;
    }

    public void setDevice_type(int deviceType) {
	this.device_type = deviceType;
    }

}
