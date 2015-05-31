package com.retellmobile.iot.rest.model.dao;

import java.util.List;

import com.retellmobile.iot.rest.model.Action;

public interface ActionDAO {
    int addAction(Action action);

    List<Action> getActionsForDeviceByDeviceId(int deviceId);

    Action getActionById(int actionId);

    int deleteAction(int actionId);

}
