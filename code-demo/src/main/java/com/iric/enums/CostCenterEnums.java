package com.iric.enums;

import lombok.Getter;

public class CostCenterEnums {

    //是否有效数据,0:否，1是
    @Getter
    public enum Enabled implements CommonEnum {
        FALSE(0, "否"),
        TRUE(1, "是");

        private Integer code;
        private String msg;

        Enabled(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
