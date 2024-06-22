package com.flzj.tank.draw;

import java.awt.*;

public class DrawShoot {

    public static void draw(int x, int y, int width,int height,Graphics g){
        g.fill3DRect(x, y, width, height, false);
    }
}
