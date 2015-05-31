package com.retellmobile.iot.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retellmobile.iot.rest.model.dao.AdapterConfigDAO;

@Service
public class AdapterConfigService {

    @Autowired
    private AdapterConfigDAO authDAO;

    @Transactional
    public boolean isValidAuthToken(String apiToken) {
	return this.authDAO.validateAuthToken(apiToken);
    }
}
