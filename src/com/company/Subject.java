package com.company;

/**
 * Created by ikanisamani on 9/30/14.
 */
public abstract class Subject {
    public abstract void addObserver(Observer o);
    public abstract void removeObserver(Observer o);
    public abstract void notifyObservers();
}
