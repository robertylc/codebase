package newfeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.map.HashedMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by robertpicyu on 2017/9/26.
 */
public class LambdaDemo {
    public static void execute(WorkerInterface worker) {
        worker.doSomeWork("");
    }

    public static void main(String[] args) {

        /**
         * Lambda:  编译器自动构建  “内部匿名类”（不保存this指针？？）
         *
         * 调用格式：
         * 入参 ：
         *      (int a)  ()
         *      (a)  : 支持类型自动推到
         *      a    ：单个入参可不加（）
         * 函数体：
         *      -> {xxxxxx}  ，
         *      -> System.out.println(e) ：只有一条语句时
         *      System.out:printIn ： 只有一条语句时
         */
        LambdaDemo.execute((e) -> { System.out.println(e);});
        LambdaDemo.execute((e) -> System.out.println(e));
        LambdaDemo.execute(System.out::print);
        List test = new LinkedList();
        test.stream().forEach(System.out::println);

        Map<String, Object> keyValues = new HashedMap();
        keyValues.forEach((key,val)->test.size());  // ?? 能访问test变量，内部变量！

        /**
         * 一些已定义的lambda 接口：
         */


    }
}

@FunctionalInterface
interface WorkerInterface {

    public void doSomeWork(String e);

}
