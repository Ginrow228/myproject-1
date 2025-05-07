package lesson28;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String address;
    private List<ClientHandler> clients;

    public ClientHandler(Socket clientSocket, List<ClientHandler> clients) throws IOException {
        this.clientSocket = clientSocket;
        this.in = new DataInputStream(clientSocket.getInputStream());
        this.out = new DataOutputStream(clientSocket.getOutputStream());
        this.address = clientSocket.getInetAddress().getHostAddress();
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = in.readUTF();
                System.out.println("Получено от клиента " + address + ": " + message);

                if (!message.startsWith("Echo: ")) {
                    sendMessage("Echo: " + message);
                }
            }
        } catch (EOFException e) {
            System.out.println("Клиент " + address + " отключился");
        } catch (IOException e) {
            System.out.println("Ошибка прри работе с клиентом " + address + ": " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при отправке сообщения клиенту " + address + ": " + e.getMessage());
        }
    }

    private void disconnect() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null) clientSocket.close();

            synchronized (clients) {
                clients.remove(this);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при закрытии соединения с клиентом" + address + ": " + e.getMessage());
        }
    }
}