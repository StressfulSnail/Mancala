package edu.metrostate.ics425.atu588.hw5.controller;

import edu.metrostate.ics425.atu588.hw5.model.MancalaBean;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author adam
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
       MancalaBean mb = new MancalaBean();
       se.getSession().setAttribute("game", mb);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {}
}
