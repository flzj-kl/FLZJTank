package com.flzj.tank;

import com.flzj.tank.common.Touch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class MyPanel extends JPanel implements Runnable,KeyListener {
    //定义一个存放Node的泛型Vector 用于恢复敌人数据
    Vector<Node> nodes = null;

    //定义我的坦克
    Hero hero = null;

    // 定义家对象
    static Home home = new Home(19 * 25, 27 * 25);

    //定义敌方坦克放入到vector中
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 6;

    //定义一个容器用于存放爆炸效果
    //说明，当子弹击中坦克时，加入一个bomb对象到vector
    Vector<Bomb>  bombs = new Vector<>();

    // 定义一个墙容器
    Vector<Wall> walls = new Vector<>();

    // 定义一个水容器
    Vector<Water> waters = new Vector<>();

    // 定义家的图片
    Image homeImg = null;
    // 定义家被摧毁图片
    Image homeDImg = null;

    // 定义驾驶员图片
    Image ding = null;
    Image gu = null;
    Image he = null;

    //定义三张炸弹图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    int levelUpKill2 = 1;
    int levelUpKill3 = 2;
    int driverLevel = 1;

    public MyPanel(String key){
        //解决nodes为空的问题
        File file = new File(Recorder.getRecodeFile());
        if(file.exists()){
            nodes = Recorder.readRecode();
        }else{
            System.out.println("文件不存在，只能重启游戏");
            key = "1";
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


        //初始化我方坦克
        hero = new Hero(14 * 25,26 * 25);   //初始化位置
        hero.setSpeed(5);

        // 根据选择初始化地图
        switch (key){
            default:
                System.out.println("您输入的值不对,正在您重开。。。");
            case "1":
                //初始化敌方坦克
                for(int i = 0 ; i < enemyTankSize ; i++){
                    Recorder.setAllEnemyTank(0);
                    //初始化位置
                    EnemyTank enemyTank = new EnemyTank(100*(i+1),0);
                    // 将enemyTanks 设置给 enemyTank!!
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
//            for(int j = 0 ; j < enemyTank.shoots.size() ; j++){
//                Shoot shoot = enemyTank.shoots.get(j);
//                new Thread(shoot).start();
//            }
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                //初始化敌方坦克
                for(int i = 0 ; i < nodes.size() ; i++ ){
                    Node node = nodes.get(i);
                    //初始化位置
                    EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY());
                    //将enemyTanks 设置给 enemyTank!!
                    enemyTank.setEnemyTanks(enemyTanks);
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
//            for(int j = 0 ; j < enemyTank.shoots.size() ; j++){
//                Shoot shoot = enemyTank.shoots.get(j);
//                new Thread(shoot).start();
//            }
                    enemyTanks.add(enemyTank);
                }
                break;

        }

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
        Recorder.setEnemyTanks(enemyTanks);
    }
    //编写方法，显示我方击毁敌方坦克的信息
    public void showInfo(Graphics g){
        int killNum = Recorder.getAllEnemyTank();
        //画出玩家的玩家的总成绩
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克",1020,30);
        drawTank(1020,60,g,0,1);//画出一个敌方坦克
        g.setColor(Color.black);
        g.drawString(killNum+"",1080,100);

        g.drawString("当前驾驶员", 1020, 175);
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
        //画面面板
        showInfo(g);

        // 如果家被爆了
        if(home.isLive()) {

            // 画已方坦克(被敌方射中死亡)
            if (hero.isHeroLive()) {
                drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
            } else {
                hero.setX(14 * 25);
                hero.setY(26 * 25);
                hero.setHeroLive(true);
                System.out.println("您已死亡重新开始");
            }
            //画玩家射出的子弹
//        if(hero.shoot != null && hero.shoot.isLive() == true) {
//            g.fill3DRect(hero.shoot.getX(), hero.shoot.getY(), 4, 4, false);
//      }
            //玩家连续射击版
            for (int i = 0; i < hero.heroShoots.size(); i++) {
                Shoot shoot = hero.heroShoots.get(i);
                if (shoot != null && shoot.isLive() == true) {
                    g.fill3DRect(shoot.getX(), shoot.getY(), 3, 3, false);
                } else {//如果shoot已经无效，（下面已经在攻击敌人时写删除了，所以这个是越界删除）
                    hero.heroShoots.remove(shoot);
                }
            }


            // 画敌方坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                //从vector容器取出的对象
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive()) {//当敌人坦克存活时才画
                    drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                    for (int j = 0; j < enemyTank.shoots.size(); j++) {
                        //取出坦克子弹
                        Shoot shoot = enemyTank.shoots.get(j);
                        //绘制77
                        if (shoot.isLive()) {
                            g.fill3DRect(shoot.getX(), shoot.getY(), 4, 4, false);
                        } else {  //移除
                            enemyTank.shoots.remove(shoot);
                        }
                    }
                }
            }

            // 画墙
            g.setColor(new Color(118, 77, 57));
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                if (wall.isLive()) {
                    int x = wall.getX(), y = wall.getY();
                    g.fill3DRect(x, y, 25, 13, true);
                    g.fill3DRect(x, y + 13, 12, 12, true);
                    g.fill3DRect(x + 12, y + 13, 13, 12, true);
                }
            }

            // 画河流
            g.setColor(new Color(0, 191, 255));
            for (int i = 0; i < waters.size(); i++) {
                Water water = waters.get(i);
                int x = water.getX(), y = water.getY();
                g.fill3DRect(x, y, 25, 25, false);
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
        }else{
            g.setFont(new Font("Serif",Font.BOLD,180));
            g.setColor(Color.red);
            String title = "失败";
            g.drawString(title,10 * 25,10 * 25 );
            home.setLive(true);
        }

    }

    //编写方法，画出坦克

    /**
     *
     * @param x       x坐标
     * @param y       y坐标
     * @param g       画笔
     * @param direct  方向   0 ↑    1 →    2  ↓   3  ←
     * @param type    种类   0 自己  1 敌人  2 其他
     *
     *
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0: //自己
                g.setColor(Color.cyan);
                break;
            case 1: //敌人
                g.setColor(Color.yellow);
                break;
            case 2: //测试坦克
                g.setColor(Color.black);
                break;
            case 3:
                g.setColor(Color.blue);
        }
        switch(direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x + 30,y,10,60,false);//右轮
                g.fill3DRect(x + 10 , y +10 , 20 ,40 ,false);//机身
                g.fillOval(x + 10 , y + 20 , 20 , 20);  //机盖
                g.drawLine(x + 20 , y  , x + 20 , y + 40);       //炮口
                break;
            case 1:
                g.fill3DRect(x,y,60,10,false);//上轮
                g.fill3DRect(x,y + 30,60,10,false);//下轮
                g.fill3DRect(x + 10 , y +10 , 40 ,20 ,false);//机身
                g.fillOval(x + 20 , y + 10 , 20 , 20);  //机盖
                g.drawLine(x + 30 , y + 20  , x + 60 , y + 20);       //炮口
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);//左轮
                g.fill3DRect(x + 30,y,10,60,false);//右轮
                g.fill3DRect(x + 10 , y +10 , 20 ,40 ,false);//机身
                g.fillOval(x + 10 , y + 20 , 20 , 20);  //机盖
                g.drawLine(x + 20 , y + 60  , x + 20 , y + 40);       //炮口
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);//上轮
                g.fill3DRect(x,y + 30,60,10,false);//下轮
                g.fill3DRect(x + 10 , y +10 , 40 ,20 ,false);//机身
                g.fillOval(x + 20 , y + 10 , 20 , 20);  //机盖
                g.drawLine(x + 30 , y + 20  , x  , y + 20);       //炮口
                break;
        }
    }

    // 编写方法，判断子弹是否击中敌人
    public void hitTank(Shoot s, EnemyTank enemyTank){
        //判断玩家击中敌方坦克
        switch (enemyTank.getDirect()){
            case 0: //坦克姿势为向上向下
            case 2:
                if(s.getX() > enemyTank.getX() && s.getX() < enemyTank.getX()+40
                && s.getY() > enemyTank.getY() && s.getY() < enemyTank.getY() +60){
                    s.setLive(false);
                    enemyTank.setLive(false);
                    //分数++
                    Recorder.addAllEnemyTank();
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
                    Recorder.addAllEnemyTank();
                    //被击中时的爆炸效果
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(enemyTank);
                    break;
                }
        }
    }

    // 判断敌方击中玩家坦克
    public void hitHero(Shoot s){

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
    public boolean hitWall(Shoot s,Wall w){
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
    public boolean hitHome(Shoot s){
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
                    homeImg = homeDImg;
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
                    homeImg = homeDImg;
                    return true;
                }
        }
        return false;
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
        while(home.isLive()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //判断玩家是否击中坦克
            for(int i = 0 ; i < hero.heroShoots.size() ; i++) {
                Shoot shoot =  hero.heroShoots.get(i);
                if ( shoot!= null && shoot.isLive()) {
                    //遍历所有坦克
                    for (int j = 0; j < enemyTanks.size(); j++) {
                        EnemyTank enemyTank = enemyTanks.get(j);
                        hitTank(shoot, enemyTank);
                    }
                }
            }

            //判断敌方是否击中玩家
            for(int i = 0 ; i < enemyTanks.size() ; i++){
                EnemyTank enemyTank = enemyTanks.get(i);
                for(int j = 0 ; j < enemyTank.shoots.size() ; j++){
                    Shoot shoot = enemyTank.shoots.get(j);
                    hitHero(shoot);
                }
            }

            // 判断是否击中墙
            for(int i = 0 ; i <  walls.size() ; i++){
                Wall wall = walls.get(i);
                // 判断墙是否被自己摧毁
                for(int j = 0 ; j < hero.heroShoots.size() ; j++) {
                    Shoot shoot = hero.heroShoots.get(j);
                    if(hitWall(shoot,wall)){
                        hero.heroShoots.remove(shoot);
                    }
                }
                // 判断墙是否被敌人摧毁
                for(int j = 0 ; j < enemyTanks.size() ; j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    for (int k = 0; k < enemyTank.shoots.size(); k++) {
                        Shoot shoot = enemyTank.shoots.get(k);
                        if(hitWall(shoot, wall)){
                            enemyTank.shoots.remove(shoot);
                        };
                    }
                }
            }

            // 判断是否打中基地(家)
            for(int i = 0 ; i < hero.heroShoots.size() ; i++){
                Shoot shoot = hero.heroShoots.get(i);
                if(hitHome(shoot)){
                    hero.heroShoots.remove(shoot);
                    home.setLive(false);
                }
            }

            for(int i = 0 ; i < enemyTanks.size() ; i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                for (int j = 0; j < enemyTank.shoots.size(); j++) {
                    Shoot shoot = enemyTank.shoots.get(j);
                    if(hitHome(shoot)) {
                        enemyTank.shoots.remove(shoot);
                        home.setLive(false);
                    }
                }
            }

            this.repaint();
        }
    }

}
