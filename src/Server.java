import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements Runnable, ActionListener {
    ServerSocket serverSocket;
    Thread[] threadArr;
    JTextArea taMessage; //메세지 출력화면
    JButton btnStop; //종료버튼
    File file = new File("testnum.txt");
    String name = "";
    int num1 = 0;
    public static void main(String[] args) {
        Server server = new Server(15);
        server.start();

    }
    public Server(int num) {
        setTitle("컴퓨터 확인 서버");
        taMessage = new JTextArea();
        taMessage.setFont(new Font("맑은 고딕",Font.BOLD,15));
        taMessage.setEditable(false);
        JScrollPane scroll = new JScrollPane(taMessage);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  //항상 스크롤바가 세로로 떠있음

        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        btnStop = new JButton("종료");
        btnStop.addActionListener(this);

        Container c = this.getContentPane();
        c.add("Center", scroll);
        c.add("South", bottom);
        bottom.add("South",btnStop);

        setBounds(300,300,300,300);
        setVisible(true);
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        num1 = num;
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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnStop) {
            try {
                serverSocket.close();
            } catch (IOException ee) {
                ee.getStackTrace();
            }
            System.exit(0);
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
                    taMessage.append(data.split(" ")[1] + " " + data.split(" ")[2]+" 사용종료\n");
                    pw.flush();
                    pw.close();
                }
                else {
                    System.out.println(data+ " => 입장");
                    pw.write(data+ " => 입장\n");
                    taMessage.append(data+" 사용시작\n");
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

