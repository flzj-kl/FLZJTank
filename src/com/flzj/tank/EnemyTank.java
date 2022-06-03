package com.flzj.tank;

import com.flzj.tank.common.Touch;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    private boolean isLive = true;
    private Shoot shoot = null;
    Vector<Shoot> shoots = new Vector<>();
    //增加成员 enemyTanks 可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    // 可以获取到水和墙的数组
    Vector<Wall> walls = null;
    Vector<Water> waters = null;
    //提供一个方法来获得 MyPanel里的坦克

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法,判断当前的这个敌人坦克，是否和enemyTanks中的其他坦克发生重叠或者碰撞
    public boolean isTouchEnemyTank() {
        //判断当前敌人坦克(this)方向 八种情况
        switch (this.getDirect()) {
            case 0://上
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //如果敌人坦克是上或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                 y的范围[enemyTank.getY(),enemyTank.getY()+60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //坦克的左上角坐标[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //坦克的右上角坐标[this.getX()+40,this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        //如果敌人坦克是左或右   x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                   y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                //坦克的左上角坐标[this.getX(),this.getY()]
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                //坦克的右上角坐标[this.getX()+40,this.getY()]
                                if (this.getX() + 40 >= enemyTank.getX()
                                        && this.getX() + 40 <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                    }
                }
                break;
            case 1://右
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //如果敌人坦克是上或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                 y的范围[enemyTank.getY(),enemyTank.getY()+60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //坦克的右上角角坐标[this.getX()+60,this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY()  >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //坦克的右下角坐标[this.getX()+60,this.getY()+40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY()  +40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        //如果敌人坦克是左或右   x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                   y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                //坦克的右上角坐标[this.getX()+60 ,this.getY()]
                                if (this.getX() + 60 >= enemyTank.getX()  //这里忘+60必死
                                        && this.getX() + 60 <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                //坦克的右下角坐标[this.getX()+60,this.getY()+40]
                                if (this.getX() + 60 >= enemyTank.getX()
                                        && this.getX() + 60 <= enemyTank.getX() + 60
                                        && this.getY() + 40 >= enemyTank.getY()
                                        && this.getY() + 40 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }

                    }
                }
                break;
            case 2://下
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //如果敌人坦克是上或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                 y的范围[enemyTank.getY(),enemyTank.getY()+60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //坦克的左下角角角坐标[this.getX(),this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //坦克的右下角坐标[this.getX()+40,this.getY()+60]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        //如果敌人坦克是左或右   x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                   y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                //坦克的左下角坐标[this.getX() ,this.getY() + 60]
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 60
                                        && this.getY() + 60 >= enemyTank.getY()
                                        && this.getY() + 60 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                //坦克的右下角坐标[this.getX()+40,this.getY()+60]
                                if (this.getX() + 40 >= enemyTank.getX()
                                        && this.getX() + 40 <= enemyTank.getX() + 60
                                        && this.getY() + 60 >= enemyTank.getY()
                                        && this.getY() + 60 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                    }
                }
                break;
            case 3://左
                //让当前敌人坦克和其他所有的敌人坦克比较
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //从vector 中取出一个敌人坦克
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        //如果敌人坦克是上或下 x的范围[enemyTank.getX(),enemyTank.getX()+40]
                        //                 y的范围[enemyTank.getY(),enemyTank.getY()+60]

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //坦克的左上角角角坐标[this.getX(),this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 40
                                    && this.getY()  >= enemyTank.getY()
                                    && this.getY()  <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //坦克的左下角坐标[this.getX(),this.getY()+40]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                        }

                        //如果敌人坦克是左或右   x的范围[enemyTank.getX(),enemyTank.getX()+60]
                        //                   y的范围[enemyTank.getY(),enemyTank.getY()+40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //坦克的左上角坐标[this.getX() ,this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY()  >= enemyTank.getY()
                                    && this.getY()  <= enemyTank.getY() + 40) {
                                return true;
                            }
                            //坦克的左下角坐标[this.getX(),this.getY()+40]
                            if (this.getX()  >= enemyTank.getX()
                                    && this.getX()  <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
        }
        return false;
    }


    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public void setWalls(Vector<Wall> walls) {
        this.walls = walls;
    }

    public void setWaters(Vector<Water> waters) {
        this.waters = waters;
    }

    @Override
    public void run() {
        while (MyPanel.home.isLive()) {
            //敌方坦克运动   敌方坦克射击
            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < 30; i++) {//向左
                        if (getY() > 0 && !isTouchEnemyTank() && !Touch.isTouchWall(this,walls) && !Touch.isTouchWater(this,waters)) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //启动子弹线程
                    shoot = new Shoot(getX()+20 , getY(),0);
                    shoots.add(shoot);
                    (new Thread(shoot)).start();
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {//向右
                        if (getX() + 60 < 1000 && !isTouchEnemyTank() && !Touch.isTouchWall(this,walls) && !Touch.isTouchWater(this,waters)) { //这里也不能60 可能是速度问题
                            moveRight();
                        }
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    shoot = new Shoot(getX()+60,getY()+20,1);
                    shoots.add(shoot);
                    (new Thread(shoot)).start();
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750 && !isTouchEnemyTank() && !Touch.isTouchWall(this,walls) && !Touch.isTouchWater(this,waters)) { //为什么不是60 你个🐽画布画错了
                            moveDown();
                        }
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    shoot = new Shoot(getX()+20,getY()+60,2);
                    shoots.add(shoot);
                    (new Thread(shoot)).start();
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0 && !isTouchEnemyTank() && !Touch.isTouchWall(this,walls) && !Touch.isTouchWater(this,waters)) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    shoot = new Shoot(getX(),getY()+20,3);
                    shoots.add(shoot);
                    (new Thread(shoot)).start();
                    break;
            }
            setDirect((int) (Math.random() * 4));

            if (!isLive) {
                break;
            }
        }
    }
}
