package carro;

import carro.Infomation;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;


/**
 *
 * @author Administrator
 */
public class Game {

    // Khai báo các biến
    private int count;
    public static JFrame f;
    JButton[][] bt;
    static boolean flag = false;
//    Timer thoigian;
    Integer second, minute;
//    JLabel demthoigian;
    JPanel p;
    JPanel pInfo;
    MenuBar menubar;
    int xx, yy, x, y;
    int[][] matran;
    int[][] matrandanh; // ma trận đánh chứa vị trí button đã đánh dùng để xử lý các hàm

    // Phần giao diện
    public Game() {
        count = 0;
        f = new JFrame();
        matrandanh = new int[20][20];
        f.setTitle("Caro Game");
        f.setSize(750, 500);
        x = 20;
        y = 20;
        f.getContentPane().setLayout(null);
        f.setResizable(false);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

         //Panel chứa các button
        p = new JPanel();
        p.setBounds(10, 30, 400, 400);
        p.setLayout(new GridLayout(x, y));
        p.setBackground(Color.red);
        f.add(p);

        
//         Bàn cờ caro
        bt = new JButton[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                final int a = i, b = j;
                bt[a][b] = new JButton();
                bt[a][b].setBackground(Color.LIGHT_GRAY);
                p.add(bt[a][b]);
                p.setVisible(false);
                p.setVisible(true);
                bt[a][b].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        flag = true;
                        matrandanh[a][b] = 1;
                        bt[a][b].setEnabled(false);
                        ImageIcon iconO = new ImageIcon("src/images/o.jpg");
                        bt[a][b].setBackground(Color.RED);
                        
//                        try {
//                            GameMessage gm = new GameMessage(9,new Cell(a,b));
//                            Client.socketHandle.write(gm);
////                            setEnableButton(false);
//                        } catch (IOException exception) {
//                            exception.printStackTrace();
//                        }
                    }
                });
            }
        }
        addPannelCon();
        addMenuBar();
        
    }
    private void addMenuBar(){
        //Menubar 
        menubar = new MenuBar();
        f.setMenuBar(menubar);
        Menu game = new Menu("Game");
        menubar.add(game);
        Menu help = new Menu("Help");
        menubar.add(help);
        MenuItem helpItem = new MenuItem("Help");
        help.add(helpItem);
        MenuItem about = new MenuItem("About");
        help.add(about);
        help.addSeparator();
        MenuItem newItem = new MenuItem("New game");
        game.add(newItem);
        MenuItem exitItem = new MenuItem("Exit");
        game.add(exitItem);
        game.addSeparator();

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nhom 1 - Caro ");
            }
        });
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private void addPannelCon(){
        pInfo = new JPanel();
        pInfo.setBounds(440, 30, 280, 450);
        pInfo.setLayout(new GridLayout(20, 20));
        
        // Tên người chơi
        JLabel tenNguoiChoi = new JLabel();
        tenNguoiChoi.setText("Tên người chơi:");
        pInfo.add(tenNguoiChoi);
        JLabel tenNguoiChoitext = new JLabel();
        tenNguoiChoitext.setText("Thịnh");
        pInfo.add(tenNguoiChoitext);
        
        // Số trận đã chơi
        JLabel soTran = new JLabel();
        soTran.setText("Số trận đã chơi:");
        pInfo.add(soTran);
        JLabel soTranText = new JLabel();
        soTranText.setText("10");
        pInfo.add(soTranText);
        
        // Số trận thắng
        JLabel soTranThang = new JLabel();
        soTranThang.setText("Số trận thắng:");
        pInfo.add(soTranThang);
        JLabel soTranThangText = new JLabel();
        soTranThangText.setText("3");
        pInfo.add(soTranThangText);
        
        // Số trận đã chơi
        JLabel soTranThua = new JLabel();
        soTranThua.setText("Số trận thua:");
        pInfo.add(soTranThua);
        JLabel soTranThuaText = new JLabel();
        soTranThuaText.setText("6");
        pInfo.add(soTranThuaText);
        
        // Số trận đã chơi
        JLabel soTranHoa = new JLabel();
        soTranHoa.setText("Số trận hòa:");
        pInfo.add(soTranHoa);
        JLabel soTranHoaText = new JLabel();
        soTranHoaText.setText("1");
        pInfo.add(soTranHoaText);

        
        f.add(pInfo);
        
    }

    public void setEnableButton(boolean b) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrandanh[i][j] == 0) {
                    bt[i][j].setEnabled(b);
                }
            }
        }
    }

    public void newgame() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                bt[i][j].setBackground(Color.LIGHT_GRAY);
                p.add(bt[i][j]);
                matran[i][j] = 0;
                matrandanh[i][j] = 0;
            }
        }
        p.setVisible(false);
        p.setVisible(true);
        setEnableButton(true);

    }
//
//    public void update(Cell cell) {
//        flag = true;
//        int a = cell.getX();
//        int b = cell.getY();
//        matrandanh[a][b] = 1;
//        bt[a][b].setEnabled(false);
//        bt[a][b].setBackground(Color.BLUE);
//        bt[a][b].setText("X");
//    }

    // Hàm xử lý caro
    public void caro(String x,String y){
        xx=Integer.parseInt(x);
        yy=Integer.parseInt(y);
        matran[xx][yy]=1;
        matrandanh[xx][yy]=1;
        bt[xx][yy].setEnabled(false);
        bt[xx][yy].setBackground(Color.BLUE);
        
        
    }
    
    public void setVisiblePane(JPanel pHienthi){
        p.add(pHienthi);
        pHienthi.setVisible(true);
        pHienthi.updateUI();
    }
    public void exit(){
        
        p.setVisible(false);
        f.setVisible(false);
        f.dispose();
    }
    
    public static void main(String[] args) {
        new Game();
    }

}
