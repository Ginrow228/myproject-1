package lesson28;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try {
            Socket clientSocket = new Socket("localhost", 7);
            System.out.println("Соединение установлено");

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            Scanner sc = new Scanner(System.in);

            ServerMessageHandler serverMessageHandler = new ServerMessageHandler(in, out);
            Thread readThread = new Thread(serverMessageHandler);
            readThread.setDaemon(true);
            readThread.start();

            String clientInput;
            System.out.println("Введите сообщение для сервера или -q для выхода:");
            while (!(clientInput = sc.nextLine()).equals("-q")) {
                out.writeUTF(clientInput);
                out.flush();
                System.out.println("Сообщение отправлено");
            }

            System.out.println("Завершение работы клиента...");
            sc.close();
            clientSocket.close();

        } catch (ConnectException e) {
            System.out.println("Не удалось подключиться к серверу: сервер не запущен или недоступен");
        } catch (IOException e) {
            System.out.println("Ошибка при работе с сервером: " + e.getMessage());
        }
    }
}
