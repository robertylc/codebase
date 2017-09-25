package Others;

public class DerivedBean extends SupperBean{
    private String strVal;
    private int intVal;
    private Integer integerVal;
    private String A = "derived a";
    private InnerBean innerBean;

    public String getA() {
        System.out.println("from derive :");
        return A;
    }

    public String getStrVal() {
        System.out.println("from derived");
        return strVal;
    }

    public InnerBean getInnerBean() {
        return innerBean;
    }

    public void setInnerBean(InnerBean innerBean) {
        this.innerBean = innerBean;
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

}

