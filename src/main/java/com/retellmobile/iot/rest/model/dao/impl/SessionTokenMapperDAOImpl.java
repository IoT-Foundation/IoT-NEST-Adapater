package com.retellmobile.iot.rest.model.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.SessionTokenMapper;
import com.retellmobile.iot.rest.model.dao.SessionTokenMapperDAO;

@Repository
public class SessionTokenMapperDAOImpl implements SessionTokenMapperDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void upsertTokenMapping(SessionTokenMapper stm) {
	sessionFactory.getCurrentSession().merge(stm);
    }

    @Override
    public SessionTokenMapper getSessionTokenMapper(String sessionId) {
	String query = "FROM SessionTokenMapper WHERE sessionId='" + sessionId
		+ "'";
	@SuppressWarnings("unchecked")
	List<SessionTokenMapper> maps = sessionFactory.getCurrentSession()
		.createQuery(query).list();
	if (maps != null && maps.size() > 0) {
	    return maps.get(0);
	}
	return null;
    }

    @Override
    public int deleteEntry(String sessionId) {
	String query = "FROM SessionTokenMapper WHERE sessionId='" + sessionId
		+ "'";
	@SuppressWarnings("unchecked")
	List<SessionTokenMapper> maps = sessionFactory.getCurrentSession()
		.createQuery(query).list();
	if (maps != null && maps.size() == 1) {
	    sessionFactory.getCurrentSession().delete(maps);
	    return 1;
	}
	return 0;
    }

}
