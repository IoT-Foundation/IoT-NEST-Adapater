package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userDevices")
public class UserDevice {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer dbId;
    @Column(name = "deviceId")
    private Integer deviceId;
    @Column(name = "userDeviceId")
    private String userDeviceIdentifier;
    @Column(name = "deviceName")
    private String deviceShortName;
    @Column(name = "structureId")
    private String structureId;
    @Column(name = "deviceLongName")
    private String deviceLongName;
    @Column(name = "token")
    private String token;

    public Integer getDeviceId() {
	return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
	this.deviceId = deviceId;
    }

    public String getUserDeviceIdentifier() {
	return userDeviceIdentifier;
    }

    public void setUserDeviceIdentifier(String userDeviceIdentifier) {
	this.userDeviceIdentifier = userDeviceIdentifier;
    }

    public String getDeviceShortName() {
	return deviceShortName;
    }

    public void setDeviceShortName(String deviceShortName) {
	this.deviceShortName = deviceShortName;
    }

    public String getStructureId() {
	return structureId;
    }

    public void setStructureId(String structureId) {
	this.structureId = structureId;
    }

    public String getDeviceLongName() {
	return deviceLongName;
    }

    public void setDeviceLongName(String deviceLongName) {
	this.deviceLongName = deviceLongName;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public Integer getDbId() {
	return dbId;
    }
}
