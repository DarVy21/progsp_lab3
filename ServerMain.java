import java.io.*;
import java.net.*;

public class ServerMain {
    static final int PORT = 4444;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Мультипоточный сервер стартовал");
        try {
            while (true) {
                Socket socket = s.accept();
                System.out.println("Новое соединение установлено");
                System.out.println("Данные клиента: "+
                        socket.getInetAddress());
                try {
                    System.out.println("Новое соединение установлено");
                    new ServerOne(socket);
                }
                catch (IOException e) {
                    socket.close();
                }
            }
        }
        finally {
            s.close();
        }
    }
}