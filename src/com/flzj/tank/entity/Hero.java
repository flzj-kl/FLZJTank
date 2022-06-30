package com.flzj.tank.entity;

import java.util.Vector;

public class Hero extends Tank {
    public Shoot shoot = null;
    public Vector<Shoot> heroShoots = new Vector<>();
    private static final int MAX_AMMO_NUM = 5;
    private boolean isHeroLive = true;

    public Hero(int x, int y) {
        super(x, y);
    }

    public boolean isHeroLive() {
        return isHeroLive;
    }

    public void setHeroLive(boolean heroLive) {
        isHeroLive = heroLive;
    }

    //    旧版只能射一发
//    public void shootEnemy(){
//        switch (getDirect()){
//            case 0:
//                shoot = new Shoot(getX()+20 , getY(),0);
//                break;
//            case 1:
//                shoot = new Shoot(getX()+60,getY()+20,1);
//                break;
//            case 2:
//                shoot = new Shoot(getX()+20,getY()+60,2);
//                break;
//            case 3:
//                shoot = new Shoot(getX(),getY()+20,3);
//                break;
//        }
    public void shootEnemy(){
        switch (getDirect()){
            case 0:
                shoot = new Shoot(getX()+20 , getY(),0);
                break;
            case 1:
                shoot = new Shoot(getX()+60,getY()+20,1);
                break;
            case 2:
                shoot = new Shoot(getX()+20,getY()+60,2);
                break;
            case 3:
                shoot = new Shoot(getX(),getY()+20,3);
                break;
        }
        if(heroShoots.size() < MAX_AMMO_NUM) {
            heroShoots.add(shoot);
            Thread thread = new Thread(shoot);
            thread.start();
        }else{
            System.out.println("子弹只能射出" + MAX_AMMO_NUM + "发");
            return;
        }


    }
}
