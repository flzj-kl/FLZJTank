package ballmove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ball extends JFrame{
    MyPanel mp = null;
    public static void main(String[] args) {
        Ball ball = new Ball();

    }
    public Ball(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel implements KeyListener {
    private int x = 10;
    private int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x,y,30,30);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y+=10;
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y-=10;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x-=10;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x+=10;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}