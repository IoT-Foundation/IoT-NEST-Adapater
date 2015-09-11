package com.retellmobile.iot.rest.resp.model;

import org.json.JSONObject;

import com.retellmobile.iot.rest.util.Keys;

public class SmokeAlarmData extends DeviceData {

    public String battery_health;
    public String co_alarm_state;
    public String smoke_alarm_state;

    public SmokeAlarmData(JSONObject jObj) {
	super(jObj);
	try {
	    this.battery_health = jObj
		    .getString(Keys.SMOKE_CO_ALARM.BATTERY_HEALTH);
	    this.co_alarm_state = jObj
		    .getString(Keys.SMOKE_CO_ALARM.CO_ALARM_STATE);
	    this.smoke_alarm_state = jObj
		    .getString(Keys.SMOKE_CO_ALARM.SMOKE_ALARM_STATE);
	} catch (Throwable th) {
	    throw new IllegalStateException();
	}
    }

    public String getBattery_health() {
	return battery_health;
    }

    public void setBattery_health(String battery_health) {
	this.battery_health = battery_health;
    }

    public String getCo_alarm_state() {
	return co_alarm_state;
    }

    public void setCo_alarm_state(String co_alarm_state) {
	this.co_alarm_state = co_alarm_state;
    }

    public String getSmoke_alarm_state() {
	return smoke_alarm_state;
    }

    public void setSmoke_alarm_state(String smoke_alarm_state) {
	this.smoke_alarm_state = smoke_alarm_state;
    }

}
