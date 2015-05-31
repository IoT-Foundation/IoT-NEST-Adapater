package com.retellmobile.iot.rest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "token")
    private String token;
    @Column(name = "oauthToken")
    private String oauthToken;
    @Column(name = "insertedTimestamp", insertable = false, updatable = false)
    private Date insertedTimestamp;
    @Column(name = "updatedTimestamp", insertable = true, updatable = true)
    private Date updatedTimestamp;

    public String getNestAuthToken() {
	return oauthToken;
    }

    public void setNestAuthToken(String oauthToken) {
	this.oauthToken = oauthToken;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public Date getUpdatedTimestamp() {
	return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Date updatedTimestamp) {
	this.updatedTimestamp = updatedTimestamp;
    }

    public Date getInsertedTimestamp() {
	return insertedTimestamp;
    }

}
