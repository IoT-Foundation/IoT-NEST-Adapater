package com.retellmobile.iot.rest.model.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.User;
import com.retellmobile.iot.rest.model.dao.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String addUser(User user) {
	return (String) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserFromToken(String token) {
	return (User) sessionFactory.getCurrentSession().get(User.class, token);
    }

}
