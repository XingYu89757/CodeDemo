package com.iric.util.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

@Data
public class MenuVo {
    private Long id;
    private Long pId;
    private String name;
    private Integer rank=0;
    private List<MenuVo> subMenus=new ArrayList<>();

    public MenuVo(Long id, Long pId){
        this.id = id;
        this.pId = pId;
    }
}
