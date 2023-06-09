package com.iric.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.iric.transaction.model.Goods;

public interface GoodService extends IService<Goods> {

    void saveGoods(Goods goods);
}
