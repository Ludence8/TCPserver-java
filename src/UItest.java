import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UItest extends JFrame implements ActionListener{
    JButton btnStart; //보내기버튼
    JButton btnStop; //종료버튼
    JTextField tfName; //메세지 입력창
    JTextArea taMessage; //메세지 출력화면
    private static Socket socket;
    private static OutputStream outputStream;
    private static DataOutputStream dos;
    boolean temp = false;

    public UItest() { //UI 제작
        taMessage = new JTextArea();
        taMessage.setFont(new Font("맑은 고딕",Font.BOLD,15));
        taMessage.setEditable(false);
        JScrollPane scroll = new JScrollPane(taMessage);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  //항상 스크롤바가 세로로 떠있음

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        tfName = new JTextField();

        btnStart = new JButton("보내기");
        btnStop = new JButton("종료");
        btnStop.addActionListener(this);
        btnStart.addActionListener(this);

        bottom.add("Center",tfName);
        bottom.add("East",btnStart);

        Container c = this.getContentPane();
        c.add("Center", scroll);
        c.add("South", bottom);
        bottom.add("South",btnStop);
        //윈도우 창 설정
        setBounds(300,300,300,300);
        setVisible(true);

    }

    public static void main(String[] args) {
        /*
        try {
            socket = new Socket("localhost", 7777);
            System.out.println("연결되었습니다.");
        } catch (UnknownHostException e) {
            System.out.println("서버를 찾을 수 없습니다.");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("서버와 연결이 안되었습니다.");
            e.printStackTrace();
            System.exit(0);
        }
         */ //서버를 킬 경우 주석 삭제
        new UItest();
    }
    public void actionPerformed(ActionEvent e) {
        String name = tfName.getText();

        if(e.getSource() == btnStop) {
            System.exit(0);
        }
        else if(e.getSource() == btnStart) {
            if(name.equals("계속")) {
                setVisible(false);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                setVisible(true);
            }
            else {
                temp = true;
                taMessage.append(name+"\n");
                setVisible(false);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                setVisible(true);
            }
        }
    }


    static String getTime() {
        SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");
        return f.format(new Date());
    }
}
