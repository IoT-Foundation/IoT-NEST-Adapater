package com.retellmobile.iot.rest.resp.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NestResponse {
    private JSONObject devices;
    private JSONObject structures;
    private JSONObject metadata;

    public JSONObject getDevices() {
	return devices;
    }

    public void setDevices(JSONObject devices) {
	this.devices = devices;
    }

    public JSONObject getStructures() {
	return structures;
    }

    public void setStructures(JSONObject structures) {
	this.structures = structures;
    }

    public JSONObject getMetadata() {
	return metadata;
    }

    public void setMetadata(JSONObject metadata) {
	this.metadata = metadata;
    }
}
