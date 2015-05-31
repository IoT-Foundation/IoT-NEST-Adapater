package com.retellmobile.iot.rest.util;

import java.util.Hashtable;
import java.util.List;

import com.retellmobile.iot.rest.model.EndpointInfo;

public class RetellIoTObjectUtil {

    public static Hashtable<String, String> convertEndpointInfoToHashtable(
	    List<EndpointInfo> info) {
	Hashtable<String, String> endpointInfo = new Hashtable<String, String>();
	if (info != null && info.size() > 0) {
	    for (EndpointInfo endpoint : info) {
		endpointInfo.put(endpoint.getName(), endpoint.getValue());
	    }
	}
	return endpointInfo;
    }
}
