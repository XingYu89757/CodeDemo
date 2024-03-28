package com.iric.util;


import org.apache.commons.text.StringEscapeUtils;

/**
 * @Author Yu.Xing
 * @Description 解决Xss攻击
 **/
public class XssUtils {
    public static void main(String[] args) {
        //org.apache.commons.lang3.StringEscapeUtils会把中文也转义(已废弃该方法，最新的包为commons-text)
        String str = StringEscapeUtils.unescapeHtml4("&lt;p&gt;打发 发顺丰&lt;/p&gt;");
        //获取数据库数据，相当于解码反转译
        System.out.println(str);
        String str2 = StringEscapeUtils.escapeHtml4("<p>打发 发顺丰</p>");
        //获取富文本编辑器数据，相当于过滤转译，防止跨站xss攻击
        System.out.println(str2);

        //方式二
        //spring的org.springframework.web.util.HtmlUtils.htmlEscape 不会转义中文
       // System.out.println( HtmlUtils.htmlEscape("<font>打发 发顺丰 xing</font>"));

    }
}
