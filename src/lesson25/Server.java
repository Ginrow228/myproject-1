package lesson25;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(7);
        System.out.println("Ожидаем подключения...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Клиент успешно подключен: " + clientSocket.getInetAddress().getHostAddress());

        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        Scanner sc = new Scanner(System.in);
        Thread readThread = new Thread(() -> {
            try {
                while (true) {
                    try {
                        String inputLine = in.readUTF();
                        System.out.println("Получено сообщение от клиента: " + inputLine);
                        if(!inputLine.startsWith("Echo: ")) {
                            out.writeUTF("Echo: " + inputLine);
                            out.flush();
                        }
                    } catch (EOFException e) {
                        System.out.println("Клиент потерял соединение");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Потеряно соединение с клиентом: " + e.getMessage());
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

        System.out.println("Завершение работы...");
        sc.close();
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();

    }
}
