package com.flzj.tank.screen;

import javax.swing.*;
import java.awt.*;

public class TestPanel extends JPanel {


    /**
     * 画封面
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.drawImage(new ImageIcon("src/com/flzj/tank/imgs/Cover.png").getImage(), 75, 25, null);
        g.setFont(new Font("Serif", Font.BOLD, 180));
        g.setColor(Color.red);
        String title = "测试界面";
        g.drawString(title, 275, 300);

    }

}
