package com.flzj.tank;


import com.flzj.tank.save.SaveRecode;
import com.flzj.tank.save.SaveTank;
import com.flzj.tank.save.SaveWall;
import com.flzj.tank.save.SaveWater;
import com.flzj.tank.screen.FirstPanel;
import com.flzj.tank.screen.GamePanel;
import com.flzj.tank.screen.TestPanel;

import javax.swing.*;
import java.awt.event.*;

public class FLZJTankFrame {

    public static JFrame jFrame = null;     // 方便传递和修改
    public static FirstPanel firstPanel = null;
    public static GamePanel gamePanel = null;
    private String choose = null;
    private TestPanel testPanel = new TestPanel();

    public FLZJTankFrame(){
        // 框架的基本信息
        jFrame = new JFrame("负离子价坦克");
        jFrame.setSize(1300,950);
        jFrame.setIconImage(new ImageIcon("src/com/flzj/tank/imgs/FLZJTank.png").getImage());
        jFrame.setLocation(300,50);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);


        // 添加FirstPanel
        firstPanel = new FirstPanel();
        firstPanel.setLayout(null);
        firstPanel.add(firstPanel.getBtn1());
        firstPanel.add(firstPanel.getBtn2());
        jFrame.add(firstPanel);


        // 框架设置为可见
        jFrame.setVisible(true);
        // 非常重要滴监听
        jFrame.setFocusable(true);



        // 在JFrame 中增加相应关闭窗口的处理(用于退出自动存档)
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveTank.save();
                SaveWall.save();
                SaveWater.save();
                SaveRecode.save();
            }
        });



    }


}

