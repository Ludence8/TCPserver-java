import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class testing extends JFrame implements ActionListener{
    private static Socket socket;
    private static OutputStream outputStream;
    private static DataOutputStream dos;
    JButton bt1;
    JTextField tx1;
    public testing() {
        super("JFrame 테스트 중");//상위 클래스의 생성자를 호출하여 윈도우 제목 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bt1 = new JButton("Hello");
        tx1 = new JTextField(20);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        c.add(tx1);
        c.add(bt1);
        bt1.addActionListener(this);
        setSize(300,200);
        setVisible(true);	//show();
        //대기
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }
        setVisible(false);
        try {
            dos.writeUTF("bye "+"[" +  getTime() + "]");
        } catch (IOException ee) {
            ee.getStackTrace();
        }

        System.out.println("종료됨");//창닫기
    }

    public static void main(String[] args) {
        try{
            socket = new Socket("localhost", 7777);
            System.out.println("Connected");
            while(true) {
                try {
                    outputStream = socket.getOutputStream();
                    dos = new DataOutputStream(outputStream);
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
                        testing ct = new testing();
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
    public void actionPerformed(ActionEvent ev) {
        String name = tx1.getText();

        if(ev.getSource() == bt1) {
            System.out.println("버튼이 눌림");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");
        return f.format(new Date());
    }
}