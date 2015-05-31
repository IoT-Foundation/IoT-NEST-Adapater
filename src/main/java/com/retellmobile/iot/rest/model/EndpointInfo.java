package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endpointInfo")
public class EndpointInfo {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer dbId;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getDbId() {
	return dbId;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
