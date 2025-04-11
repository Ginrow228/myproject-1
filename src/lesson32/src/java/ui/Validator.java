package ui;

public class Validator {
    public static boolean isValidIpAddress(String ipAddress){
        String ip = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        if (ipAddress == null || ipAddress.isEmpty()) {
            return false;
        }
        return ipAddress.matches(ip);
    }

    public static boolean isValidMacAddress(String macAddress) {
        String mac = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";
        if(macAddress == null || macAddress.isEmpty()) {
            return false;
        }
        return macAddress.matches(mac);
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.matches("^[a-zA-Z0-9_\\-\\s]+$");
    }

    public static boolean isValidStatus(String status) {
        String[] statuses = {"active", "inactive"};
        for (String validStatus : statuses) {
            if(validStatus.equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidType(String type) {
        String[] types = {"router", "server", "switch", "other"};
        for (String validType : types) {
            if(validType.equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

}
