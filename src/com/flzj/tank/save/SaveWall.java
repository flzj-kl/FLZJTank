package com.flzj.tank.save;

import com.flzj.tank.entity.EnemyTank;
import com.flzj.tank.entity.Hero;
import com.flzj.tank.entity.Wall;
import com.flzj.tank.screen.GamePanel;

import java.io.*;
import java.util.Vector;

public class SaveWall {
    //定义IO流
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String saveFile = "src\\data\\saveWall.txt";

    // 获取GamePanel的字段
    private static Vector<Wall> walls = new Vector<>();


    // 读取文件中关于墙的位置数据
    public static Vector<Wall> load(){
        try {
            br = new BufferedReader(new FileReader(saveFile));
            String line = "";
            while((line = br.readLine())!= null){
                String[] xy = line.split(" ");
                Wall wall = new Wall(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
                walls.add(wall);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return walls;
    }



    //当游戏退出时，保存文件
    public static void save(){
        try {
            // 赋值
            walls = GamePanel.walls;

            // 判断是否为空.避免文件覆盖
            if(walls == null){
                return;
            }
            bw = new BufferedWriter(new FileWriter(saveFile));
            // 保存各种信息
            for(int i = 0 ; i < walls.size() ; i++){
                Wall wall = walls.get(i);
                if(wall.isLive()){//建议判断
                    //保存该enemyTank的信息
                    String record = wall.getX()+" "+ wall.getY();
                    //写入到文件
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
