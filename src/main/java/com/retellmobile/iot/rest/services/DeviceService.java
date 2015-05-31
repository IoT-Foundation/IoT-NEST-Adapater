package com.retellmobile.iot.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retellmobile.iot.rest.model.Action;
import com.retellmobile.iot.rest.model.SupportedDevice;
import com.retellmobile.iot.rest.model.UserDevice;
import com.retellmobile.iot.rest.model.dao.ActionDAO;
import com.retellmobile.iot.rest.model.dao.SupportedDeviceDAO;
import com.retellmobile.iot.rest.model.dao.UserDeviceDAO;

@Service
public class DeviceService {

    @Autowired
    private SupportedDeviceDAO sdDAO;

    @Autowired
    private UserDeviceDAO userDeviceDAO;

    @Autowired
    private ActionDAO actionDAO;

    @Transactional
    public List<SupportedDevice> getSupportedDevices() {
	return this.sdDAO.getSupportedDevices();
    }

    @Transactional
    public SupportedDevice getDeviceById(int deviceId) {
	return this.sdDAO.getDeviceById(deviceId);
    }

    @Transactional
    public int addSupportedDevice(SupportedDevice sd) {
	return this.sdDAO.addSupportedDevices(sd);
    }

    @Transactional
    public List<UserDevice> getUserDevices(String token) {
	return this.userDeviceDAO.getDevicesForUser(token);
    }

    @Transactional
    public UserDevice getUserDeviceByUserDeviceId(String userDId) {
	return this.userDeviceDAO.getDeviceByUserDeviceIdentifier(userDId);
    }

    @Transactional
    public int addUserDevice(UserDevice device) {
	return this.userDeviceDAO.addUserDevice(device);
    }

    @Transactional
    public int deleteUserDevice(int dbId) {
	return this.userDeviceDAO.deleteDeviceById(dbId);
    }

    @Transactional
    public List<Action> getActionsForDevice(int deviceId) {
	return this.actionDAO.getActionsForDeviceByDeviceId(deviceId);
    }

    @Transactional
    public Action getActionById(int dbId) {
	return this.actionDAO.getActionById(dbId);
    }

    @Transactional
    public int addAction(Action action) {
	return this.actionDAO.addAction(action);
    }
}
