package com.flzj.tank.judging;


import com.flzj.tank.Recorder;
import com.flzj.tank.entity.*;
import com.flzj.tank.save.SaveRecode;
import com.flzj.tank.screen.GamePanel;

import java.util.Vector;

public class Hit {

    private static Hero hero = null;
    private static Vector<Bomb> bombs = null;
    private static Vector<EnemyTank> enemyTanks = null;
    private static Vector<Wall> walls = null;


    // 用于填充引用GamePanel的数据
    public static void setDetail(){
        hero = GamePanel.hero;
        bombs  = GamePanel.bombs;
        enemyTanks = GamePanel.enemyTanks;
        walls = GamePanel.walls;
    }


    // 编写方法，判断子弹是否击中敌人
    public static void hitTank(Shoot s, EnemyTank enemyTank){
        setDetail();
        //判断玩家击中敌方坦克
        switch (enemyTank.getDirect()){
            case 0: //坦克姿势为向上向下
            case 2:
                if(s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX()+40
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() +60){
                    s.setLive(false);
                    enemyTank.setLive(false);
                    //分数++
                    SaveRecode.killScoreAdd();
                    //Recorder.addAllEnemyTank();
                    //hero.heroShoots.remove(s);
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(enemyTank);
                    hero.heroShoots.remove(s);
                    break;
                }
            case 1://坦克姿势为向左向右
            case 3:
                if(s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX()+60
                        && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() +40) {
                    s.setLive(false);
                    enemyTank.setLive(false);
                    //分数++
                    SaveRecode.killScoreAdd();
                    //Recorder.addAllEnemyTank();
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(enemyTank);
                    break;
                }
        }
    }

    // 判断敌方击中玩家坦克
    public static void hitHero(Shoot s){
        setDetail();

        switch (hero.getDirect()){
            case 0: //坦克姿势为向上向下
            case 2:
                if(s.getX() > hero.getX() && s.getX() < hero.getX()+40
                        && s.getY() > hero.getY() && s.getY() < hero.getY() +60){
                    s.setLive(false);
                    hero.setHeroLive(false);
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
                    bombs.add(bomb);
                    break;
                }
            case 1://坦克姿势为向左向右
            case 3:
                if(s.getX() > hero.getX() && s.getX() < hero.getX()+60
                        && s.getY() > hero.getY() && s.getY() < hero.getY() +40) {
                    s.setLive(false);
                    hero.setHeroLive(false);
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(hero.getX(), hero.getY());
                    bombs.add(bomb);
                    break;
                }
        }
    }

    // 判断坦克是否击中墙
    public static boolean hitWall(Shoot s, Wall w){
        setDetail();
        // 0和2 子弹上下 1和3 子弹左右
        switch (s.getDirect()){
            case 0:
            case 2:
                if(s.getX() >= w.getX() && s.getX() <= w.getX() + 25
                        && s.getY() >= w.getY() && s.getY() <= w.getY() + 25) {
                    s.setLive(false);
                    w.setLive(false);
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(w.getX(), w.getY());
                    bombs.add(bomb);
                    walls.remove(w);
                    return true;
                }
            case 1:
            case 3:
                if(s.getY() >= w.getY() && s.getY() <= w.getY() + 25
                        && s.getX() >= w.getX() && s.getX() <= w.getX() + 25) {
                    s.setLive(false);
                    w.setLive(false);
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(w.getX(), w.getY());
                    bombs.add(bomb);
                    walls.remove(w);
                    return true;
                }
        }
        return false;
    }

    // 判断是否打中基地(家)
    public static boolean hitHome(Shoot s){
        setDetail();
        int homeX = 19 * 25;
        int homeY = 27 * 25;
        // 0和2 子弹上下 1和3 子弹左右
        switch (s.getDirect()){
            case 0:
            case 2:
                if(s.getX() >= homeX && s.getX() <= homeX + 75
                        && s.getY() >= homeY && s.getY() <= homeY + 75) {
                    s.setLive(false);
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(homeX, homeY);
                    bombs.add(bomb);
                    GamePanel.homeImg = GamePanel.homeDImg;
                    return true;
                }
            case 1:
            case 3:
                if(s.getY() >= homeY && s.getY() <= homeY + 75
                        && s.getX() >= homeX && s.getX() <= homeX + 75) {
                    s.setLive(false);
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(homeX, homeY);
                    bombs.add(bomb);
                    GamePanel.homeImg = GamePanel.homeDImg;
                    return true;
                }
        }
        return false;
    }


}
