package IO;

import net.sf.json.regexp.RegexpUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by robertpicyu on 2017/9/25.
 */
public class StringUtilsDemo {

    public static String[] findAll(String str, String regStr){
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(str);
        List<String> result = new LinkedList<String>();
        while (matcher.find()) {    // 游标下移动
            result.add(matcher.group());
        }
        return result.toArray(new String[]{});
    }
    public static void main(String[] args) {
        /**
         * 整理下StringUtilis的常用方法:
         * 1、字符串属性判断：
         *      范围：empty\alpha\ numeric\ whitespace\  upperCase
         *      子串： contains
         *      字符集：containsAny，containsOnly
         *      首尾：startWith
         *      位置检索：字符串，字符集合
         *
         * 2、字符串修改类操作：
         *      添加操作：
         *          首、尾添加：left、rightPad()
         *          中间插入：StringBuilder中的方法
         *      删除：
         *          首尾删除
         *      替换：
         *          大、小写转换：
         *              capital（）、 upperCase()、switchCase()
         *          子串替换：
         *              replace()
         *          正则表达式：（使用String中的方法）
         *              math判断:
         *              splite():
         *              提取：
         *              替换：replace（），支持正则表达式分组替换（组号$n）;
         *
         * 3、其它：
         *      字符拼接： join() \ repeate()
         *      拆分：splite
         *      截取：首、尾、中间截取，mid，substring()
         */

        /**
         *  regular :
         *      匹配、提取、拆分、替换
         *  子串互相换 :
         *      repalce("(...)(s2Reg)(...)(s2Reg)(...)","$1$4$3$2$5")
         *   采用简写：
         *      \d、\w、\s（空格）、^ $（首尾）
         *   几个非捕获组：
         *      位置锚定：
         *          (?=)和(?<=)：前向锚定、后向锚定
         *          (?!X) (?<!X)：否锚定
         *          (?:) ： 非捕获，区别是会消费掉字符！！
         *  */
        String regTest = "serg1.999.seg2.4444.seg3";
        regTest.matches(".*\\d+.*");
        String[] target = regTest.split("\\d{2,}");
        target = findAll(regTest,"\\d{2,}");
        regTest = regTest.replaceAll("\\d{2,}(\\.\\w+)","$1");


        /* 连接、分割 */
        StringUtils.join("","","");
        StringUtils.join(ArrayUtils.toArray("a","b","c"),"--");     // a-b-c
        StringUtils.repeat("aaa",5);
        StringUtils.split("serg1.999.seg2.4444.seg3","4444");    //不支持正则表达式
        StringUtils.strip("  ddd");
        StringUtils.strip("aaaabbb1c3cc","13");  //去掉 1、3 ,第二个参数是字符集！


        /* 修改操作：添加、删除、替换 */
        StringUtils.center("aaaa",8,"-");     // --aaaa--
        StringUtils.leftPad("aaaa",8,"-");     // ----aaaa
        StringUtils.rightPad("aaaa",8,"-");
        StringBuilder stringBuilder = new StringBuilder("");  // 缺少insert操作，可以用stringbuilder 替换
        stringBuilder.insert(5,"hhhh");
        StringUtils.removeStart("start dddd","start");
        StringUtils.remove("start dddd","start");
        regTest = StringUtils.strip("  aa bbb  ");
        regTest = StringUtils.trim("  aaa bbb  ");
        StringUtils.replace("","","");

        /* 其它:   大、小写  */
        StringUtils.abbreviate("aabbccee",6);  // aab...
        StringUtils.upperCase("");
        StringUtils.lowerCase("");
        StringUtils.capitalize("");
        StringUtils.swapCase("");
        StringUtils.defaultIfBlank("","empty value");

        /* 属性操作： */
        StringUtils.indexOf("ssabsss","b");
        StringUtils.indexOfAny("sssss","target");
        StringUtils.contains("", "");
        StringUtils.containsAny("", "");    //字符集判断
        StringUtils.isAlpha("");

    }
}
