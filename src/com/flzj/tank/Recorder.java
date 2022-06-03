package com.flzj.tank;

import java.io.*;
import java.util.Vector;

/*
        用于记录信息和文字交互
 */
//@SuppressWarnings({"all"})
public class Recorder{
    //定义变量，用来记录我方击败坦克
    private static int allEnemyTank = 0;
    //定义IO流
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recodeFile = "src\\myRecode.txt";
    //定义一个Vector,指向myPanel 对象的 坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    //增加一个nodes容器接受读取的值
    private static Vector<Node> nodes = new Vector<>();


    //读取文件路径
    public static String getRecodeFile() {
        return recodeFile;
    }

    //读取方法
    public static Vector<Node> readRecode(){
        try {
            br = new BufferedReader(new FileReader(recodeFile));
            allEnemyTank = Integer.parseInt(br.readLine());
            String line = "";
            while((line = br.readLine())!= null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
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
        return nodes;
    }


    //增加一个方法，当游戏退出时，保存文件
    public static void keepRecode(){
        try {
            bw = new BufferedWriter(new FileWriter(recodeFile));
            bw.write(allEnemyTank + "\r\n");
            //记录敌方坦克的坐标和方向 一行保存一个敌人信息
            //OOP 定义一个属性，然后通过SetXXX来得到 敌人的Vector
            for(int i = 0 ; i < enemyTanks.size() ; i++){
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive()){//建议判断
                    //保存该enemyTank的信息
                    String record = enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect();
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


    public static int getAllEnemyTank() {
        return allEnemyTank;
    }

    public static void setAllEnemyTank(int allEnemyTank) {
        Recorder.allEnemyTank = allEnemyTank;
    }

    //当我方坦克击毁一个敌方坦克，就应该allEnemyTank++
    public static void addAllEnemyTank(){
        Recorder.allEnemyTank++;
    }


}

