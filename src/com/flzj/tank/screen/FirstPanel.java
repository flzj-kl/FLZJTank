package com.flzj.tank.screen;

import com.flzj.tank.FLZJTank2;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FirstPanel extends JPanel {

    private JButton btn1 = new JButton("开始游戏");
    private JButton btn2 = new JButton("继续游戏");

    /**
     * 画封面
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(new ImageIcon("src/com/flzj/tank/imgs/Cover.png").getImage(),75,25,null);
        g.setFont(new Font("Serif",Font.BOLD,180));
        g.setColor(Color.red);
        String title = "坦克大战";
        g.drawString(title,275,300);

        int x = 400,y = 500;
        drawTank(x,y,g);
        x = 300;
        y = 75;
        g.setColor(Color.yellow);
        for(int i = x ; i < 1000 ; i += 80)
            drawTank(i,y,g);

        // 设置按钮
        btn1.setFont(new Font("宋体",Font.BOLD,50));
        btn2.setFont(new Font("宋体",Font.BOLD,50));
        btn1.setBounds(450,450,400,150);
        btn2.setBounds(450,625,400,150);
        btn1.setBackground(new Color(237,51,51));
        btn2.requestFocus();
        btn1.requestFocus();
    }
    public JButton getBtn1() {
        return btn1;
    }

    public JButton getBtn2() {
        return btn2;
    }

    // 画坦克
    public void drawTank(int x,int y,Graphics g) {
        g.fill3DRect(x,y,10,60,false);//左轮
        g.fill3DRect(x + 30,y,10,60,false);//右轮
        g.fill3DRect(x + 10 , y +10 , 20 ,40 ,false);//机身
        g.fillOval(x + 10 , y + 20 , 20 , 20);  //机盖
        g.drawLine(x + 20 , y  , x + 20 , y + 40);       //炮口*/
    }
}
