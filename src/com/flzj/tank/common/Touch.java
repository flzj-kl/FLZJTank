package com.flzj.tank.common;

import com.flzj.tank.Tank;
import com.flzj.tank.Wall;
import com.flzj.tank.Water;

import java.util.Vector;

/**
 *  这个类用来判断是碰撞
 */
public class Touch {

    // 判断坦克是否接触到城墙
    public static boolean isTouchWall(Tank tank, Vector<Wall> walls){
        int direct = tank.getDirect();
        for(int i = 0 ; i < walls.size() ; i++){
            Wall wall = walls.get(i);
            if(direct == 0){            // 坦克向上移动
                // 左轮判断
                if(tank.getX() >= wall.getX() && tank.getX() <= wall.getX() + 25
                && tank.getY()  >= wall.getY() && tank.getY()  <= wall.getY() +25)
                return true;
                // 右轮判断
                if(tank.getX() + 40 >= wall.getX() && tank.getX() + 40 <= wall.getX() + 25
                        && tank.getY()  >= wall.getY() && tank.getY()  <= wall.getY() +25)
                    return true;
                // 枪口判断
                if(tank.getX() + 20 >= wall.getX() && tank.getX() + 20 <= wall.getX() + 25
                        && tank.getY()  >= wall.getY() && tank.getY()  <= wall.getY() +25)
                    return true;
            }else if(direct == 1){      // 坦克向右移动
                // 上轮判断
                if(tank.getY() >= wall.getY() && tank.getY() <= wall.getY() + 25
                  && tank.getX() + 60 >= wall.getX() && tank.getX() + 60 <= wall.getX() + 25)
                    return true;
                // 下轮判断
                if(tank.getY() + 40 >= wall.getY() && tank.getY() + 40 <= wall.getY() + 25
                        && tank.getX() + 60 >= wall.getX() && tank.getX() + 60 <= wall.getX() + 25)
                    return true;
                // 枪口判断
                if(tank.getY() + 20 >= wall.getY() && tank.getY() + 20 <= wall.getY() + 25
                        && tank.getX() + 60 >= wall.getX() && tank.getX() + 60 <= wall.getX() + 25)
                    return true;
            }else if(direct == 2){     // 坦克向下移动
                // 左轮判断
                if(tank.getX()  >= wall.getX() && tank.getX() <= wall.getX() + 25
                        && tank.getY() + 60 >= wall.getY() && tank.getY() + 60 <= wall.getY() +25)
                    return true;
                // 右轮判断
                if(tank.getX() + 40 >= wall.getX() && tank.getX() + 40 <= wall.getX() + 25
                        && tank.getY() + 60 >= wall.getY() && tank.getY() + 60 <= wall.getY() +25)
                    return true;
                // 枪口判断
                if(tank.getX() + 20 >= wall.getX() && tank.getX() + 20 <= wall.getX() + 25
                        && tank.getY() + 60 >= wall.getY() && tank.getY() + 60 <= wall.getY() +25)
                    return true;
            }else if(direct == 3){     // 坦克向左移动
                // 上轮判断
                if(tank.getY() >= wall.getY() && tank.getY() <= wall.getY() + 25
                        && tank.getX()  >= wall.getX() && tank.getX() <= wall.getX() + 25)
                    return true;
                // 下轮判断
                if(tank.getY() + 40 >= wall.getY() && tank.getY() + 40 <= wall.getY() + 25
                        && tank.getX()  >= wall.getX() && tank.getX() <= wall.getX() + 25)
                    return true;
                // 枪口判断
                if(tank.getY() + 20 >= wall.getY() && tank.getY() + 20 <= wall.getY() + 25
                        && tank.getX()  >= wall.getX() && tank.getX() <= wall.getX() + 25)
                    return true;
            }
        }
        return false;
    }

    // 判断坦克是否接触到水
    public static boolean isTouchWater(Tank tank, Vector<Water> waters){
        int direct = tank.getDirect();
        for(int i = 0 ; i < waters.size() ; i++){
            Water wall = waters.get(i);
            if(direct == 0){            // 坦克向上移动
                // 左轮判断
                if(tank.getX() > wall.getX() && tank.getX() < wall.getX() + 25
                        && tank.getY()  > wall.getY() && tank.getY()  < wall.getY() +25)
                    return true;
                // 右轮判断
                if(tank.getX() + 40 > wall.getX() && tank.getX() + 40 < wall.getX() + 25
                        && tank.getY()  > wall.getY() && tank.getY()  < wall.getY() +25)
                    return true;
            }else if(direct == 1){      // 坦克向右移动
                // 上轮判断
                if(tank.getY() > wall.getY() && tank.getY() < wall.getY() + 25
                        && tank.getX() + 60 >= wall.getX() && tank.getX() + 60 <= wall.getX() + 25)
                    return true;
                // 下轮判断
                if(tank.getY() + 40 > wall.getY() && tank.getY() + 40 < wall.getY() + 25
                        && tank.getX() + 60 > wall.getX() && tank.getX() + 60 < wall.getX() + 25)
                    return true;
            }else if(direct == 2){     // 坦克向下移动
                // 左轮判断
                if(tank.getX()  > wall.getX() && tank.getX() < wall.getX() + 25
                        && tank.getY() + 60 > wall.getY() && tank.getY() + 60 < wall.getY() +25)
                    return true;
                // 右轮判断
                if(tank.getX() + 40 > wall.getX() && tank.getX() + 40 < wall.getX() + 25
                        && tank.getY() + 60 > wall.getY() && tank.getY() + 60 < wall.getY() +25)
                    return true;
            }else if(direct == 3){     // 坦克向左移动
                // 上轮判断
                if(tank.getY() > wall.getY() && tank.getY() < wall.getY() + 25
                        && tank.getX()  > wall.getX() && tank.getX() < wall.getX() + 25)
                    return true;
                // 下轮判断
                if(tank.getY() + 40 > wall.getY() && tank.getY() + 40 < wall.getY() + 25
                        && tank.getX()  > wall.getX() && tank.getX() < wall.getX() + 25)
                    return true;
            }
        }
        return false;
    }
}
