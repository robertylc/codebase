package newfeature;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by robertpicyu on 2017/9/25.
 */
public class BeanUtilsDemo {
    public static void main(String[] args) {
        //BeanUtils.setProperty();
        //BeanUtils.getProperty();
        //BeanUtils.cloneBean();          // 复杂数据类型的field(array\list\map)不会clone，支持深度clone。
        //BeanUtils.copyProperties();
        //BeanUtils.copyProperty(null,null,null);

        //PropertyUtils.copyProperties();  // 复杂数据类型(array\list\map)会抛异常。

        //ConvertUtils.convert() // 支持array类型 和 primate 类型
    }
}
