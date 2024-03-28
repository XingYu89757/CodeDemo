package com.iric.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iric.transaction.model.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper  extends BaseMapper<Goods> {
}
