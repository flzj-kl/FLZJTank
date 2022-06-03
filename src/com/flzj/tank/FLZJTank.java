package com.flzj.tank;



import com.flzj.tank.screen.FirstPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class FLZJTank extends JFrame {
    MyPanel mp = null;
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        FLZJTank flzjTank = new FLZJTank();
    }

    public FLZJTank(){
        System.out.println("请输入选择 1 : 新游戏 2 :继续游戏");
        String key = sc.next();
        mp = new MyPanel(key);
        this.add(mp);
        Thread thread = new Thread(mp);
        thread.start();

        this.setTitle("负离子价坦克");
        this.setSize(1300,950);
        this.setIconImage(new ImageIcon("src/com/flzj/tank/imgs/FLZJTank.png").getImage());
        this.setLocation(300,50);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecode();
            }
        });
    }
}
