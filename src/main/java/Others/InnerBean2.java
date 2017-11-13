package Others;

public class InnerBean2 {
    private Integer intVal;
    private String strVal;
    private String newField;

    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

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
