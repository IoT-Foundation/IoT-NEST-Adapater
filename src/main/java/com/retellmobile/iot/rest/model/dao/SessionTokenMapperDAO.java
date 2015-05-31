package com.retellmobile.iot.rest.model.dao;

import com.retellmobile.iot.rest.model.SessionTokenMapper;

public interface SessionTokenMapperDAO {

    void upsertTokenMapping(SessionTokenMapper stm);

    SessionTokenMapper getSessionTokenMapper(String sessionId);

    int deleteEntry(String sessionId);

}
