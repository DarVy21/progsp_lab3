import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Server {
    private static final int PORT = 4444;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server initiation started: " + serverSocket);

            socket = serverSocket.accept();
            System.out.println("Connection accepted: " + socket);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            String str = in.readLine();

            while (!str.equals("quite")) {

                System.out.println("Need to find: " + str);
                String sentences = findSentences(str);

                out.println("From server: "+sentences);
                str = in.readLine();
            }

            System.out.println("closing ...");
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String findSentences(String word) throws IOException {
        File file = new File("README.txt");
        if (!file.exists())
            file.createNewFile();

        StringBuffer outBuff = new StringBuffer();
        BufferedReader buff = new BufferedReader(new FileReader("README.txt"));
        int count=0;
        while(buff.ready()) {

            StringTokenizer tokenizer = new StringTokenizer(buff.readLine(), ".!;?");
            while(tokenizer.hasMoreTokens()) {
                String s = tokenizer.nextToken();
                if(s.contains(word)) {
                    outBuff.append(s );
                    count++;
                }
            }
        }
        if (count==0){
            outBuff.append("ничего не найдено");
        }
        buff.close();


        return outBuff.toString();
    }

}


