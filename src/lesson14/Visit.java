package lesson14;

public class Visit {

    private String userID;
    private String timestamp;

    public Visit(String userID, String timestamp) {
        this.userID = userID;
        this.timestamp = timestamp;
    }

    public String getUserID() {
        return userID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "userID='" + userID + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
