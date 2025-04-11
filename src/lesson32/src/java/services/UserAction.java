package services;

import java.util.Arrays;
import java.util.Optional;

public enum UserAction {
    ADD_NETWORK(1, "To add new network"),
    ADD_DEVICE(2, "To add device to network"),
    ADD_CONNECTION(3, "To add connection"),
    SEARCH_NETWORK(4, "Search network by name"),
    SEARCH_DEVICE(5, "Search device by name"),
    SHOW_NETWORKS_WITH_DEVICES(6,"Show all networks and devices connected to them"),
    SHOW_DEVICES_WITH_CONNECTIONS(7,"Show all devices and their connections on the network"),
    SHOW_STATISTICS(8, "Show statistics"),
    EXIT(9, "To exit");

    private int code;
    private String description;

    UserAction(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<UserAction> valueOf(int code) {
        return Arrays.stream(values())
                .filter(action -> action.code == code)
                .findAny();
    }
}
