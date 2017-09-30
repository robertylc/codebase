package DataStructDemo;

import Others.DerivedBean;
import Others.InnerBean;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class StreamDemo {
    public static void main(String[] args) {
        /**
         * stream 使用流程：  创建  --》  transfer  --》  reduce
         * 创建：.stream()
         * 转换：
         *      filter（）
         *          distinct（）
         *          limit（）
         *          skip（）
         *      map（）：
         *  reduce：
         *      新的list： collect.toList();  collect.toSet();
         *      新的Map：groupingBy()
         *      聚合到一个值: reduce
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

        /* 聚合得到Set/list */
        nums.stream().filter(num -> num != null).
                distinct().
                skip(2).
                limit(4).collect(toSet());

        /* 聚合得到Map */
        List<InnerBean> targets = null;
        InnerBean b1 = new InnerBean();
        InnerBean b2 = new InnerBean();
        InnerBean b3 = new InnerBean();
        b1.setStrVal("group1");
        b1.setIntVal(100);
        b2.setStrVal("group2");
        b2.setIntVal(100);
        b3.setStrVal("group1");
        b3.setIntVal(100);
        targets = Arrays.asList(b1,b2,b3);
        Map<String, List<InnerBean>> resultMap = targets.stream().filter(num -> num != null).
                distinct().
                limit(4).
                collect(groupingBy(InnerBean::getStrVal));

        resultMap.entrySet().forEach(
                        ele -> {
                            System.out.println(ele.getKey());
                            ele.getValue().forEach(System.out::println);
                        }
        );

        /** reduce() 的三个重载版本：
         *  1、reduce(T) ： T 类型已经被stream（）限制了
         *  2、reduce（T, accumulator<T></>） : T 也被限制了，第一个参数作为一个累加初始化值
         *  3、reduce（U sum，（U，T），（U，U））：U 为T的一个容器, stream做了以下操作：
         *      sum.add(T)
         */
        targets.stream().reduce((a,b)->{         // a,b 都已经被限制为类型InnerBean
            a.setIntVal( a.getIntVal() + b.getIntVal());
            return a;
        });

        InnerBean c = new InnerBean();
        c.setIntVal(0);
        targets.stream().reduce(c,(a,b)->{         // a,b,c 都已经被限制为类型InnerBean
            a.setIntVal( a.getIntVal() + b.getIntVal());
            return a;
        });

        DerivedBean sumBean = new DerivedBean();
        targets.stream().reduce(sumBean,(a,b)->{         // a,b,c 都已经被限制为类型InnerBean
            a.setIntVal( a.getIntVal() + b.getIntVal());
            return a;
        },(sum1,sum2)->{
            sum1.setIntVal(
                    sum1.getIntVal() + sum2.getIntVal()
            );
            return sum1;
        });

    }
}
