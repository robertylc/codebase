package Others;

public class InnerBean{
    private Integer intVal;
    private String strVal;


    public String getStrVal() {
//        System.out.println("from inner");
        return strVal;
    }
    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }

    public String getInnerStr(){return "innert" +strVal;}

    public Integer getIntVal() {
        return intVal;
    }

    public void setIntVal(Integer intVal) {
        this.intVal = intVal;
    }
}
