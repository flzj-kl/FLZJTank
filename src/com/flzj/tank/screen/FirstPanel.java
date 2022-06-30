package com.flzj.tank.screen;

import com.flzj.tank.FLZJTankFrame;

import com.flzj.tank.draw.DrawTank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPanel extends JPanel {
    // 按钮罢了
    private JButton btn1 = new JButton("开始游戏");
    private JButton btn2 = new JButton("继续游戏");

    // 从FLZJTank获取的属性
    private JFrame jFrame = FLZJTankFrame.jFrame;
    private GamePanel gamePanel = null;

    // 初始代码块，用于给按钮加上监听器和给组件加上按钮
    {
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn1.setVisible(false);
                btn2.setVisible(false);
                FirstPanel firstPanel = FLZJTankFrame.firstPanel;
                jFrame.remove(firstPanel);
                gamePanel = new GamePanel("1");
                Thread myPanelThread = new Thread(gamePanel);
                myPanelThread.start();
                jFrame.add(gamePanel);
                jFrame.addKeyListener(gamePanel);
                // jFrame.remove(myPanel);
                // jFrame.removeKeyListener(myPanel);
                jFrame.repaint();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn1.setVisible(false);
                btn2.setVisible(false);
                FirstPanel firstPanel = FLZJTankFrame.firstPanel;
                jFrame.remove(firstPanel);
                gamePanel = new GamePanel("2");
                new Thread(gamePanel).start();
                jFrame.add(gamePanel);
                jFrame.addKeyListener(gamePanel);
                jFrame.repaint();
            }
        });
    }


    /**
     * 画封面
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // 背景图片
        g.drawImage(new ImageIcon("src/com/flzj/tank/imgs/Cover.png").getImage(),75,25,null);
        g.setFont(new Font("Serif",Font.BOLD,180));
        g.setColor(Color.red);
        String title = "负离子价坦克";
        g.drawString(title,75,300);

        // 画坦克作为背景
        int x = 350,y = 500;
        DrawTank.drawRight(x,y,g);
        x = 300;
        y = 75;
        g.setColor(Color.yellow);
        for(int i = x ; i < 1000 ; i += 80)
            DrawTank.drawUp(i,y,g);

        // 画按钮
        btn1.setFont(new Font("宋体",Font.BOLD,50));
        btn2.setFont(new Font("宋体",Font.BOLD,50));
        btn1.setBounds(450,450,400,150);
        btn2.setBounds(450,625,400,150);
        btn1.setBackground(new Color(237,51,51));
        btn2.requestFocus();
        btn1.requestFocus();
    }

    // 获取按钮
    public JButton getBtn1() {
        return btn1;
    }
    public JButton getBtn2() {
        return btn2;
    }
}
