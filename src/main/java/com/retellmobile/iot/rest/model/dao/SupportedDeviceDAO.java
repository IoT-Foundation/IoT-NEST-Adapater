package com.retellmobile.iot.rest.model.dao;

import java.util.List;

import com.retellmobile.iot.rest.model.SupportedDevice;

public interface SupportedDeviceDAO {

    int addSupportedDevices(SupportedDevice devices);

    List<SupportedDevice> getSupportedDevices();

    SupportedDevice getDeviceById(int deviceId);

}
