package com.flzj.tank.save;

import com.flzj.tank.Node;
import com.flzj.tank.entity.EnemyTank;
import com.flzj.tank.entity.Hero;
import com.flzj.tank.screen.GamePanel;

import java.io.*;
import java.util.Vector;

public class SaveTank {
    //定义IO流
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String saveFile = "src\\data\\saveTank.txt";

    // 获取GamePanel的字段
    private static Vector<EnemyTank> enemyTanks = null;
    private static Hero hero = null;

    // 坦克集合字段
    private static Vector<TankNode> tankNodes = new Vector<>();

    public static String getSaveFile() {
        return saveFile;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        SaveTank.enemyTanks = enemyTanks;
    }

    // 读取文件中关于坦克位置的数据
    public static Vector<TankNode> load(){
        try {
            br = new BufferedReader(new FileReader(saveFile));
            String line = "";
            while((line = br.readLine())!= null){
                String[] xyd = line.split(" ");
                TankNode tankNode = new TankNode(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                tankNodes.add(tankNode);
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
        return tankNodes;
    }

    //当游戏退出时，保存文件
    public static void save(){
        try {
            // 赋值
            hero = GamePanel.hero;
            enemyTanks = GamePanel.enemyTanks;

            // 判断是否为空.避免文件覆盖
            if(hero == null){
                return;
            }
            if(enemyTanks.size() == 0){
                return;
            }

            bw = new BufferedWriter(new FileWriter(saveFile));

            // 保存各种信息
            String heroRecode = hero.getX() + " " + hero.getY() + " " + hero.getDirect();
            bw.write(heroRecode + "\r\n");
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


}
