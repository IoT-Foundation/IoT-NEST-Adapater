package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "actions")
public class Action {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer dbId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String desc;
    @Column(name = "valueName")
    private String valueName;
    @Column(name = "valueType")
    private String valueType;
    @Column(name = "deviceId")
    private Integer deviceId;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDesc() {
	return desc;
    }

    public void setDesc(String desc) {
	this.desc = desc;
    }

    public Integer getDbId() {
	return dbId;
    }

    public String getValueName() {
	return valueName;
    }

    public void setValueName(String valueName) {
	this.valueName = valueName;
    }

    public String getValueType() {
	return valueType;
    }

    public void setValueType(String valueType) {
	this.valueType = valueType;
    }

    public Integer getDeviceId() {
	return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
	this.deviceId = deviceId;
    }

}
