package com.yikuan.frame;

import com.yikuan.fruit.dao.FruitDAO;
import com.yikuan.fruit.dao.impl.FruitDAOImpl;
import com.yikuan.fruit.pojo.Fruit;

import javax.swing.*;
import java.awt.*;

public class AddCountFrame extends JFrame {

    JPanel panel = new JPanel();

    // 添加按钮
    JButton button_add = new JButton("确定");

    // 取消按钮
    JButton button_quit = new JButton("取消");

    // 库存表签
    JLabel label_count = new JLabel("请输入添加的库存：");

    // 库存文本
    JTextField textField_count = new JTextField();

    // 水果名字
    String label_fruit;

    public AddCountFrame(String label_fruit){
            this.label_fruit = label_fruit;

            // 水果名字
            JLabel fruitName = new JLabel(label_fruit);

            // 设置窗口名字
            this.setTitle("添加水果库存");

            // 设置窗口大小
            this.setSize(300, 200);

            // 设置窗口居中
            this.setLocationRelativeTo(null);

            // 设置不可最大化
            this.setResizable(false);

            // 设置关闭
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 设置窗口可见
            this.setVisible(true);

            // 添加容器
            this.setContentPane(panel);

            ///////////添加控件////////////////
            panel.setLayout(null);

            // 添加水果名字
            panel.add(fruitName);
            fruitName.setFont(new Font("黑体",Font.BOLD,24));
            fruitName.setBounds(110, 5, 120, 50);

            // 添加库存标签
            panel.add(label_count);
            label_count.setFont(new Font("黑体",Font.PLAIN,16));
            label_count.setBounds(10, 35, 200, 50);

            // 添加库存文本框
            panel.add(textField_count);
            textField_count.setFont(new Font("黑体", Font.PLAIN, 16));
            textField_count.setBounds(50, 80,180, 30);

            // 添加按钮
            panel.add(button_add);
            button_add.setFont(new Font("黑体", Font.PLAIN, 16));
            button_add.setBounds(50, 115, 80, 30);
            button_add.addActionListener((e)->{
                AddCountFunction();
            });

            // 添加取消按钮
            panel.add(button_quit);
            button_quit.setFont(new Font("黑体", Font.PLAIN, 16));
            button_quit.setBounds(150, 115,80,30);
            button_quit.addActionListener((e)->{
                this.dispose();
            });



    }

    private void AddCountFunction(){
        String count = textField_count.getText();
        FruitDAO fruitDAO = new FruitDAOImpl();
        Fruit FN = fruitDAO.getFruitByFname(label_fruit);
        FN.setFcount(FN.getFcount() + Integer.parseInt(count));
        fruitDAO.updateFruit(FN);
        JOptionPane.showMessageDialog(null,"添加成功！");
        this.dispose();
    }
}
