package com.flzj.tank.draw;

import java.awt.*;

public class DrawWall {

    public static void draw(int x, int y, Graphics g){
        g.setColor(new Color(118, 77, 57));
        g.fill3DRect(x, y, 25, 13, true);
        g.fill3DRect(x, y + 13, 12, 12, true);
        g.fill3DRect(x + 12, y + 13, 13, 12, true);
    }



}
