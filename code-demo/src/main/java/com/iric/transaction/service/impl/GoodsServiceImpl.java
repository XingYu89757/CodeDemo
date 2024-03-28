package com.iric.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iric.transaction.mapper.GoodsMapper;
import com.iric.transaction.model.Goods;
import com.iric.transaction.service.GoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodService {


    @Override
    public void saveGoods(Goods goods) {
        this.save(goods);
    }
}
