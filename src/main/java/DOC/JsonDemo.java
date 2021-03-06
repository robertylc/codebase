package DOC;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by robertpicyu on 2017/8/31.
 */
public class JsonDemo {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        DemoBean bean = new DemoBean();
        bean.setObjVal(new InnerBean());
        String jsonStr = objectMapper.writeValueAsString(bean);
        System.out.printf(jsonStr);
    }
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
