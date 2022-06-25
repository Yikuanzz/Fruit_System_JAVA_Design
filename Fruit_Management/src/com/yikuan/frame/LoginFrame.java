package com.yikuan.frame;

import com.yikuan.fruit.dao.UserDAO;
import com.yikuan.fruit.dao.impl.UserDAOImpl;
import com.yikuan.model.User;
import com.yikuan.util.DbUtil;
import com.yikuan.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.sql.Connection;

public class LoginFrame extends JFrame{

    JLabel label_login = new JLabel();

    ImageIcon icon_login = new ImageIcon("images/login.png");

    JPanel panel = new JPanel();

    JLabel label_text = new JLabel("水果管理系统");

    JLabel label_user = new JLabel("用户名 ：");

    JTextField textField_user = new JTextField();

    JLabel label_pwd = new JLabel("密  码 ：");

    JTextField textField_pwd = new JTextField();

    JButton button_login = new JButton("登录");

    DbUtil dbUtil = new DbUtil();
    UserDAOImpl userDao = new UserDAOImpl();

    public LoginFrame(){
        /////////////////基础设置//////////////////////
        // 设置窗口名字
        this.setTitle("管理员登录");

        // 设置窗口大小
        this.setSize(600, 600);

        // 设置关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口居中
        this.setLocationRelativeTo(null);

        // 设置不可最大化
        this.setResizable(false);

        // 设置窗口可见
        this.setVisible(true);

        // 添加容器
        this.setContentPane(panel);

        ////////////////添加控件///////////////////
        panel.setLayout(null);

        // 登录图片
        panel.add(label_login);
        label_login.setIcon(icon_login);
        label_login.setBounds(140, 30,icon_login.getIconWidth(), icon_login.getIconHeight());


        // 登录文字
        panel.add(label_text);
        label_text.setFont(new Font("幼圆",Font.BOLD,24));
        label_text.setBounds(210,240,400,50);
        label_text.requestFocus();


        // 用户名标签
        panel.add(label_user);
        label_user.setFont(new Font("黑体",Font.PLAIN, 18));
        label_user.setBounds(100,310,100,50);



        // 用户名文本框
        panel.add(textField_user);
        textField_user.setFont(new Font("黑体",Font.PLAIN,14));
        textField_user.setBounds(200,320,220,30);
        textField_user.addFocusListener(new JTextFieldHintListener(textField_user, " 请输入用户名"));

        // 密码标签
        panel.add(label_pwd);
        label_pwd.setFont(new Font("黑体",Font.PLAIN, 18));
        label_pwd.setBounds(100,350,100,50);

        // 密码文本框
        panel.add(textField_pwd);
        textField_pwd.setFont(new Font("黑体",Font.PLAIN, 14));
        textField_pwd.setBounds(200,360,220,30);
        textField_pwd.addFocusListener(new JTextFieldHintListener(textField_pwd, " 请输入密码"));

        // 登录按钮
        panel.add(button_login);
        button_login.setFont(new Font("黑体",Font.PLAIN, 16));
        button_login.setBounds(230,410,150,30);
        button_login.addActionListener((e)->{
            login_system();
        });

    }

    // 登录事件
    private void login_system(){
        String userName = this.textField_user.getText();
        String password = this.textField_pwd.getText();
        if(StringUtil.isEmpty(userName)){
            JOptionPane.showMessageDialog(null, "用户名不能为空！");
        }
        if(StringUtil.isEmpty(password)){
            JOptionPane.showMessageDialog(null,"密码不能为空!");
        }
        User user = new User(userName, password);
        Connection conn = null;

        conn = dbUtil.getCon();
        User currentUser = userDao.login(conn, user);
        if(currentUser!=null){
            // JOptionPane.showMessageDialog(null, "登录成功！");
            dispose();
            new MainFrame();
        }else {
            JOptionPane.showMessageDialog(null, "用户名或密码错误！");
        }



    }
}




