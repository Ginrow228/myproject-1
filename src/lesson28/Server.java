package lesson28;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Server {
    private static final int MAX_CLIENTS = 3;
    private static List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7);
        System.out.println("Ожидаем подключения клиента...");

        Thread consoleThread = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите сообщение для отправки клиентам или -q для выхода: ");
            String input;
            while (!(input = sc.nextLine()).equals("-q")) {
                synchronized (clients) {
                    String message = input;
                    for (ClientHandler client : clients) {
                        client.sendMessage(message);
                    }
                }
                if (!clients.isEmpty()) {
                    System.out.println("Сообщение отправлено всем клиентам");
                } else {
                    System.out.println("Клиентов не наблюдается");
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            System.out.println("Завершение работы сервера...");
            System.exit(0);
        });
        consoleThread.start();

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                synchronized (clients) {
                    if (clients.size() < MAX_CLIENTS) {
                        System.out.println("Клиент подключен: " + clientSocket.getInetAddress().getHostAddress());
                        ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                        clients.add(clientHandler);
                        Thread clientThread = new Thread(clientHandler);
                        clientThread.start();
                    } else {
                        System.out.println("Превышено максимальное количество клиентов");
                        clientSocket.close();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка сервера: " + e.getMessage());
        } finally {
            if (!serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}