package com.flzj.tank.draw;

import com.flzj.tank.save.SaveRecode;

import java.awt.*;

public class DrawInfo {
    private static int dKillNum;
    public static void score(Graphics g){
        dKillNum = SaveRecode.getKillScore();
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克",1020,30);
        DrawTank.drawByDirectAndType(1020,60,g,0,1);//画出一个敌方坦克
        g.setColor(Color.black);
        g.drawString(dKillNum+"",1080,100);
        g.drawString("当前驾驶员", 1020, 175);
    }



}
