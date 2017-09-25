package Others;

public class SupperBean {
    private String  supperStr;
    private String A = "inner a";
    public String getSupperStr() {
        return supperStr;
    }

    public void setSupperStr(String supperStr) {
        this.supperStr = supperStr;
    }
    public String getA() {
        System.out.println("from inner :");
        return A;
    }
}
