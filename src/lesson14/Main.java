package lesson14;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args){

        AttandanceLogger attandanceLogger = new AttandanceLogger();

        attandanceLogger.registerVisit("user1", "09:15");
        attandanceLogger.registerVisit("user2", "10:00");
        attandanceLogger.registerVisit("user1", "09:45");
        attandanceLogger.registerVisit("user3", "11:30");
        attandanceLogger.registerVisit("user2", "10:20");
        attandanceLogger.registerVisit("user4", "14:10");
        attandanceLogger.registerVisit("user1", "09:55");
        attandanceLogger.registerVisit("user2", "10:45");
        attandanceLogger.registerVisit("user4", "14:20");
        attandanceLogger.registerVisit("user3", "11:50");
        attandanceLogger.registerVisit("user5", "15:00");
        attandanceLogger.registerVisit("user1", "16:30");
        attandanceLogger.registerVisit("user3", "11:59");
//        attandanceLogger.registerVisit("user2", "11:11");

        attandanceLogger.printAllVisits();

        attandanceLogger.printVisitFrequencies();

        String mostPopularHour = attandanceLogger.mostPopularHour();

        System.out.println("Популярные часы: " + mostPopularHour);

    }
}
