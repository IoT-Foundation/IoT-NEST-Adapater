package com.retellmobile.iot.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sessionTokenMapper")
public class SessionTokenMapper {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;
    @Column(name = "sessionId")
    private String sessionId;
    @Column(name = "returnURL")
    private String returnURL;
    @Column(name = "state")
    private String state;

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getReturnURL() {
	return returnURL;
    }

    public void setReturnURL(String returnURL) {
	this.returnURL = returnURL;
    }

    public String getId() {
	return id;
    }

    public String getSessionId() {
	return sessionId;
    }

    public void setClientId(String sessionId) {
	this.sessionId = sessionId;
    }

}
