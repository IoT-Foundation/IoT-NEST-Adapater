package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devices")
public class SupportedDevice {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer dbId;
    @Column(name = "deviceType")
    private String deviceType;
    @Column(name = "misc")
    private String misc;
    @Column(name = "hrefImageUrl")
    private String hrefImageUrl;

    public String getDeviceType() {
	return deviceType;
    }

    public void setDeviceType(String deviceType) {
	this.deviceType = deviceType;
    }

    public String getMisc() {
	return misc;
    }

    public void setMisc(String misc) {
	this.misc = misc;
    }

    public Integer getDbId() {
	return dbId;
    }

    public String getHrefImageUrl() {
	return hrefImageUrl;
    }

    public void setHrefImageUrl(String hrefImageUrl) {
	this.hrefImageUrl = hrefImageUrl;
    }

}
