import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client2 {
    private static final int PORT = 4444;

    public static void main(String[] args) throws IOException {
        try {

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(System.in));
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("Trying to connect: " + localHost);

            Socket socket = new Socket(localHost, PORT);
            System.out.println("Connected. Detail information: " + socket);

            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);

            System.out.println("Please enter word that you want to find at the server dictionary:");
            String word = reader.readLine();
            System.out.println("you've entered: "+word);
            while (!word.equals("quite")) {
                out.println(word);

                String str = in.readLine();
                System.out.println(str);
                System.out.println("---------------------------");
                word = reader.readLine();
                System.out.println("you've entered: "+word);
            }
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
