import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

class ServerOne extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;


    public ServerOne(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream())), true);
        start();
    }
    public void run() {
        try {

            String str = in.readLine();

            while (!str.equals("quite")) {

                System.out.println("Need to find: " + str);
                String sentences = findSentences(str);

                out.println("From server: "+sentences);
                str = in.readLine();
            }
            System.out.println("Соединение закрыто");
        }
        catch (IOException e) {
            System.err.println("Все, все ушли");
        }
        finally {
            try {
                socket.close();
            }
            catch (IOException e) {
                System.err.println("Сокет не закрыт");
            }
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
