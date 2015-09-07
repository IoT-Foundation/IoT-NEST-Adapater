package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userDevices")
public class UserDevice {

    public enum DeviceType {
	thermostats, smoke_co_alarms
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer dbId;
    @Column(name = "deviceId")
    private String deviceId;
    @Column(name = "userDeviceId")
    private DeviceType deviceType;
    @Column(name = "deviceName")
    private String deviceShortName;
    @Column(name = "structureId")
    private String structureId;
    @Column(name = "deviceLongName")
    private String deviceLongName;
    @Column(name = "token")
    private String token;
    @Column(name = "structureName")
    private String structName;
    @Column(name = "postalCode")
    private String zipCode;

    public String getDeviceId() {
	return deviceId;
    }

    public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
    }

    @Enumerated(EnumType.ORDINAL)
    public DeviceType getUserDeviceType() {
	return deviceType;
    }

    @Enumerated(EnumType.ORDINAL)
    public void setUserDeviceType(DeviceType deviceType) {
	this.deviceType = deviceType;
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

    public String getStructName() {
	return structName;
    }

    public void setStructName(String structName) {
	this.structName = structName;
    }

    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }
}
