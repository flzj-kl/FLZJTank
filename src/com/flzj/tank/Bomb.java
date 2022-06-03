package com.flzj.tank;

public class Bomb {
    private int x,y;
    private int life = 9;   //生命周期
    private boolean isLive = true;

    public int getLife() {
        return life;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown(){
        if(life > 0 ){
            life--;
        }else{
            isLive = false;
        }
    }

}
