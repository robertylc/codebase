package DataStructDemo;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Created by robertpicyu on 2017/9/28.
 */
public class CollectionDemo {
    public static void main(String[] args) {

        /**
         * 快速创建Map、list、set（）
         */

        /**
         *  list <--> array
         */
        String[] strings = "seg.seg2.seg3".split(".");
        List<String> list = Arrays.asList(strings);
        list = Lists.newArrayList(strings);
        list.toArray(new String[list.size()]);

    }
}
