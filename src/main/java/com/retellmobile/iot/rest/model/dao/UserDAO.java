package com.retellmobile.iot.rest.model.dao;

import com.retellmobile.iot.rest.model.User;

public interface UserDAO {

    String addUser(User user);

    User getUserFromToken(String token);

}
