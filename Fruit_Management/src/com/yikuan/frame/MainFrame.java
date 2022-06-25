package com.yikuan.frame;

import com.yikuan.fruit.dao.FruitDAO;
import com.yikuan.fruit.dao.impl.FruitDAOImpl;
import com.yikuan.fruit.pojo.Fruit;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class MainFrame extends JFrame {
    // 添加容器
    JPanel panel = new JPanel();

    // 查询库存按钮
    JButton button_query = new JButton("查看库存");

    // 添加库存按钮
    JButton button_add = new JButton("添加库存");

    // 查看指定库存按钮
    JButton button_inquire = new JButton("查看水果");

    // 下架水果按钮
    JButton button_delete = new JButton("下架水果");

    // 退出按钮
    JButton button_close = new JButton("退出");

    // 时间
    JLabel label_time = new JLabel("时间：");
    JTextField time = new JTextField();
    SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    // 表头
    Object[] columnNames = {"编号","名称","价格","库存","备注"};
    String[][] cellData = new String[0][5];

    DefaultTableModel model = new DefaultTableModel(cellData, columnNames){
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    // 表格
    JTable table = new JTable(model);

    // 滑动窗口
    JScrollPane scrollPane = new JScrollPane(table);

    // 对象
    FruitDAO fruitDAO = new FruitDAOImpl();

    public MainFrame(){

        // 设置窗口名字
        this.setTitle("水果系统");

        // 设置窗口大小
        this.setSize(700, 600);

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

        /////////////添加控件//////////////////
        panel.setLayout(null);

        // 水果查询
        panel.add(button_query);
        button_query.setFont(new Font("黑体",Font.PLAIN,16));
        button_query.setBounds(520, 100, 120,30);
        button_query.addActionListener((e)->{
            ClearData();
            LoadFruitData();
        });

        // 添加水果
        panel.add(button_add);
        button_add.setFont(new Font("黑体",Font.PLAIN,16));
        button_add.setBounds(520, 170, 120,30);
        button_add.addActionListener((e)->{
            new FruitNameFrame(0);
        });

        // 查看指定水果
        panel.add(button_inquire);
        button_inquire.setFont(new Font("黑体",Font.PLAIN,16));
        button_inquire.setBounds(520, 240, 120,30);
        button_inquire.addActionListener((e)->{
            new FruitNameFrame(1);
        });

        // 下架水果
        panel.add(button_delete);
        button_delete.setFont(new Font("黑体",Font.PLAIN,16));
        button_delete.setBounds(520, 310, 120,30);
        button_delete.addActionListener((e)->{
            new FruitNameFrame(2);
        });

        // 退出按钮
        panel.add(button_close);
        button_close.setFont(new Font("黑体",Font.PLAIN,16));
        button_close.setBounds(520, 380, 120,30);
        button_close.addActionListener((e)->{
            this.dispose();
        });

        // 窗口
        panel.add(scrollPane);
        scrollPane.requestFocus();
        scrollPane.setBounds(30,80,450, 360);

        // 添加时间标签
        panel.add(label_time);
        label_time.setFont((new Font("黑体",Font.PLAIN,16)));
        label_time.setBounds(10, 20, 50,30);

        // 添加时间
        panel.add(time);
        time.setFont((new Font("黑体",Font.PLAIN,16)));
        time.setBounds(70,20,165, 30);
        time.addActionListener(new TimeActionListener());

    }

    // 清处方法
    public void ClearData(){
        if(model.getRowCount()>0){
            for(int i = model.getRowCount() - 1; i > 0;--i){
                model.removeRow(i);
            }
            model.removeRow(0);
        }
    }

    // 加载方法
    public void LoadFruitData(){
        try {
                for (Fruit fruit : fruitDAO.getFruitList()){
                    Vector<String> vector = new Vector<>();
                    vector.addElement( String.valueOf(fruit.getFid()));
                    vector.addElement(fruit.getFname());
                    vector.addElement(String.valueOf(fruit.getPrice()));
                    vector.addElement(String.valueOf(fruit.getFcount()));
                    vector.addElement(fruit.getRemark());
                    model.addRow(vector);
                    System.out.println(model.getRowCount());
                };
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 添加内部类
    // 添加“时间文本框”的事件监听器，用来实现动态刷新时间
    class TimeActionListener implements ActionListener {
        public TimeActionListener(){
            javax.swing.Timer t=new javax.swing.Timer(1000,this);
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent ae){
            time.setText(myfmt.format(new java.util.Date()).toString());
        }
    }
}


