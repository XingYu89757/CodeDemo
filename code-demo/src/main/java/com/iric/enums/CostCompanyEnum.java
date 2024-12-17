package com.iric.enums;
import lombok.Getter;

@Getter
public enum CostCompanyEnum {
    BeiJing("2", "北京以岭药业有限公司"),
    DaYunHe("02", "河北大运河医药物流有限公司"),
    ShiJiaZhuang("1", "石家庄以岭药业股份有限公司");

    private String code;
    private String label;

    CostCompanyEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }
}
