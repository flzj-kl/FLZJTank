package com.flzj.tank.entity;


import com.flzj.tank.screen.GamePanel;

public class Shoot implements Runnable{
    private int x;
    private int y;
    private int direct;
    private int speed = 15;
    private boolean isLive = true;

    public Shoot(int x, int y,int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirect() {
        return direct;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        while(isLive && GamePanel.home.isLive()){
            switch (direct){
                case 0:     //上
                    y -= speed;
                    break;
                case 1:     //右
                    x += speed;
                    break;
                case 2:     //下
                    y += speed;
                    break;
                case 3:     //左
                    x -= speed;
                    break;
            }
            if(!(x >= 0 && x<= 1000 && y >= 0 && y <= 750 && isLive)){
                //System.out.println("子弹退出线程");
                isLive = false;
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("x="+x+" y="+y);
        }
    }
}
