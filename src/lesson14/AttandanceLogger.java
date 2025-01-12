package lesson14;
import java.util.*;

public class AttandanceLogger {
    private final List<Visit> log;
    private final Map<String, Integer> visitFrequency;

    public AttandanceLogger() {
        this.log = new ArrayList<>();
        this.visitFrequency = new HashMap<>();
    }

    public void registerVisit(String userID, String timestamp) {
        Visit newVisit = new Visit(userID, timestamp);
        log.add(newVisit);
        visitFrequency.put(userID, visitFrequency.getOrDefault(userID, 0) + 1);

    }

    public Map<String, Integer> frequencyOfVisits() {
        return new HashMap<>(visitFrequency);
    }

    public void printAllVisits() {
        for (Visit visit : log) {
            System.out.println(visit);
        }
    }

    public void printVisitFrequencies() {
        Map<String, Integer> frequencyMap = frequencyOfVisits();
        for (String userID : frequencyMap.keySet()) {
            Integer visitCounter = frequencyMap.get(userID);
            System.out.println(userID + ": " + visitCounter);
        }
    }

    public String mostPopularHour() {
        Map<String, Integer> hourFrequency = new TreeMap<>();
        int maxVisits = 0;

        for (Visit visit : log) {
            String visitTime = visit.getTimestamp();
            String hour = visitTime.substring(0, 2);
            int count = hourFrequency.getOrDefault(hour, 0) + 1;
            hourFrequency.put(hour, count);
            if (count > maxVisits) {
                maxVisits = count;
            }
        }
        String mostPopularHour = "";
        for (String hour : hourFrequency.keySet()) {
            if (hourFrequency.get(hour) == maxVisits) {
                if (!mostPopularHour.isEmpty()) {
                    mostPopularHour += ", ";
                }
                mostPopularHour += hour + " : 00";
            }
        }
        return mostPopularHour;
    }
}
