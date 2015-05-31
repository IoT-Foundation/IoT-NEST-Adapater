package com.retellmobile.iot.rest.model.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retellmobile.iot.rest.model.Action;
import com.retellmobile.iot.rest.model.dao.ActionDAO;

@Repository
public class ActionDAOImpl implements ActionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int addAction(Action action) {
	return (Integer) sessionFactory.getCurrentSession().save(action);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Action> getActionsForDeviceByDeviceId(int deviceId) {
	return sessionFactory.getCurrentSession()
		.createQuery("from Action where deviceId =" + deviceId + "")
		.list();
    }

    @Override
    public int deleteAction(int actionId) {
	Action action = (Action) sessionFactory.getCurrentSession().get(
		Action.class, actionId);
	if (action != null) {
	    sessionFactory.getCurrentSession().delete(action);
	    return 1;
	}
	return 0;
    }

    @Override
    public Action getActionById(int actionId) {
	return (Action) sessionFactory.getCurrentSession().get(Action.class,
		actionId);
    }

}
