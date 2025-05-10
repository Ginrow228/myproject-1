package lesson25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("localhost", 7);
        System.out.println("Соединение установлено");

        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        Scanner sc = new Scanner(System.in);

        Thread readThread = new Thread(() -> {
            try {
                while (true) {
                    try {
                        String serverResponse = in.readUTF();
                        System.out.println("Получено сообщение от сервера: " + serverResponse);
                        if(!serverResponse.startsWith("Echo: ")) {
                            out.writeUTF("Echo: " + serverResponse);
                            out.flush();
                        }
                    } catch (EOFException e) {
                        System.out.println("Сервер потерял соединение");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Соединение с сервером потеряно: " + e.getMessage());
            }
        });
        readThread.start();

        System.out.println("Введите сообщение для клиента или введите -q для завершения работы: ");
        String clientInput;
        while (!(clientInput = sc.nextLine()).equals("-q")) {
            out.writeUTF(clientInput);
            out.flush();
            System.out.println("Сообщение отправлено");
        }

        System.out.println("Завершение работы клиента...");
        sc.close();
        in.close();
        out.close();
        clientSocket.close();

    }
}
