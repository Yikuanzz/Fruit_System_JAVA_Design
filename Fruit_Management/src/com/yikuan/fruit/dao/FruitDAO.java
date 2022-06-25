package com.yikuan.fruit.dao;

import com.yikuan.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    // 1.查询库存列表
    List<Fruit> getFruitList();

    // 2.新增库存
    boolean addFruit(Fruit fruit);

    // 3.修改库存
    boolean updateFruit(Fruit fruit);

    // 4.根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    // 5.删除特定库存记录
    boolean delFruit(String fname);
}
