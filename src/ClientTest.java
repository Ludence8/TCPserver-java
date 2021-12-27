import javax.swing.*;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClientTest {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 7777);
            System.out.println("Connected");
            while(true) {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(outputStream);
                    String a = JOptionPane.showInputDialog("이름을 입력해주세요!");
                    int blank = 0;
                    for(int i = 0; i < a.length(); i++) {
                        if(a.charAt(i) == ' ') {++blank;}
                    }
                    if(a.equals("end")) {
                        dos.writeUTF("bye "+"[" +  getTime() + "]");
                        socket.close();
                        break;
                    }
                    if(blank == 0) {
                        dos.writeUTF("[" +  getTime() + "] " + a);
                        TimeUnit.SECONDS.sleep(3);
                    }
                    else {
                        System.out.println("입력형식이 잘못되었습니다.");
                    }
                } catch (IOException e) {
                    e.getStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");
        return f.format(new Date());
    }
}
