package com.flzj.tank.draw;

import java.awt.*;

public class DrawWater {
    /*
            |----|
            |    | 25
            |----|
              25
     */

    // 正常画水
    public static void draw(int x,int y,Graphics g){
        g.setColor(new Color(0, 191, 255));
        g.fill3DRect(x, y, 25, 25, false);
    }




}
