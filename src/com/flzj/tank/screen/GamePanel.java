package com.flzj.tank.screen;

import com.flzj.tank.FLZJTankFrame;

import com.flzj.tank.Recorder;
import com.flzj.tank.common.InitMap;
import com.flzj.tank.draw.*;
import com.flzj.tank.entity.*;
import com.flzj.tank.judging.Hit;
import com.flzj.tank.judging.Touch;
import com.flzj.tank.save.SaveRecode;
import com.flzj.tank.save.SaveTank;
import com.flzj.tank.save.TankNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class GamePanel extends JPanel implements Runnable,KeyListener {
        //定义一个存放TankNodes的泛型Vector 用于恢复敌我数据
        public static Vector<TankNode> tankNodes = null;
        //定义我的坦克
        public static Hero hero = null;
        // 定义家对象
        public static Home home = new Home(19 * 25, 27 * 25);
        //定义敌方坦克放入到vector中
        public static Vector<EnemyTank>enemyTanks = new Vector<>();
        // 最大坦克数量
        //当子弹击中坦克时，加入一个bomb对象到vector
        public static Vector<Bomb>  bombs = new Vector<>();
        // 定义一个墙容器
        public static Vector<Wall> walls = new Vector<>();
        // 定义一个水容器
        public static Vector<Water> waters = new Vector<>();
        // 定义家的图片
        public static Image homeImg = null;
        // 定义家被摧毁图片
        public static Image homeDImg = null;
        // 定义驾驶员图片
        Image ding = null;
        Image gu = null;
        Image he = null;
        //定义三张炸弹图片
        Image image1 = null;
        Image image2 = null;
        Image image3 = null;

        // 升级系统
        int levelUpKill2 = 1;
        int levelUpKill3 = 2;
        public static int driverLevel;
        public static int killNum;


        public GamePanel(String key){
            // 根据选择初始化地图
            switch (key){
                default:
                    System.out.println("您输入的值不对,正在您重开。。。");
                case "1":
                    //初始化我方坦克
                    InitMap.init();
                    driverLevel = 1;
                    killNum = 0;
                    break;
                case "2":
                    InitMap.initBySave();
                    driverLevel = SaveRecode.getDriverLevel();
                    killNum = SaveRecode.getKillScore();
                    break;
            }
            hero = InitMap.getHero();
            SaveTank.setEnemyTanks(enemyTanks);
            walls = InitMap.getWalls();
            waters = InitMap.getWaters();


            //初始化爆炸图片
            image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/bomb_1.gif"));
            image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/bomb_2.gif"));
            image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/bomb_3.gif"));

            // 初始化家图片 "/com/flzj/tank/imgs/Home.gif"
            homeImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/home.gif"));
            // 初始化家被摧毁图片
            homeDImg = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/destroyH.gif"));

            // 初始化驾驶员图片
            ding = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/ding.jpg"));
            gu = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/Gu.jpg"));
            he = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/com/flzj/tank/imgs/He.jpg"));

            //初始化敌方坦克的容器
            //Recorder.setEnemyTanks(enemyTanks);

        }
        //编写方法，显示我方击毁敌方坦克的信息
        public void showInfo(Graphics g){
            DrawInfo.score(g);
            killNum = SaveRecode.getKillScore();
            // 画出当前驾驶员
            if(driverLevel == 1) {
                g.setColor(Color.PINK);
                g.drawImage(ding, 1050, 200, 150, 150, this);
                g.drawString("坦克丁真", 1060, 375);
                g.setColor(Color.black);
                g.drawString("当前车速: " + hero.getSpeed(), 1060, 400);
                g.drawString("当前子弹速: " + 15, 1060, 425);
                // 升级还需要
                if(levelUpKill2 - killNum > 0) {
                    g.drawString("升级还需击杀: " + (levelUpKill2 - killNum) + "个敌人", 1060, 450);
                }else{
                    driverLevel = 2;
                    hero.setSpeed(10);
                }
            }
            if(driverLevel == 2){
                g.setColor(Color.PINK);
                g.drawImage(gu, 1050, 200, 150, 150, this);
                g.drawString("GU20", 1060, 375);
                g.setColor(Color.black);
                g.drawString("当前车速: " + hero.getSpeed(), 1060, 400);
                g.drawString("当前子弹速: " + 15, 1060, 425);
                // 升级还需要
                if(levelUpKill3 - killNum > 0) {
                    g.drawString("升级还需击杀: " + (levelUpKill3 - killNum) + "个敌人", 1060, 450);
                }else{
                    driverLevel = 3;
                    hero.setSpeed(15);
                }
            }
            if(driverLevel == 3){
                g.setColor(Color.PINK);
                g.drawImage(he, 1050, 200, 150, 150, this);
                g.drawString("何同学", 1060, 375);
                g.setColor(Color.black);
                g.drawString("当前车速: " + hero.getSpeed(), 1060, 400);
                g.drawString("当前子弹速: " + 15, 1060, 425);
            }

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.fillRect(0,0,1000,750);//填充矩形 默认黑色
            // 如果已经初始化了
            //画面面板
            showInfo(g);
            // 如果家没被爆了
            if (home.isLive() && InitMap.isInitialized()) {
            // 画已方坦克(被敌方射中死亡)
            if (hero.isHeroLive()) {
                DrawTank.drawByDirectAndType(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
            } else {
                hero.setX(14 * 25);
                hero.setY(26 * 25);
                hero.setHeroLive(true);
                System.out.println("您已死亡重新开始");
            }

            //玩家连续射击版 画子弹*
            for (int i = 0; i < hero.heroShoots.size(); i++) {
                Shoot shoot = hero.heroShoots.get(i);
                if (shoot != null && shoot.isLive() == true) {
                    DrawShoot.draw(shoot.getX(), shoot.getY(), 3, 3, g);
                } else {//如果shoot已经无效，（下面已经在攻击敌人时写删除了，所以这个是越界删除）
                    hero.heroShoots.remove(shoot);
                }
            }

            // 画敌方坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                //从vector容器取出的对象
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive()) {//当敌人坦克存活时才画
                    DrawTank.drawByDirectAndType(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                    for (int j = 0; j < enemyTank.shoots.size(); j++) {
                        //取出坦克子弹
                        Shoot shoot = enemyTank.shoots.get(j);
                        //绘制77
                        if (shoot.isLive()) {
                            DrawShoot.draw(shoot.getX(), shoot.getY(), 3, 3, g);
                        } else {  //移除
                            enemyTank.shoots.remove(shoot);
                        }
                    }
                }
            }

            // 画墙
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (wall.isLive()) {
                    int x = wall.getX(), y = wall.getY();
                    DrawWall.draw(x, y, g);
                }
            }

            // 画河流
            for (int i = 0; i < waters.size(); i++) {
                Water water = waters.get(i);
                int x = water.getX(), y = water.getY();
                DrawWater.draw(x, y, g);
            }

            // 画基地图片
            g.drawImage(homeImg, 19 * 25, 27 * 25, 75, 75, this);

            // 画出爆炸效果(如果集合中有炸弹就要画)
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = bombs.get(i);
                if (bomb.getLife() > 6) {
                    g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
                } else if (bomb.getLife() > 3) {
                    g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
                } else {
                    g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
                }
                //画完后让生命周期减少
                bomb.lifeDown();
                if (bomb.getLife() == 0) {
                    bombs.remove(bomb);
                }
            }

        }else if(SaveRecode.getKillScore() == InitMap.enemyTankSize){
            g.setFont(new Font("Serif", Font.BOLD, 180));
            g.setColor(Color.red);
            String title = "成功";
            g.drawString(title, 10 * 25, 10 * 25);
            JFrame jFrame = FLZJTankFrame.jFrame;
            jFrame.repaint();
        }else {
            g.setFont(new Font("Serif", Font.BOLD, 180));
            g.setColor(Color.red);
            String title = "失败";
            g.drawString(title, 10 * 25, 10 * 25);
            JFrame jFrame = FLZJTankFrame.jFrame;
            jFrame.remove(this);
            // home.setLive(false);
        }

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        // 玩家控制坦克移动
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W){
                hero.setDirect(0);
                if (hero.getY() > 0 && !Touch.isTouchWall(hero,walls) && !Touch.isTouchWater(hero,waters)) {
                    hero.moveUp();
                }
            }else if(e.getKeyCode() == KeyEvent.VK_D){
                hero.setDirect(1);
                if(hero.getX() + 60 < 1000 && !Touch.isTouchWall(hero,walls) && !Touch.isTouchWater(hero,waters)) {
                    hero.moveRight();
                }
            }else if(e.getKeyCode() == KeyEvent.VK_S){
                hero.setDirect(2);
                if(hero.getY() + 60 < 750  && !Touch.isTouchWall(hero,walls) && !Touch.isTouchWater(hero,waters)) {
                    hero.moveDown();
                }
            }else if(e.getKeyCode() == KeyEvent.VK_A){
                hero.setDirect(3);
                if(hero.getX() > 0  && !Touch.isTouchWall(hero,walls) && !Touch.isTouchWater(hero,waters)) {
                    hero.moveLeft();
                }
            }else if(e.getKeyCode() == KeyEvent.VK_J){
                //System.out.println("用户按下J开始射击");
                //if(hero.shoot == null || !hero.shoot.isLive()) {
                hero.shootEnemy();
                //}
            }
            this.repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        // 这个线程用来判断
        @Override
        public void run() {
                while (home.isLive() && InitMap.isInitialized() ) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //判断玩家是否击中坦克
                    for (int i = 0; i < hero.heroShoots.size(); i++) {
                        Shoot shoot = hero.heroShoots.get(i);
                        if (shoot != null && shoot.isLive()) {
                            //遍历所有坦克
                            for (int j = 0; j < enemyTanks.size(); j++) {
                                EnemyTank enemyTank = enemyTanks.get(j);
                                Hit.hitTank(shoot, enemyTank);
                            }
                        }
                    }
                    //判断敌方是否击中玩家
                    for (int i = 0; i < enemyTanks.size(); i++) {
                        EnemyTank enemyTank = enemyTanks.get(i);
                        for (int j = 0; j < enemyTank.shoots.size(); j++) {
                            Shoot shoot = enemyTank.shoots.get(j);
                            Hit.hitHero(shoot);
                        }
                    }
                    // 判断是否击中墙
                    for (int i = 0; i < walls.size(); i++) {
                        Wall wall = walls.get(i);
                        // 判断墙是否被自己摧毁
                        for (int j = 0; j < hero.heroShoots.size(); j++) {
                            Shoot shoot = hero.heroShoots.get(j);
                            if (Hit.hitWall(shoot, wall)) {
                                hero.heroShoots.remove(shoot);
                            }
                        }
                        // 判断墙是否被敌人摧毁
                        for (int j = 0; j < enemyTanks.size(); j++) {
                            EnemyTank enemyTank = enemyTanks.get(j);
                            for (int k = 0; k < enemyTank.shoots.size(); k++) {
                                Shoot shoot = enemyTank.shoots.get(k);
                                if (Hit.hitWall(shoot, wall)) {
                                    enemyTank.shoots.remove(shoot);
                                }
                                ;
                            }
                        }
                    }
                    // 判断是否打中基地(家)
                    for (int i = 0; i < hero.heroShoots.size(); i++) {
                        Shoot shoot = hero.heroShoots.get(i);
                        if (Hit.hitHome(shoot)) {
                            hero.heroShoots.remove(shoot);
                            home.setLive(false);
                        }
                    }
                    for (int i = 0; i < enemyTanks.size(); i++) {
                        EnemyTank enemyTank = enemyTanks.get(i);
                        for (int j = 0; j < enemyTank.shoots.size(); j++) {
                            Shoot shoot = enemyTank.shoots.get(j);
                            if (Hit.hitHome(shoot)) {
                                enemyTank.shoots.remove(shoot);
                                home.setLive(false);
                            }
                        }
                    }
                    this.repaint();
            }
        }
}