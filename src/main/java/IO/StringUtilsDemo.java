package IO;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by robertpicyu on 2017/9/25.
 */
public class StringUtilsDemo {
    public static void main(String[] args) {
        /**
         * 整理下StringUtilis的常用方法:
         *  1、多字符拼接： join() \ repeate()
         *
         *  2、分：
         *      拆分：splite
         *      截取：mid，substring()
         *      删除：首尾删除、子串删除
         *
         *  3、修改：
         *      首\尾添加( 循环添加 )： center\ leftpad \rightpad
         *      省略 ：abcdefg  ->  abc....
         *      剔除： 空格，字符集，
         *      替换：
         *
         *  3、大、小写转换：
         *      capital（）
         *      upperCase()
         *      switchCase()
         *
         *  4、字符检查:
         *      范围：empty\alpha\numeric\whitespace\upperCase
         *      子串： contains
         *      字符集： containsAny，containsOnly
         *      首尾：startWith
         *      位置检索：字符串，字符集合
         */
        StringUtils.join("","","");
        StringUtils.join(ArrayUtils.toArray("a","b","c"),"--");     // a-b-c
        StringUtils.repeat("aaa",5);

        StringUtils.strip("  ddd");
        StringUtils.strip("aaaabbb1c3cc","13");  //去掉 1、3 ,第二个参数是字符集！

        StringUtils.removeStart("start dddd","start");
        StringUtils.remove("start dddd","start");

        StringUtils.center("aaaa",8,"-");     // --aaaa--
        StringUtils.leftPad("aaaa",8,"-");     // ----aaaa
        StringUtils.abbreviate("aabbccee",6);

        StringUtils.indexOf("sssss","target");
        StringUtils.indexOfAny("sssss","target");

        StringUtils.defaultIfBlank("","empty value");

    }
}
