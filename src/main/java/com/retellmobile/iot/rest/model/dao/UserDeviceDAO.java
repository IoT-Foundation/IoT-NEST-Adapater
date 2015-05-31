package com.retellmobile.iot.rest.model.dao;

import java.util.List;

import com.retellmobile.iot.rest.model.UserDevice;

public interface UserDeviceDAO {
    int addUserDevice(UserDevice device);

    List<UserDevice> getDevicesForUser(String token);

    UserDevice getDeviceByUserDeviceIdentifier(String userDId);

    int deleteDeviceById(int dbId);

}
