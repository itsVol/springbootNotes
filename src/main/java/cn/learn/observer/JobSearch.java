package cn.learn.observer;

public class JobSearch {
    public static void main(String[] args) {
        JavaDeveloperJobSite jobSite= new JavaDeveloperJobSite();
        jobSite.addVacancy("First job position");
        jobSite.addVacancy("Second job position");

        Observer FirstSubscriber=new Subscriber("Alex");
        Observer SecondSubscriber=new Subscriber("Anne");

        jobSite.addObserver(FirstSubscriber);
        jobSite.addObserver(SecondSubscriber);

        jobSite.addVacancy("Third job position ");
    }
}
