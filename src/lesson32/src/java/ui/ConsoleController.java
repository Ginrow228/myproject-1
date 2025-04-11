package ui;

import model.Connection;
import model.Device;
import model.Network;
import services.UserAction;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class ConsoleController {
    private final Scanner scanner;

    public ConsoleController(Scanner scanner) {
        this.scanner = scanner;
    }

    public Network readNewNetwork() {
        String name;
        do {
            name = readString("Enter network name: ", scanner);
            if (!Validator.isValidName(name)) {
                System.out.println("Use only letters, numbers, spaces, underscores and hyphens");
            }
        } while (!Validator.isValidName(name));

        String description = readString("Enter network description: ", scanner);

        return new Network(name, description);
    }

    public Device readNewDevice() {
        String name = readValidatedInput(
                "Enter device name: ",
                scanner,
                Validator::isValidName,
    "Use only letters, numbers, spaces, underscores and hyphens."
        );
        String ipAddress = readValidatedInput(
                "Enter device ip: ",
                scanner,
                Validator::isValidIpAddress,
                "Use format XXX.XXX.XXX"
        );
        String macAddress = readValidatedInput(
                "Enter device mac: ",
                scanner,
                Validator::isValidMacAddress,
                "Use format XXX.XXX.XXX or XXX-XXX-XXX"
        );
        String type = readValidatedInput(
                "Enter device type: ",
                scanner,
                Validator::isValidType,
                "Type can only be router, switch, server and other"
        );
        String status = readValidatedInput(
                "Enter device status: ",
                scanner,
                Validator::isValidStatus,
                "Status can only be active or inactive"
        );
        return new Device(name, ipAddress, macAddress, type, status);
    }


    public int getUserChoice() {
        System.out.println("Enter to do: ");
        for (var action : UserAction.values()) {
            System.out.println(action.getCode() + " " + action.getDescription());
        }

        return scanner.nextInt();
    }

    public Network selectNetwork(List<Network> networks) {
        System.out.println("Select network by id: ");
        while (true) {
            for (var network : networks) {
                System.out.println(network.toString());
            }

            var id = scanner.nextLong();
            scanner.nextLine();
            for (Network network : networks) {
                if (network.getId().equals(id)) {
                    return network;
                }
            }
            System.out.println("Incorrect id");
        }
    }

    private static String readString(String request, Scanner scanner) {
        System.out.println(request);
        return scanner.nextLine();
    }

    public String readSearchName() {
        System.out.println("Enter name to search: ");
        return scanner.next();
    }

    public void printNetworks(List<Network> networks) {
        System.out.println("Found networks: ");
        for (Network network : networks) {
            System.out.println(network.toString());
        }
    }

    public void printDevices(List<Device> devices) {
        System.out.println("Found devices: ");
        for (Device device : devices) {
            System.out.println("Device: " + device.getName() + " (ID: " + device.getId() + ")");
            System.out.println("  IP: " + device.getIpAddress());
            System.out.println("  MAC: " + device.getMacAddress());
            System.out.println("  Type: " + device.getType());
            System.out.println("  Status: " + device.getStatus());
        }
    }

    public void printNetworksWithDevices(List<Network> networksWithDevices) {
        System.out.println("Networks and devices: ");
        for (Network network : networksWithDevices) {
            System.out.println("Network: " + network.getName() + " (ID: " + network.getId() + ")");
            System.out.println("Description: " + network.getDescription());

            List<Device> devices = network.getDevices();
            System.out.println("Device (" + devices.size() + "): ");
            for (Device device : devices) {
                System.out.println(device.getName() + " (ID: " + device.getId() + ")");
                System.out.println("IP: " + device.getIpAddress());
                System.out.println("Type: " + device.getType());
                System.out.println("Status: " + device.getStatus());
            }
        }
    }

    public void printDevicesWithConnections(List<Device> devicesWithConnections) {
        System.out.println("Devices and their connections: ");
        if (devicesWithConnections.isEmpty()) {
            System.out.println("No devices found is this network!");
            return;
        }

        for (Device device : devicesWithConnections) {
            System.out.println("Device: " + device.getName() + " (ID: " + device.getId() + ")");
            System.out.println("IP: " + device.getIpAddress());
            System.out.println("MAC: " + device.getMacAddress());
            System.out.println("Type: " + device.getType());
            System.out.println("Status: " + device.getStatus());
            List<Connection> connections = device.getConnections();
            if (connections.isEmpty()) {
                System.out.println("No connections for this device!");
            } else {
                System.out.println("Connections (" + connections.size() + "):");
                for (Connection connection : connections) {
                    System.out.println("ID Connection" + connection.getId());
                    System.out.println("From device ID: " + connection.getDeviceFromId());
                    System.out.println("To device ID: " + connection.getDeviceToId());
                    System.out.println("Type: " + connection.getConnectionType());
                    System.out.println("Status: " + connection.getStatus());
                }
            }
        }
    }

    public Connection readNewConnection(List<Device> devices) {
        System.out.println("Select first device by id: ");
        for (Device device : devices) {
            System.out.println("Device: " + device.getName() + " (ID: " + device.getId() + ")");
            System.out.println("Ip: " + device.getIpAddress());
            System.out.println("Mac: " + device.getMacAddress());
        }

        Long deviceFromId = scanner.nextLong();
        scanner.nextLine();

        Device deviceFrom = null;
        for (Device device : devices) {
            if (device.getId().equals(deviceFromId)) {
                deviceFrom = device;
                break;
            }
        }
        if (deviceFrom == null) {
            System.out.println("Invalid device ID");
            return null;
        }
        System.out.println("Select second device by id: ");
        for (Device device : devices) {
            if (!device.getId().equals(deviceFromId)) {
                System.out.println("Device: " + device.getName() + " (ID: " + device.getId() + ")");
                System.out.println("Ip: " + device.getIpAddress());
                System.out.println("Mac: " + device.getMacAddress());
            }
        }

        Long deviceToId = scanner.nextLong();
        scanner.nextLine();
        Device deviceTo = null;
        for (Device device : devices) {
            if (device.getId().equals(deviceToId)) {
                deviceTo = device;
                break;
            }
        }
        if (deviceTo == null) {
            System.out.println("Invalid device ID");
            return null;
        }
        String connectionType = readString("Enter connection type: ", scanner);
        String status = readString("Enter connection status: ", scanner);
        return new Connection(null, deviceFrom.getId(), deviceTo.getId(), connectionType, status, null);
    }

    public void printStatistics(Network network, int activeDevices, Map<String, Integer> deviceTypes, Map<String, Integer> connectionTypes) {
        System.out.println("=== Statistics ===");
        System.out.println("Network: " + network.getName() + " (ID: " + network.getId() + ")");
        System.out.println("Description: " + network.getDescription());
        System.out.println("Created at: " + network.getCreated_at());
        System.out.println("------------------------");

        System.out.println("Active devices: " + activeDevices);
        System.out.println("Device Types:");
        if (deviceTypes.isEmpty()) {
            System.out.println("No devices in this network");
        } else {
            for (String type : deviceTypes.keySet()) {
                System.out.println(type + ": " + deviceTypes.get(type));
            }
            System.out.println("------------------------");
        }

        System.out.println("Connection Types:");
        if (connectionTypes.isEmpty()) {
            System.out.println("No connections in this network");
        } else {
            for (String type : connectionTypes.keySet()) {
                System.out.println(type + ": " + connectionTypes.get(type));
            }
            System.out.println("==============");
        }
    }


    public void printNetwork(Network network) {
        System.out.println(network.toString());
    }

    public void printError(String error) {
        System.out.println(error);
    }

    private String readValidatedInput(String prompt, Scanner scanner, Predicate<String> validator, String errorMessage) {
        String input;
        do {
            input = readString(prompt, scanner);
            if (!validator.test(input)) {
                System.out.println(errorMessage);
            }
        } while (!validator.test(input));
        return input;
    }

}
