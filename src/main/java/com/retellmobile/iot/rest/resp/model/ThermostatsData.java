package com.retellmobile.iot.rest.resp.model;

import org.json.JSONObject;

import com.retellmobile.iot.rest.model.SupportedDevice;
import com.retellmobile.iot.rest.util.Keys;

public class ThermostatsData extends DeviceData {

    public boolean can_cool;
    public boolean can_heat;
    public boolean is_using_emergency_heat;
    public boolean has_fan;
    public boolean fan_timer_active;
    public String hvac_state;
    public String hvac_mode;
    public String away;
    public double target_temperature_f;
    public double target_temperature_high_f;
    public double target_temperature_low_f;
    public double away_temperature_high_f;
    public double away_temperature_low_f;

    public ThermostatsData(SupportedDevice sInfo, JSONObject jObj) {
	super(jObj);
	try {
	    this.hrefImageUrl = sInfo.getHrefImageUrl();
	    this.can_cool = jObj.getBoolean(Keys.THERMOSTAT.CAN_COOL);
	    this.can_heat = jObj.getBoolean(Keys.THERMOSTAT.CAN_HEAT);
	    this.is_using_emergency_heat = jObj
		    .getBoolean(Keys.THERMOSTAT.IS_USING_EMERGENCY_HEAT);
	    this.has_fan = jObj.getBoolean(Keys.THERMOSTAT.HAS_FAN);
	    if (jObj.has(Keys.THERMOSTAT.FAN_TIMER_ACTIVE)) {
		this.fan_timer_active = jObj
			.getBoolean(Keys.THERMOSTAT.FAN_TIMER_ACTIVE);
	    }
	    if (jObj.has(Keys.THERMOSTAT.HVAC_STATE)) {
		this.hvac_state = jObj.getString(Keys.THERMOSTAT.HVAC_STATE);
	    }
	    this.hvac_mode = jObj.getString(Keys.THERMOSTAT.HVAC_MODE);
	    if (jObj.has(Keys.STRUCTURE.AWAY)) {
		this.away = jObj.getString(Keys.STRUCTURE.AWAY);
	    }
	    this.target_temperature_f = jObj
		    .getDouble(Keys.THERMOSTAT.TARGET_TEMP_F);
	    this.target_temperature_high_f = jObj
		    .getDouble(Keys.THERMOSTAT.TARGET_TEMP_HIGH_F);
	    this.target_temperature_low_f = jObj
		    .getDouble(Keys.THERMOSTAT.TARGET_TEMP_LOW_F);
	    this.away_temperature_high_f = jObj
		    .getDouble(Keys.THERMOSTAT.AWAY_TEMP_HIGH_F);
	    this.away_temperature_low_f = jObj
		    .getDouble(Keys.THERMOSTAT.AWAY_TEMP_LOW_F);
	} catch (Throwable th) {
	    throw new IllegalStateException();
	}
    }

    public boolean isCan_cool() {
	return can_cool;
    }

    public void setCan_cool(boolean can_cool) {
	this.can_cool = can_cool;
    }

    public boolean isCan_heat() {
	return can_heat;
    }

    public void setCan_heat(boolean can_heat) {
	this.can_heat = can_heat;
    }

    public boolean isIs_using_emergency_heat() {
	return is_using_emergency_heat;
    }

    public void setIs_using_emergency_heat(boolean is_using_emergency_heat) {
	this.is_using_emergency_heat = is_using_emergency_heat;
    }

    public boolean isHas_fan() {
	return has_fan;
    }

    public void setHas_fan(boolean has_fan) {
	this.has_fan = has_fan;
    }

    public boolean isFan_timer_active() {
	return fan_timer_active;
    }

    public void setFan_timer_active(boolean fan_timer_active) {
	this.fan_timer_active = fan_timer_active;
    }

    public String getHvac_state() {
	return hvac_state;
    }

    public void setHvac_state(String hvac_state) {
	this.hvac_state = hvac_state;
    }

    public String getHvac_mode() {
	return hvac_mode;
    }

    public void setHvac_mode(String hvac_mode) {
	this.hvac_mode = hvac_mode;
    }

    public double getTarget_temperature_f() {
	return target_temperature_f;
    }

    public void setTarget_temperature_c(double target_temperature_f) {
	this.target_temperature_f = target_temperature_f;
    }

    public double getTarget_temperature_high_f() {
	return target_temperature_high_f;
    }

    public void setTarget_temperature_high_f(double target_temperature_high_f) {
	this.target_temperature_high_f = target_temperature_high_f;
    }

    public double getTarget_temperature_low_f() {
	return target_temperature_low_f;
    }

    public void setTarget_temperature_low_f(double target_temperature_low_f) {
	this.target_temperature_low_f = target_temperature_low_f;
    }

    public double getAway_temperature_high_f() {
	return away_temperature_high_f;
    }

    public void setAway_temperature_high_f(double away_temperature_high_f) {
	this.away_temperature_high_f = away_temperature_high_f;
    }

    public double getAway_temperature_low_f() {
	return away_temperature_low_f;
    }

    public void setAway_temperature_low_f(double away_temperature_low_f) {
	this.away_temperature_low_f = away_temperature_low_f;
    }
}
