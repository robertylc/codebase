package Others;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by robertpicyu on 2017/10/23.<br/>
 * Bean的一些常用操作：<br/>
 * 1、属性传递 ：
 *  同名属性 复制 或者 累加操作<br/>
 * 2、属性筛选 ：
 * 3、属性格式变换
 */
public class BeanOperations extends BeanUtils{
    public static void main(String[] args) {
        InnerBean sourBean = new InnerBean();
        sourBean.setIntVal(100);
        InnerBean2 targetBean = new InnerBean2();
        try {
            BeanUtils.copyProperties(targetBean,sourBean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
