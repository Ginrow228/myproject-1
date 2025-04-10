package ui;

import model.Device;
import model.Network;
import services.NetworkService;
import services.UserAction;

import java.util.List;
import java.util.Scanner;

public class ConsoleController {
    private final Scanner scanner;

    public ConsoleController(Scanner scanner) {
        this.scanner = scanner;
    }

    public Network readNewNetwork() {
        try (Scanner scanner = new Scanner(System.in)) {
            String name = readString("Enter network name: ", scanner);
            String description = readString("Enter network description: ", scanner);

            return new Network(name, description);
        }
    }

    public Device readNewDevice() {
        try (Scanner scanner = new Scanner(System.in)) {
            String name = readString("Enter network name: ", scanner);
            String ipAddress = readString("Enter network ip: ", scanner);
            String macAddress = readString("Enter network mac: ", scanner);
            String type = readString("Enter network type: ", scanner);
            String status = readString("Enter network status: ", scanner);

            return new Device(name, ipAddress, macAddress, type, status);
        }
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

            var index = scanner.nextInt();
            if(index < 0 || index >= networks.size()) {
                System.out.println("Incorrect index");
            } else {
                return networks.get(index);
            }
        }
    }

    private static String readString(String request, Scanner scanner){
        System.out.println(request);
        scanner.reset();
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

    public void printNetwork(Network network) {
        System.out.println(network.toString());
    }

    public void printError(String error) {
        System.out.println(error);
    }

    private static int readInt(String request, Scanner scanner){
        System.out.println(request);
        scanner.reset();
        return scanner.nextInt();
    }
}
