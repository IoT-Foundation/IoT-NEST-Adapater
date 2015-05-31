package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adapterConfig")
public class AdapterConfig {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer dbId;
    @Column(name = "name")
    private String name;
    @Column(name = "token")
    private String token;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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
