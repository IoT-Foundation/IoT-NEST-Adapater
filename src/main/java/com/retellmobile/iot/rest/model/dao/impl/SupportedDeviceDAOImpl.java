package com.retellmobile.iot.rest.model.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.SupportedDevice;
import com.retellmobile.iot.rest.model.dao.SupportedDeviceDAO;

@Repository
public class SupportedDeviceDAOImpl implements SupportedDeviceDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addSupportedDevices(SupportedDevice device) {
	return (Integer) sessionFactory.getCurrentSession().save(device);
    }

    @Override
    public SupportedDevice getDeviceById(int deviceId) {
	SupportedDevice sd = (SupportedDevice) sessionFactory
		.getCurrentSession().get(SupportedDevice.class, deviceId);
	return sd;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SupportedDevice> getSupportedDevices() {
	return sessionFactory.getCurrentSession()
		.createQuery("from SupportedDevice").list();
    }

}
