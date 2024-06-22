package com.flzj.tank.common;

import com.flzj.tank.entity.*;
import com.flzj.tank.save.*;
import com.flzj.tank.screen.GamePanel;

import java.awt.*;
import java.util.Vector;

public class InitMap {

    // 从GamePanel获取到的字段值
    private static Hero hero = null;
    private static Vector<EnemyTank> enemyTanks = GamePanel.enemyTanks;
    private static Vector<Wall> walls = new Vector<>();
    private static Vector<Water> waters = new Vector<>();
    // 最大敌方坦克数量
    public static int enemyTankSize = 3;

    //定义一个存放TankNodes的泛型Vector 用于恢复敌我数据
    public static Vector<TankNode> tankNodes = null;
    private static boolean initialized;

    public static boolean isInitialized() {
        return initialized;
    }

    public static Hero getHero() {
        return hero;
    }

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static Vector<Wall> getWalls() {
        return walls;
    }

    public static Vector<Water> getWaters() {
        return waters;
    }

    public static void init(){
        hero = new Hero(14 * 25,26 * 25);   //初始化位置
        hero.setSpeed(5);




        //初始化敌方坦克
        for(int i = 0 ; i < enemyTankSize ; i++){
            // Recorder.setAllEnemyTank(0);
            //初始化位置
            EnemyTank enemyTank = new EnemyTank(100*(i+1),0);
            // 将enemyTanks 设置给 enemyTank
            enemyTank.setEnemyTanks(enemyTanks);
            // 将地图设置给 enemyTank
            enemyTank.setWalls(walls);
            enemyTank.setWaters(waters);
            //设置方向和速度
            enemyTank.setDirect(2);
            enemyTank.setSpeed(5);
            //启动敌人坦克线程
            new Thread(enemyTank).start();
            //子弹
            Shoot shoot = new Shoot(enemyTank.getX()+20, enemyTank.getY(), enemyTank.getDirect());
            enemyTank.shoots.add(shoot);
            //启动子弹线程
            new Thread(shoot).start();
            enemyTanks.add(enemyTank);
        }

        // 初始化基地(家)的墙
        walls.add(new Wall(18 * 25 ,29 * 25));      // 左
        walls.add(new Wall(18 * 25 , 28 * 25));
        walls.add(new Wall(18 * 25 , 27 * 25));
        walls.add(new Wall(18 * 25 , 26 * 25));     // 中
        walls.add(new Wall(19 * 25 , 26 * 25));
        walls.add(new Wall(20 * 25 , 26 * 25));
        walls.add(new Wall(21 * 25 , 26 * 25));
        walls.add(new Wall(22 * 25 , 26 * 25));
        walls.add(new Wall(22 * 25 ,29 * 25));      // 右
        walls.add(new Wall(22 * 25 , 28 * 25));
        walls.add(new Wall(22 * 25 , 27 * 25));

        // 画墙
        for(int x = 2 * 25,cnt = 0 ; x <= 36 * 25 ; x += 25 * 4)
            for(int y = 10 * 25 ; y <=  15 * 25 ; y += 25) {
                if(cnt == 2){
                    cnt = 0;
                    continue;
                }
                cnt++;
                walls.add(new Wall(x, y));
            }
        // 左边再次画墙
        for(int x = 0 * 25 ; x <= 11 * 25 ; x += 25)
            for(int y = 19 * 25 ; y <=  23 * 25 ; y += 25)
                walls.add(new Wall(x,y));
        // 右边再次画墙
        for(int x = 29 * 25 ; x <= 39 * 25 ; x += 25)
            for(int y = 19 * 25 ; y <=  23 * 25 ; y += 25)
                walls.add(new Wall(x,y));

        // 初始化水的位置
        for(int x = 12 * 25 ; x <= 28 * 25 ; x += 25)
            for(int y = 19 * 25 ; y <=  20 * 25 ; y += 25)
                waters.add(new Water(x,y));

        initialized = true;
    }


    public static void initBySave(){
        tankNodes = SaveTank.load();
        walls = SaveWall.load();
        waters = SaveWater.load();
        SaveRecode.load();
        GamePanel.killNum = SaveRecode.getKillScore();
        GamePanel.driverLevel = SaveRecode.getDriverLevel();

        // 初始化我方坦克
        TankNode tankNode = tankNodes.get(0);
        hero = new Hero(tankNode.getX(),tankNode.getY());
        hero.setSpeed(5);

        //初始化敌方坦克
        for(int i = 1 ; i < tankNodes.size() ; i++ ){
            TankNode node = tankNodes.get(i);
            //初始化位置
            EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY());
            //将enemyTanks 设置给 enemyTank!!
            enemyTank.setEnemyTanks(enemyTanks);
            enemyTank.setWalls(walls);
            enemyTank.setWaters(waters);
            //设置方向和速度
            enemyTank.setDirect(node.getDirect());
            enemyTank.setSpeed(10);
            //启动敌人坦克线程
            new Thread(enemyTank).start();
            //子弹
            Shoot shoot = new Shoot(enemyTank.getX()+20, enemyTank.getY(), enemyTank.getDirect());
            enemyTank.shoots.add(shoot);
            //启动子弹线程
            new Thread(shoot).start();
            enemyTanks.add(enemyTank);
        }

        initialized = true;
    }



}
