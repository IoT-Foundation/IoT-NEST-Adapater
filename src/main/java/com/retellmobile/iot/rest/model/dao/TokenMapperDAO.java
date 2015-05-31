package com.retellmobile.iot.rest.model.dao;

import com.retellmobile.iot.rest.model.TokenMapper;

public interface TokenMapperDAO {

    String addTokenMapper(TokenMapper tokenMapper);

    int deleteTokenMapperByTempToken(String tempToken);

    TokenMapper getTokenMapperByTempToken(String tempToken);
}
