package com.retellmobile.iot.rest.services;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retellmobile.iot.rest.model.dao.EndpointInfoDAO;
import com.retellmobile.iot.rest.util.RetellIoTObjectUtil;

@Service
public class EndpointInfoService {

    @Autowired
    private EndpointInfoDAO endpointDAO;

    @Transactional
    public Hashtable<String, String> getEndpointInfo() {
	return RetellIoTObjectUtil
		.convertEndpointInfoToHashtable(this.endpointDAO
			.getEndpointInfo());
    }
}
