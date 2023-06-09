package com.iric.transaction.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Goods {

    @TableId(value = "goods_id",type = IdType.AUTO)
    private Long goodsId;

    private String goodsName;

    private Date createTime;


}
