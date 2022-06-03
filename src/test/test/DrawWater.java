import javax.swing.*;
import java.awt.*;

public class DrawWater extends JFrame{
    public static void main(String[] args) {
        DrawWater drawWater = new DrawWater();
    }
    public DrawWater(){
        this.add(new mp02());
        //设置窗口的大小
        this.setSize(1350,950);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class mp02 extends JPanel{
    @Override
    public void paint(Graphics g) {
        g.fillRect(0,0,1000,750);//填充矩形 默认黑色
        // Water
        g.setColor(Color.cyan);
        g.fill3DRect(100,100,25,25,true);
        g.fill3DRect(125,100,25,25,false);
        g.setColor(Color.BLUE);
        g.fill3DRect(100,125,25,25,true);
        g.fill3DRect(125,125,25,25,false);

        g.setColor(new Color(0,191,255));
        for(int x = 200 ; x <= 800 ; x += 25){
            for(int y = 200 ; y <= 225 ; y += 25)
                g.fill3DRect(x,y,25,25,false);
        }

    }

}