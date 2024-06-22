package com.flzj.tank.draw;

import java.awt.*;

public class DrawTank {
 /*
                坦克图
              10   20
            |---|  |  |---|
            |   |--|--|   |
        60  |   | |-| | 40|
            |   |-----|   |
            |---|     |---|
 */

    // 坦克朝上
    public static void drawUp(int x, int y, Graphics g) {
        g.fill3DRect(x,y,10,60,false);                      //左轮
        g.fill3DRect(x + 30,y,10,60,false);               //右轮
        g.fill3DRect(x + 10 , y +10 , 20 ,40 ,false);   //机身
        g.fillOval(x + 10 , y + 20 , 20 , 20);                //机盖
        g.drawLine(x + 20 , y  , x + 20 , y + 40);                  //炮口
    }
    // 坦克朝下
    public static void drawDown(int x, int y, Graphics g) {
        g.fill3DRect(x,y,10,60,false);//左轮
        g.fill3DRect(x + 30,y,10,60,false);//右轮
        g.fill3DRect(x + 10 , y +10 , 20 ,40 ,false);//机身
        g.fillOval(x + 10 , y + 20 , 20 , 20);  //机盖
        g.drawLine(x + 20 , y + 60  , x + 20 , y + 40);
    }
    // 坦克朝左
    public static void drawLeft(int x, int y, Graphics g) {
        g.fill3DRect(x,y,60,10,false);//上轮
        g.fill3DRect(x,y + 30,60,10,false);//下轮
        g.fill3DRect(x + 10 , y +10 , 40 ,20 ,false);//机身
        g.fillOval(x + 20 , y + 10 , 20 , 20);  //机盖
        g.drawLine(x + 30 , y + 20  , x  , y + 20);       //炮口
    }
    // 坦克朝右
    public static void drawRight(int x, int y, Graphics g) {
        g.fill3DRect(x,y,60,10,false);//上轮
        g.fill3DRect(x,y + 30,60,10,false);//下轮
        g.fill3DRect(x + 10 , y +10 , 40 ,20 ,false);//机身
        g.fillOval(x + 20 , y + 10 , 20 , 20);  //机盖
        g.drawLine(x + 30 , y + 20  , x + 60 , y + 20);
    }

    // 设置己方坦克颜色
    public static void setHeroColor(Graphics g){
        g.setColor(Color.cyan);
    }

    // 设置敌方坦克颜色
    public static void setEnemyColor(Graphics g){
        g.setColor(Color.yellow);
    }

    /**
     * 根据位置画坦克
     * @param x
     * @param y
     * @param g
     * @param direct  0 ↑    1 →    2  ↓   3  ←
     */
    public static void drawByDirect(int x,int y,Graphics g,int direct){
        switch(direct){
            case 0:
                DrawTank.drawUp(x,y,g);
                break;
            case 1:
                DrawTank.drawRight(x,y,g);
                break;
            case 2:
                DrawTank.drawDown(x,y,g);
                break;
            case 3:
                DrawTank.drawLeft(x,y,g);
                break;
        }
    }

    /**
     *
     * @param g
     * @param type 0 己方坦克  1 敌方坦克
     */
    public static void setByType(Graphics g,int type){
        switch (type){
            case 0: //自己
                DrawTank.setHeroColor(g);
                break;
            case 1: //敌人
                DrawTank.setEnemyColor(g);
                break;
        }
    }


    //
    public static void drawByDirectAndType(int x,int y,Graphics g,int direct,int type){
        setByType(g,type);
        drawByDirect(x,y,g,direct);
    }







}
