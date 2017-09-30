package MultiThread;

import Others.DerivedBean;

/**
 * Created by robertpicyu on 2017/9/28.
 */
public class AvoidCompetitionDemo {
    public static void main(String[] args) {
        /**
         * 避免 多线程竞争的几个手段：
         * 1、线程共享变量: threadLocal:
         *
         */

        /** 注意避免内存泄露:
         *  1、框架中有使用线程池： 一个线程被重复使用
         */
        ThreadLocal<DerivedBean>  threadLocal = new ThreadLocal();
        DerivedBean oldVal = threadLocal.get();
        threadLocal.set(oldVal);
    }
}
