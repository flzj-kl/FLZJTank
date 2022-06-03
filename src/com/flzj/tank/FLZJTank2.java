package com.flzj.tank;


import com.flzj.tank.screen.FirstPanel;
import com.flzj.tank.screen.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FLZJTank2 {
    private JFrame jFrame = null;
    private FirstPanel firstPanel = null;
    private MyPanel myPanel = null;
    private String choose = null;
    private TestPanel testPanel = new TestPanel();

    public static void main(String[] args) {
        FLZJTank2 flzjTank2 = new FLZJTank2();
    }

    public FLZJTank2(){
        // 框架的基本信息
        jFrame = new JFrame("坦克大战");
        jFrame.setSize(1300,950);
        jFrame.setIconImage(new ImageIcon("src/com/flzj/tank/imgs/FLZJTank.png").getImage());
        jFrame.setLocation(300,50);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);


        // 添加FirstPanel
        firstPanel = new FirstPanel();
        firstPanel.setLayout(null);

        // 添加按钮 和 点击事件
        JButton btn1 = firstPanel.getBtn1();
        JButton btn2 = firstPanel.getBtn2();


        // 添加监听
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn1.setVisible(false);
                btn2.setVisible(false);
                jFrame.remove(firstPanel);
                myPanel = new MyPanel("1");
                new Thread(myPanel).start();
                jFrame.add(myPanel);
                jFrame.addKeyListener(myPanel);
                jFrame.repaint();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn1.setVisible(false);
                btn2.setVisible(false);
                jFrame.remove(firstPanel);
                myPanel = new MyPanel("2");
                new Thread(myPanel).start();
                jFrame.add(myPanel);
                jFrame.addKeyListener(myPanel);
                jFrame.repaint();
            }
        });

        firstPanel.add(btn1);
        firstPanel.add(btn2);

        jFrame.add(firstPanel);

        // 框架设置为可见
        jFrame.setVisible(true);
        // 非常重要滴监听
        jFrame.setFocusable(true);


/*

        // 在JFrame 中增加相应关闭窗口的处理(用于退出自动存档)
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecode();
            }
        });

*/

    }



}

