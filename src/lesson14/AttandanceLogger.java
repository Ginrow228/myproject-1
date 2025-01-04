package lesson14;

import com.sun.source.tree.Tree;

import java.time.LocalTime;
import java.util.*;

public class AttandanceLogger {

    private List<String[]> log;
    private Map<String, Integer> visitFrequency;

    public AttandanceLogger() {
        this.log = new ArrayList<>();
        this.visitFrequency = new HashMap<>();
    }

    public void registerVisit(String userID, String timestamp){
        log.add(new String[]{userID, timestamp});
        visitFrequency.put(userID, visitFrequency.getOrDefault(userID, 0) + 1);

    }

    public Map<String, Integer> frequencyOfVisits(){
        return new HashMap<>(visitFrequency);
    }

    public void printAllVisits(){
        for (String[] record : log) {
            System.out.println(record[0] + " " + record[1]);
        }
    }

    public void printVisitFrequencies(){
        Map<String, Integer> frequencyMap = frequencyOfVisits();
        for(String userID : frequencyMap.keySet()){
            Integer visitCounter = frequencyMap.get(userID);
            System.out.println(userID + ": " + visitCounter);
        }
    }

    public String mostPopularHour(){
        Map<String, Integer> hourFrequency = new TreeMap<>();
        for(String[] record : log){
            String visitTime = record[1];
            String hour = visitTime.substring(0, 2);
            hourFrequency.put(hour, hourFrequency.getOrDefault(hour, 0) + 1);
        }

        int maxVisits = 0;
        for(String hour : hourFrequency.keySet()){
            int visitCounter = hourFrequency.get(hour);
            if(visitCounter > maxVisits){
                maxVisits = visitCounter;
            }
        }
        String mostPopularHour = "";
        for (String hour : hourFrequency.keySet()){
            if(hourFrequency.get(hour) == maxVisits){
                if(!mostPopularHour.isEmpty()){
                    mostPopularHour += ", ";
                }
                mostPopularHour += hour + " : 00";
            }
        }

        return mostPopularHour;
    }

}
