package com.yikuan.view;

import com.yikuan.fruit.controller.Menu;

public class Client {
    public static void main(String[] args) {
        Menu m = new Menu();

        boolean flag = true;
        // 调用显示主菜单
        while (flag){
            int slt = m.showMainMenu();
            switch (slt){
                case 1:
                    // 显示库存列表
                    m.showFruitList();
                    break;
                case 2:
                    // 添加水果库存信息
                    m.addFruit();
                    break;
                case 3:
                    // 查看特定水果库存信息
                    m.showFruitInfo();
                    break;
                case 4:
                    // 水果下架
                    m.delFruit();
                    break;
                case 5:
                    flag = m.exit();
                    break;
                default:
                    System.out.println("你不按套路出牌！");
                    break;
            }
        }
        System.out.println("谢谢使用！");
    }
}
