package com.company;

import java.util.HashMap;

/**
 * Created by ikanisamani on 9/30/14.
 */
public abstract class Observer {
    public abstract void update(String upDatedTime, HashMap<String,TickerInfo> info);
}
