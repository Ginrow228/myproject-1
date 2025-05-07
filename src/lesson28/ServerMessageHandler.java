package lesson28;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class ServerMessageHandler implements Runnable {

        private DataInputStream in;
        private DataOutputStream out;

        public ServerMessageHandler(DataInputStream in, DataOutputStream out) {
            this.in = in;
            this.out = out;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String serverResponse = in.readUTF();
                    System.out.println("Получено от сервера: " + serverResponse);
                    System.out.println("Введите сообщение для сервера или -q для выхода:");

                    if (!serverResponse.startsWith("Echo: ")) {
                        sendMessage("Echo: " + serverResponse);
                    }
                }
            } catch (EOFException e) {
                System.out.println("Сервер закрыл соединение");
            } catch (IOException e) {
                System.out.println("Ошибка при чтении с сервера: " + e.getMessage());
            }
        }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при отправке эхо-сообщения: " + e.getMessage());
        }
    }
}

