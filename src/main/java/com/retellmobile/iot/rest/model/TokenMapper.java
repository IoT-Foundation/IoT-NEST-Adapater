package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tokenMapper")
public class TokenMapper {
    @Id
    @Column(name = "tempToken")
    private String tempToken;
    @Column(name = "token")
    private String token;

    public String getTempToken() {
	return tempToken;
    }

    public void setTempToken(String tempToken) {
	this.tempToken = tempToken;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

}
