package cn.learn.observer;

import cn.learn.observer.Observer;
//被观察者
public interface Observed {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();


}
