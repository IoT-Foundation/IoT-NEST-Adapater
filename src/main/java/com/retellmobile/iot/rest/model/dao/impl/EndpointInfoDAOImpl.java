package com.retellmobile.iot.rest.model.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.EndpointInfo;
import com.retellmobile.iot.rest.model.dao.EndpointInfoDAO;

@Repository
public class EndpointInfoDAOImpl implements EndpointInfoDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<EndpointInfo> getEndpointInfo() {
	return sessionFactory.getCurrentSession()
		.createQuery("from EndpointInfo").list();
    }

}
