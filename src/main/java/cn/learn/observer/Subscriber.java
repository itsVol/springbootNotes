package cn.learn.observer;

import java.util.List;
//订阅者
public class Subscriber implements Observer{
    String name;
    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(List<String> vacancies) {
        System.out.println("Dear"+ name + " we had some changes in "+vacancies);


    }
}
