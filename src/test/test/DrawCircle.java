package java;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame{
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();

    }
    public DrawCircle(){
        mp = new MyPanel();
        //把面板放入到窗口(画框)
        this.add(mp);
        //设置窗口的大小
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel{
    //1.MyPanel 对象是一个画板
    //2.Graphics g 把 g 理解成画笔
    //3.Graphics 提供了很多绘图方法


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("paint 方法被调用了~");
        //画一个圆
        g.setColor(Color.CYAN);
        g.fillRect(10,10,15,75);
        g.fillRect(25,25,45,45);
        g.fillRect(70,10,15,75);
        g.setColor(Color.DARK_GRAY);
        g.fillOval(30,30,30,30);
        g.drawLine(45,0,45,50);
//        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/sy.jpg"));
//        g.drawImage(image,0,0,143,192,this);

    }
}