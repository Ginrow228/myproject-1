package services;

import jdbc.NetworksDao;
import model.Device;
import model.Network;
import ui.ConsoleController;
import java.util.List;

public class NetworkService {
    private NetworksDao networkDao;
    private ConsoleController consoleController;

    public NetworkService(ConsoleController consoleController) throws ClassNotFoundException {
        networkDao = new NetworksDao();
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
                deviceToAdd = networkDao.save(deviceToAdd);
                System.out.println("Device added: " + deviceToAdd.getName());
            }
            case SEARCH_NETWORK -> {
                var name = consoleController.readSearchName();
                List<Network> networks = networkDao.findNetworksByName(name);
                consoleController.printNetworks(networks);
            }
            case SEARCH_DEVICE -> {
                var name = consoleController.readSearchName();
                List<Device> devices = networkDao.findDevicesByName(name);
                consoleController.printDevices(devices);
            }
            case EXIT -> {
                return;
            }
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }
}

