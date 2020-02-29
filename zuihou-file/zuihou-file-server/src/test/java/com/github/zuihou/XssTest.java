package com.github.zuihou;

import com.github.zuihou.xss.utils.XssUtils;

/**
 * This is a Description
 *
 * @author zuihou
 * @date 2019/11/19
 */
public class XssTest {

    public static void main(String[] args) {
        System.out.println(XssUtils.xssClean("<script>123</script> <admin>123</admin> div=<div onclick=\"alert(1)\" data-id=\"123\" style=\"color:red;\">123</div>, p=<p>pppp</p>, a=<a href=\"http://baidu.com\">dian</a> br=<br/>  b=<br>", null));
    }
}
