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
    JButton btnStop; //종료버튼
    JLabel[] jLabels = new JLabel[30];
    public static void main(String[] args) {
        Server server = new Server(15);
        server.start();

    }
    public Server(int num) {
        setTitle("컴퓨터 확인 서버");
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridBagLayout());
        int temp = 0;
        for(int i = 0; i < 30; i+=2) {
            jLabels[i] = new JLabel(Integer.toString(temp+1) + "번 : ");
            temp++;
        }
        for(int i = 1; i < 30; i+=2) {
            jLabels[i] = new JLabel("사용가능");
            jLabels[i].setForeground(Color.GREEN);
        }
        Container c = this.getContentPane();
        c.add(bottom);
        GridBagConstraints[] gbc = new GridBagConstraints[30];
        for(int i = 0; i < 30; i+=2) {
            gbc[i] = new GridBagConstraints();
            gbc[i+1] = new GridBagConstraints();
            gbc[i].gridx = 0;
            gbc[i+1].gridx = 1;
            gbc[i].gridy = i;
            gbc[i+1].gridy = i;
            gbc[i].gridwidth = 1;
            gbc[i].gridheight = 1;
            gbc[i+1].gridwidth = 1;
            gbc[i+1].gridheight = 1;
            bottom.add(jLabels[i], gbc[i]);
            bottom.add(jLabels[i+1], gbc[i+1]);
        }
        setBounds(300,400,300,400);
        setVisible(true);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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
                int num = Integer.parseInt(data.split(" ")[3]);

                if(data.split(" ")[0].equals("bye")) {
                    jLabels[num*2-1].setText("사용가능");
                    jLabels[num*2-1].setForeground(Color.GREEN);
                }
                else {
                    jLabels[num*2-1].setText("사용불가");
                    jLabels[num*2-1].setForeground(Color.RED);

                }

            } catch(IOException e) {
                e.getStackTrace();
            }
        }
    }
}

