package com.company;

/**
 * Created by ikanisamani on 9/30/14.
 */
public interface iSubject {
    public abstract void addObserver(iObserver o);
    public abstract void removeObserver(iObserver o);
    public abstract void notifyObservers();
}
