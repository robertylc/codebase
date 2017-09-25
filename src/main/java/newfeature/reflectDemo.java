package newfeature;


import Others.DerivedBean;
import Others.InnerBean;
import newfeature.DemoBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by robertpicyu on 2017/9/21.
 */
public class reflectDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        DerivedBean demo = new DerivedBean();
        Method method = DerivedBean.class.getMethod("getA");
        System.out.println(method.invoke(demo));
        Field field = DerivedBean.class.getDeclaredField("A");
        field.setAccessible(true);
        System.out.println(field.get(demo));

        method = DerivedBean.class.getSuperclass().getMethod("getA");
        System.out.println(method.invoke(demo));
        field = DerivedBean.class.getSuperclass().getDeclaredField("A");
        field.setAccessible(true);
        System.out.println(field.get(demo));

    }

    @Target(ElementType.PACKAGE)
    public @interface DemoAnnotation{}
}

class DemoBean{
    private String strVal;
    private int intVal;
    private Integer integerVal;
    private InnerBean objVal;

    private String A = "derived a";

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public Integer getIntegerVal() {
        return integerVal;
    }

    public void setIntegerVal(Integer integerVal) {
        this.integerVal = integerVal;
    }

    public InnerBean getObjVal() {
        return objVal;
    }

    public void setObjVal(InnerBean objVal) {
        this.objVal = objVal;
    }

    public String getA() {
        System.out.println("from derive :");
        return A;
    }
}
