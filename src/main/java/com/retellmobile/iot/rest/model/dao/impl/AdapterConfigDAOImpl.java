package com.retellmobile.iot.rest.model.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.AdapterConfig;
import com.retellmobile.iot.rest.model.dao.AdapterConfigDAO;

@Repository
public class AdapterConfigDAOImpl implements AdapterConfigDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean validateAuthToken(String apiToken) {
	String query = "FROM AdapterConfig WHERE token='" + apiToken + "'";
	@SuppressWarnings("unchecked")
	List<AdapterConfig> configs = sessionFactory.getCurrentSession()
	.createQuery(query).list();
	if (configs != null && configs.size() == 1) {
	    return true;
	}
	return false;
    }

}
