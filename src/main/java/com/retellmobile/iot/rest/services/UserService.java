package com.retellmobile.iot.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retellmobile.iot.rest.model.SessionTokenMapper;
import com.retellmobile.iot.rest.model.TokenMapper;
import com.retellmobile.iot.rest.model.User;
import com.retellmobile.iot.rest.model.dao.SessionTokenMapperDAO;
import com.retellmobile.iot.rest.model.dao.TokenMapperDAO;
import com.retellmobile.iot.rest.model.dao.UserDAO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TokenMapperDAO tmDAO;

    @Autowired
    private SessionTokenMapperDAO sessionTokenMapperDAO;

    @Transactional
    public String addUser(User user) {
	return this.userDAO.addUser(user);
    }

    @Transactional
    public User getUserFromToken(String userToken) {
	return this.userDAO.getUserFromToken(userToken);
    }

    @Transactional
    public void upsertSessionData(SessionTokenMapper stm) {
	sessionTokenMapperDAO.upsertTokenMapping(stm);
    }

    @Transactional
    public SessionTokenMapper getInitiatorSessionTokens(String sessionId) {
	return this.sessionTokenMapperDAO.getSessionTokenMapper(sessionId);
    }

    @Transactional
    public int deleteSessionEntry(String sessionId) {
	return this.sessionTokenMapperDAO.deleteEntry(sessionId);
    }

    @Transactional
    public void addTokenMapper(TokenMapper tmp) {
	this.tmDAO.addTokenMapper(tmp);
    }

    @Transactional
    public int deleteTokenMapperByTempToken(String tempToken) {
	return this.tmDAO.deleteTokenMapperByTempToken(tempToken);
    }

    @Transactional
    public TokenMapper getTokenMapperByTempToken(String tempToken) {
	return this.tmDAO.getTokenMapperByTempToken(tempToken);
    }
}
