package newfeature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by robertpicyu on 2017/9/21.
 */
public class reflectDemo {
    public static void main(String[] args) {

    }

    @Target(ElementType.PACKAGE)
    public @interface DemoAnnotation{}
}

class DemoBean{
    private String strVal;
    private int intVal;
    private Integer integerVal;
    private InnerBean objVal;

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
}

class InnerBean{
    private String strVal;

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }
}


