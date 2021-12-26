import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerTest implements Runnable {
    ServerSocket serverSocket;
    Thread[] threadArr;
    File file = new File("testnum.txt");
    String name = "";
    public static void main(String[] args) {
        ServerTest server = new ServerTest(15);
        server.start();
    }
    public ServerTest(int num) {
        try {
            serverSocket = new ServerSocket(7777);
            threadArr = new Thread[num];
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    public void start() {
        for(int i = 0; i <threadArr.length; i++) {
            threadArr[i] = new Thread(this);
            threadArr[i].start();
        }
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        }catch (IOException e) {
            e.getStackTrace();
        }
        while(true) {
            try {
                InputStream in = socket.getInputStream();
                DataInputStream dim = new DataInputStream(in);
                String data = dim.readUTF();
                BufferedWriter bw = new BufferedWriter(new FileWriter("testnum.txt", true));
                PrintWriter pw = new PrintWriter(bw, true);

                if(data.split(" ")[0].equals("bye")) {
                    System.out.println(data.split(" ")[1]+" " +name + " => 퇴장");
                    pw.write(data.split(" ")[1]+" " +name + " => 퇴장\n");
                    pw.flush();
                    pw.close();
                }
                else {
                    System.out.println(data+ " => 입장");
                    pw.write(data+ " => 입장\n");
                    name = data.split(" ")[1];
                    pw.flush();
                    pw.close();
                }

            } catch(IOException e) {
                e.getStackTrace();
            }
        }
    }
}

