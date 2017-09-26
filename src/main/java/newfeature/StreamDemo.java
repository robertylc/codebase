package newfeature;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class StreamDemo {
    public static void main(String[] args) {
        /**
         * stream 使用流程：  创建  --》  transfer  --》  reduce
         * 创建：
         *
         * 转换（过滤）：
         *      filter（）
         *          distinct（）
         *          limit（）
         *          skip（）
         *      map（）
         *
         *  reduce：
         *      新的list： collect（）
         *      聚合
         */

        List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);

        /* 得到一个聚合值  */
        System.out.println("sun is : " + nums.stream().filter(num -> num != null).
                distinct().
                skip(2).
                limit(4).
                peek(System.out::println).
                mapToInt(num -> num * 2).
                peek(System.out::println).   // foreach() 返回的就不是stream了，
                sum());

        /* 聚合得到Set */
        nums.stream().filter(num -> num != null).
                distinct().
                skip(2).
                limit(4).collect(toSet());

        nums.stream().filter(num -> num != null).
                distinct().
                skip(2).
                limit(4).collect()

    }
}
