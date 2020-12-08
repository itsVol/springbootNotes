package cn.learn.observer;

import java.util.List;
//观察者
public interface Observer {
    public void handleEvent(List<String> vacancies);
}
