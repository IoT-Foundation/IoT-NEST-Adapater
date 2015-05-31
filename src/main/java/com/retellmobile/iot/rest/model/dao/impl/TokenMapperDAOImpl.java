package com.retellmobile.iot.rest.model.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.TokenMapper;
import com.retellmobile.iot.rest.model.dao.TokenMapperDAO;

@Repository
public class TokenMapperDAOImpl implements TokenMapperDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String addTokenMapper(TokenMapper tokenMapper) {
	return (String) sessionFactory.getCurrentSession().save(tokenMapper);
    }

    @Override
    public int deleteTokenMapperByTempToken(String tempToken) {
	TokenMapper device = (TokenMapper) sessionFactory.getCurrentSession()
		.get(TokenMapper.class, tempToken);
	if (device != null) {
	    sessionFactory.getCurrentSession().delete(device);
	    return 1;
	}
	return 0;
    }

    @Override
    public TokenMapper getTokenMapperByTempToken(String tempToken) {
	return (TokenMapper) sessionFactory.getCurrentSession().get(
		TokenMapper.class, tempToken);
    }

}
