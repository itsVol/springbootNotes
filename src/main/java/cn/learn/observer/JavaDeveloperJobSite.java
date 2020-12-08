package cn.learn.observer;

import java.util.ArrayList;
import java.util.List;

public class JavaDeveloperJobSite implements Observed{
    List<String> vacancies=new ArrayList<>();//位置
    List<Observer> subscriber =new ArrayList<>();//订阅者

    public void addVacancy(String vacancy){
        this.vacancies.add(vacancy);
        notifyObservers();
    }
    public void removeVacancy(String vacancy){
        this.vacancies.remove(vacancy);
        notifyObservers();
    }
    @Override
    public void addObserver(Observer observer) {
        this.subscriber.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.subscriber.remove(observer);

    }

    @Override
    public void notifyObservers() {
        for (Observer observer:subscriber){
            observer.handleEvent(this.vacancies);
        }

    }
}
