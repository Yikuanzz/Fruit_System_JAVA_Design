package com.yikuan.frame;

import com.yikuan.fruit.dao.FruitDAO;
import com.yikuan.fruit.dao.UserDAO;
import com.yikuan.fruit.dao.impl.FruitDAOImpl;
import com.yikuan.fruit.dao.impl.UserDAOImpl;
import com.yikuan.fruit.pojo.Fruit;
import com.yikuan.util.StringUtil;

import javax.swing.*;
import java.awt.*;

public class FruitNameFrame extends JFrame {
    //对象
    FruitDAO fruitDAO = new FruitDAOImpl();

    JPanel panel = new JPanel();

    // 水果名标签
    JLabel label_fruit = new JLabel("请输入水果名称");

    // 水果文本框
    JTextField textField_fruit = new JTextField();

    // 查询按钮
    JButton button_inquire = new JButton("确定");

    public FruitNameFrame(int slt){
        // 设置窗口名字
        this.setTitle("水果名称");

        // 设置窗口大小
        this.setSize(300, 200);

        // 设置窗口居中
        this.setLocationRelativeTo(null);

        // 设置不可最大化
        this.setResizable(false);

        // 设置窗口可见
        this.setVisible(true);

        // 添加容器
        this.setContentPane(panel);

        //////////////添加控件////////////////
        panel.setLayout(null);

        // 添加标签
        panel.add(label_fruit);
        label_fruit.setFont(new Font("黑体",Font.PLAIN, 24));
        label_fruit.setBounds(50,10,180,50);

        // 设置文本框
        panel.add(textField_fruit);
        textField_fruit.setFont(new Font("黑体",Font.PLAIN, 18));
        textField_fruit.setBounds(50,60,180,30);

        // 设置查询按钮
        panel.add(button_inquire);
        button_inquire.setFont(new Font("黑体",Font.PLAIN, 18));
        button_inquire.setBounds(50,100,180,30);
        button_inquire.addActionListener((e)-> SelectFrame(slt));
    }

    private void SelectFrame(int slt){
        String FruitName = textField_fruit.getText();
        switch (slt) {
            case 0:
                AddFunction(FruitName);
                break;
            case 1:
                SearchMsg(FruitName);
                break;
            case 2:
                DeleteFruit(FruitName);
        }


    }

    public void AddFunction(String FruitName){
        Fruit fruit = fruitDAO.getFruitByFname(FruitName);
        if(fruit!=null){
            new AddCountFrame(FruitName);
            this.dispose();
        }else {
            new AddFrame(FruitName);
            this.dispose();
        }
    }

    public void SearchMsg(String FruitName){
        Fruit fruit = fruitDAO.getFruitByFname(FruitName);
        if(fruit!=null){
            new ShowMessageFrame(FruitName);
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"不存在该水果！");
        }
    }

    public void DeleteFruit(String FruitName){
        Fruit fruit = fruitDAO.getFruitByFname(FruitName);
        if(fruit!=null){
            fruitDAO.delFruit(FruitName);
            JOptionPane.showMessageDialog(null,"下架成功！");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"不存在该水果！");
        }
    }
}
