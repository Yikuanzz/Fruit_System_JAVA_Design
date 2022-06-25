package com.yikuan.frame;

import com.yikuan.fruit.dao.FruitDAO;
import com.yikuan.fruit.dao.impl.FruitDAOImpl;
import com.yikuan.fruit.pojo.Fruit;

import javax.swing.*;
import java.awt.*;

public class AddFrame extends JFrame {

    JPanel panel = new JPanel();

    // 水果价格
    JLabel label_price = new JLabel("请输入水果价格：");
    JTextField textField_price = new JTextField();

    // 水果库存
    JLabel label_count = new JLabel("请输入水果库存：");
    JTextField textField_count = new JTextField();

    // 水果备注
    JLabel label_remark = new JLabel("请输入水果备注：");
    JTextField textField_remark = new JTextField();

    // 添加按钮
    JButton button_add = new JButton("添加");

    // 取消按钮
    JButton button_quit = new JButton("取消");

    // 水果名
    String label_fruit;
    public AddFrame(String label_fruit){
        // 水果名字
        this.label_fruit = label_fruit;

        // 水果名字
        JLabel fruitName = new JLabel(label_fruit);

        // 设置窗口名字
        this.setTitle("水果添加");

        // 设置窗口大小
        this.setSize(480, 420);

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

        ///////////////添加控件////////////////////
        panel.setLayout(null);

        // 添加标签
        panel.add(fruitName);
        fruitName.setFont(new Font("黑体",Font.BOLD,36));
        fruitName.setBounds(195, 20, 100, 50);

        // 添加价格标签
        panel.add(label_price);
        label_price.setFont(new Font("黑体",Font.PLAIN,18));
        label_price.setBounds(40, 90, 160, 30);

        // 添加价格文本
        panel.add(textField_price);
        textField_price.setFont(new Font("黑体",Font.PLAIN,18));
        textField_price.setBounds(190, 90, 200, 30);

        // 添加数量标签
        panel.add(label_count);
        label_count.setFont(new Font("黑体",Font.PLAIN,18));
        label_count.setBounds(40, 160, 160, 30);

        // 添加数量文本
        panel.add(textField_count);
        textField_count.setFont(new Font("黑体",Font.PLAIN,18));
        textField_count.setBounds(190, 160, 200, 30);

        // 添加备注
        panel.add(label_remark);
        label_remark.setFont(new Font("黑体",Font.PLAIN,18));
        label_remark.setBounds(40, 230, 160, 30);

        // 添加备注文本
        panel.add(textField_remark);
        textField_remark.setFont(new Font("黑体",Font.PLAIN,18));
        textField_remark.setBounds(190, 230, 200, 30);

        // 添加按钮
        panel.add(button_add);
        button_add.setFont(new Font("黑体",Font.PLAIN,18));
        button_add.setBounds(160, 300, 80, 30);
        button_add.addActionListener((e)->{
            AddMsg();
        });

        // 添加取消按钮
        panel.add(button_quit);
        button_quit.setFont(new Font("黑体",Font.PLAIN,18));
        button_quit.setBounds(270, 300,80,30);
        button_quit.addActionListener((e)->{
            this.dispose();
        });
    }

    private void AddMsg(){
        String FruitName = label_fruit;
        String price = textField_price.getText();
        String count = textField_count.getText();
        String remark = textField_remark.getText();
        Fruit fruit = new Fruit(0,FruitName, Integer.parseInt(price), Integer.parseInt(count), remark);
        FruitDAO fruitDAO = new FruitDAOImpl();
        fruitDAO.addFruit(fruit);
        JOptionPane.showMessageDialog(null,"添加成功！");
        this.dispose();
    }
}
