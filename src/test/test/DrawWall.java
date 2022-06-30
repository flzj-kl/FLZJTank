

import javax.swing.*;
import java.awt.*;

public class DrawWall extends JFrame{
    public static void main(String[] args) {
        DrawWall drawWall = new DrawWall();
    }
    public DrawWall(){
        this.add(new mp01());
        //设置窗口的大小
        this.setSize(1350,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class mp01 extends JPanel{
    @Override
    public void paint(Graphics g) {
        // 普通的墙
/*        g.fillRect(0,0,1000,750);//填充矩形 默认黑色
        g.setColor(new Color(118,77,57));*/

        // 50 * 50 的墙
/*        g.fill3DRect(100,100,50,25,true);
        g.fill3DRect(100,125,25,25,true);
        g.fill3DRect(125,125,25,25,true);*/

        // 25 * 25 的墙
/*        for(int x = 0 ; x < 1000 ; x += 25) {
            for(int y = 0 ; y < 750 ; y += 25) {
                if(x == y ) continue;
                g.fill3DRect(x, y,  25, 13, true);
                g.fill3DRect(x, y + 13, 12, 12, true);
                g.fill3DRect(x + 12, y + 13, 13, 12, true);
            }
        }*/
        // 画坦克
/*        int x = 200,y = 100;
        g.fill3DRect(x,y,10,60,false);//左轮
        g.fill3DRect(x + 30,y,10,60,false);//右轮
        g.fill3DRect(x + 10 , y +10 , 20 ,40 ,false);//机身
        g.fillOval(x + 10 , y + 20 , 20 , 20);  //机盖
        g.drawLine(x + 20 , y  , x + 20 , y + 40);       //炮口*/

/*        // Iron wall
        g.setColor(new Color(176, 176, 176));
        g.fill3DRect(100,100,25,25,true);
        g.setColor(new Color(255, 255, 255));
        g.fill3DRect(110,110,12,13,true);*/



    }

}