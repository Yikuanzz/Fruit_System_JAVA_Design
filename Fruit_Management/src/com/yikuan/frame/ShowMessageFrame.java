package com.yikuan.frame;

import com.yikuan.fruit.dao.FruitDAO;
import com.yikuan.fruit.dao.impl.FruitDAOImpl;
import com.yikuan.fruit.pojo.Fruit;

import javax.swing.*;
import java.awt.*;

public class ShowMessageFrame extends JFrame {

    public ShowMessageFrame(String FruitName){
        FruitDAO fruitDAO = new FruitDAOImpl();
        Fruit FN = fruitDAO.getFruitByFname(FruitName);

        // 设置容器
        JPanel panel = new JPanel();

        // 水果价格
        JLabel label_price = new JLabel("水果价格：");
        JLabel label_price_ = new JLabel(String.valueOf(FN.getPrice()));

        // 水果库存
        JLabel label_count = new JLabel("水果库存：");
        JLabel label_count_ = new JLabel(String.valueOf(FN.getFcount()));

        // 水果备注
        JLabel label_remark = new JLabel("水果备注：");
        JLabel label_remark_ = new JLabel(FN.getRemark());

        // 返回按钮
        JButton button_quit = new JButton("返回");


        // 水果名字
        JLabel label_fruitName = new JLabel("水果名称：");

        JLabel label_fruitName_ = new JLabel(FruitName);

        // 设置窗口名字
        this.setTitle("水果信息");

        // 设置窗口大小
        this.setSize(300, 400);

        // 设置关闭
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // 设置窗口居中
        this.setLocationRelativeTo(null);

        // 设置不可最大化
        this.setResizable(false);

        // 设置窗口可见
        this.setVisible(true);

        // 添加容器
        this.setContentPane(panel);

        /////////////////添加控件/////////////////////////
        panel.setLayout(null);

        // 水果名称标签
        panel.add(label_fruitName);
        label_fruitName.setFont(new Font("黑体",Font.PLAIN,18));
        label_fruitName.setBounds(40,20,160,30);

        // 添加水果标签
        panel.add(label_fruitName_);
        label_fruitName_.setFont(new Font("黑体",Font.BOLD,18));
        label_fruitName_.setBounds(130, 10, 100, 50);

        // 添加价格标签
        panel.add(label_price);
        label_price.setFont(new Font("黑体",Font.PLAIN,18));
        label_price.setBounds(40, 90, 160, 30);

        // 添加价格文本
        panel.add(label_price_);
        label_price_.setFont(new Font("黑体",Font.PLAIN,18));
        label_price_.setBounds(130, 90, 200, 30);

        // 添加数量标签
        panel.add(label_count);
        label_count.setFont(new Font("黑体",Font.PLAIN,18));
        label_count.setBounds(40, 160, 160, 30);

        // 添加数量文本
        panel.add(label_count_);
        label_count_.setFont(new Font("黑体",Font.PLAIN,18));
        label_count_.setBounds(130, 160, 200, 30);

        // 添加备注
        panel.add(label_remark);
        label_remark.setFont(new Font("黑体",Font.PLAIN,18));
        label_remark.setBounds(40, 230, 160, 30);

        // 添加备注文本
        panel.add(label_remark_);
        label_remark_.setFont(new Font("黑体",Font.PLAIN,18));
        label_remark_.setBounds(130, 230, 200, 30);

        // 添加取消按钮
        panel.add(button_quit);
        button_quit.setFont(new Font("黑体",Font.PLAIN,18));
        button_quit.setBounds(100, 300,80,30);
        button_quit.addActionListener((e)->{
            this.dispose();
        });
    }

}
