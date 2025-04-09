package services;

import jdbc.NetworksDao;
import ui.ConsoleController;
import java.sql.SQLException;

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
            }
            case EXIT -> {
                return;
            }
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }
}

