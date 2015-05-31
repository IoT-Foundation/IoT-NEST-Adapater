package com.retellmobile.iot.rest.model.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.UserDevice;
import com.retellmobile.iot.rest.model.dao.UserDeviceDAO;

@Repository
public class UserDeviceDAOImpl implements UserDeviceDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addUserDevice(UserDevice device) {
	return (Integer) sessionFactory.getCurrentSession().save(device);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserDevice> getDevicesForUser(String token) {
	return sessionFactory.getCurrentSession()
		.createQuery("from UserDevice where token ='" + token + "'")
		.list();
    }

    @Override
    public UserDevice getDeviceByUserDeviceIdentifier(String userDId) {
	@SuppressWarnings("unchecked")
	List<UserDevice> device = sessionFactory
	.getCurrentSession()
	.createQuery(
		"from UserDevice where userDeviceIdentifier ='"
				+ userDId + "'").list();
	if (device != null && device.size() > 0) {
	    return device.get(0);
	} else {
	    return null;
	}
    }

    @Override
    public int deleteDeviceById(int dbId) {
	UserDevice device = (UserDevice) sessionFactory.getCurrentSession()
		.get(UserDevice.class, dbId);
	if (device != null) {
	    sessionFactory.getCurrentSession().delete(device);
	    return 1;
	}
	return 0;
    }

}
