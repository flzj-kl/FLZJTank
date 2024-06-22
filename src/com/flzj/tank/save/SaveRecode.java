package com.flzj.tank.save;

import com.flzj.tank.entity.Wall;
import com.flzj.tank.screen.GamePanel;

import java.io.*;

public class SaveRecode {
    //定义IO流
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String saveFile = "src\\data\\saveRecode.txt";
    private static int killScore;
    private static int driverLevel;

    public static int getKillScore() {
        return killScore;
    }

    public static int getDriverLevel() {
        return driverLevel;
    }

    public static void load() {
        try {
            br = new BufferedReader(new FileReader(saveFile));
            String line = "";
            line = br.readLine();
            killScore = Integer.parseInt(line);
            line = br.readLine();
            driverLevel = Integer.parseInt(line);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void save() {
        try {
            bw = new BufferedWriter(new FileWriter(saveFile));
            driverLevel = GamePanel.driverLevel;
            // 保存各种信息
            String ks = Integer.toString(killScore);
            //写入到文件
            bw.write(ks + "\r\n");
            String dl = Integer.toString(driverLevel);
            bw.write(dl + "\r\n");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void killScoreAdd(){
        killScore++;
    }

}
