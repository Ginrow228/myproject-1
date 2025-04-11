package services;

import jdbc.ConnectionDao;
import jdbc.DeviceDao;
import jdbc.NetworksDao;
import model.Device;
import model.Network;
import ui.ConsoleController;
import java.util.List;
import java.util.Map;

public class NetworkService {
    private NetworksDao networkDao;
    private DeviceDao deviceDao;
    private ConnectionDao connectionDao;
    private ConsoleController consoleController;

    public NetworkService(ConsoleController consoleController) throws ClassNotFoundException {
        networkDao = new NetworksDao();
        deviceDao = new DeviceDao();
        connectionDao = new ConnectionDao();
        this.consoleController = consoleController;
    }

    public void process() {
        while(true) {
            int userChoice = consoleController.getUserChoice();
            var selectedAction = UserAction.valueOf(userChoice);

            if (selectedAction.isPresent()) {
                var action = selectedAction.get();
                if(action == UserAction.EXIT)
                    return;

                try {
                    processChoice(action);
                } catch (Exception e) {
                    consoleController.printError(e.getMessage());
                }
            } else {
                consoleController.printError("Unexpected choice");
            }
        }
    }

    private void processChoice(UserAction action) throws Exception {
        switch (action) {
            case ADD_NETWORK -> {
                var network = consoleController.readNewNetwork();
                network = networkDao.save(network);
                consoleController.printNetwork(network);
            }
            case ADD_DEVICE -> {
                var networks = networkDao.getNetworks();
                var networkToSet = consoleController.selectNetwork(networks);
                var deviceToAdd = consoleController.readNewDevice();
                deviceToAdd.setNetworkId(networkToSet.getId());
                deviceToAdd = deviceDao.save(deviceToAdd);
                System.out.println("Device added: " + deviceToAdd.getName());
            }
            case ADD_CONNECTION -> {
                var networks = networkDao.getNetworks();
                var selectedNetwork = consoleController.selectNetwork(networks);
                List<Device> devices = deviceDao.getDevicesByNetworkId(selectedNetwork.getId());
                if (devices.size() < 2) {
                    System.out.println("There are not enough devices on the network to create a connection");
                    break;
                }
                var connectionToAdd = consoleController.readNewConnection(devices);
                connectionToAdd = connectionDao.save(connectionToAdd);
                System.out.println("Connection added between devices with IDs: " +
                        connectionToAdd.getDeviceFromId() + " and " + connectionToAdd.getDeviceToId());
            }
            case SEARCH_NETWORK -> {
                var name = consoleController.readSearchName();
                List<Network> networks = networkDao.findNetworksByName(name);
                consoleController.printNetworks(networks);
            }
            case SEARCH_DEVICE -> {
                var name = consoleController.readSearchName();
                List<Device> devices = deviceDao.findDevicesByName(name);
                consoleController.printDevices(devices);
            }
            case SHOW_NETWORKS_WITH_DEVICES -> {
                List<Network> networksWithDevices = networkDao.getNetworksWithDevices();
                consoleController.printNetworksWithDevices(networksWithDevices);
            }
            case SHOW_DEVICES_WITH_CONNECTIONS -> {
                var networks = networkDao.getNetworks();
                var selectedNetwork = consoleController.selectNetwork(networks);
                List<Device> devicesWithConnections = deviceDao.getDevicesWithConnectionsByNetworkId(selectedNetwork.getId());
                consoleController.printDevicesWithConnections(devicesWithConnections);
            }
            case SHOW_STATISTICS -> {
                var networks = networkDao.getNetworks();
                var selectedNetwork = consoleController.selectNetwork(networks);
                int activeDevices = networkDao.countActiveDevicesInNetwork(selectedNetwork.getId());
                Map<String, Integer> deviceTypeStats = deviceDao.getDeviceTypeStatisticsByNetworkId(selectedNetwork.getId());
                Map<String, Integer> connectionTypeStats = connectionDao.getConnectionTypeStatisticsByNetworkId(selectedNetwork.getId());
                consoleController.printStatistics(selectedNetwork, activeDevices, deviceTypeStats, connectionTypeStats);
            }
            case EXIT -> {
                return;
            }
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }
}


