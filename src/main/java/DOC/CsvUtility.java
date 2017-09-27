package DOC;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by robertpicyu on 2017/9/21.
 */
public class CsvUtility {
    /**
     * 解析CSV文件，得到bean list
     * @param csvData : csv文件的所有数据，字符串格式
     * @param cls ：Bean class，field目前支持String、Integer、Double、Long，其它类型自己添加
     * @param <T>
     * @return
     */
    static  public <T> List<T> getObjectList(String csvData, Class<T> cls) {
        List<T> result = null;
        List<String> csvLines = null;
        try {
            csvLines = IOUtils.readLines(new StringReader(csvData));
        } catch (IOException e) {
           // impossible here!
        }
        List<Entry<Field, Integer>> fields = getFieldsOnOrder(cls);
        for (String line : csvLines) {
            String[] fieldStrs = line.split("\t");

            T obj = null;
            try {
                obj = cls.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }

            for (Entry<Field, Integer> entry : fields) {
                Field field = entry.getKey();
                Integer order = entry.getValue();
                if (order < fieldStrs.length) {
                    Object val = getValueFromString(fieldStrs[order], field.getType());
                    field.setAccessible(true);
                    try {
                        field.set(obj, val);
                    } catch (IllegalAccessException e) {
                    }
                }
            }
            result.add(obj);
        }
        return result;
    }

    static  public <T> T getObject(String cvsData, Class<T> cls) {
        T obj = null;
        try {
            obj = cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        String[] fieldStrs = cvsData.split("\t");
        List<Entry<Field, Integer>> fields = getFieldsOnOrder(cls);
        for (Entry<Field, Integer> entry:fields){
            Field field = entry.getKey();
            Integer order = entry.getValue();
            if (order < fieldStrs.length){
                Object val = getValueFromString(fieldStrs[order],field.getType());
                field.setAccessible(true);
                try {
                    field.set(obj,val);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    static private <T> List<Entry<Field,Integer>> getFieldsOnOrder(Class<T> cls){
        Field[] fields = cls.getDeclaredFields();
        List<Entry<Field,Integer>> result = new ArrayList<Entry<Field,Integer>>();
        for (Field field:fields){
            CvsFieldOrder order = field.getAnnotation(CvsFieldOrder.class);
            Entry<Field, Integer> entry = new AbstractMap.SimpleEntry<Field, Integer>(field,order.value());
            result.add(entry);
        }
        return result;
    }

    static private <T> T  getValueFromString(String str, Class<T> cls){
        T result = null;
        if (String.class.isAssignableFrom(cls)){
            result = (T)str;
        }else if (Integer.class.isAssignableFrom(cls)){
            result = (T) Integer.valueOf(str);
        }else if (Long.class.isAssignableFrom(cls)){
            result = (T) Long.valueOf(str);
        } else if(Double.class.isAssignableFrom(cls)){
            result = (T) Double.valueOf(str);
        }
        return result;
    }


    public static void main(String[] args) {
        //StringUtils;
        //stream
        String csvLine = "9999\tteststring\t333";
 //       TestBean bean = CsvUtility.getObject(csvLine,TestBean.class);

//        String csvpath = "E:\\private_project\\codebase\\src\\main\\resource\\cld_ppyun_test_cld_live80.csv";
//        String csvpath = "E:\\private_project\\codebase\\src\\main\\resource\\cld_ppyun_channel_5min_flowbw_uvvvwt_2file.csv";
        String csvpath = "E:\\private_project\\codebase\\src\\main\\resource\\cld_ppyun_test_cld_vod80_2.csv";
//        String csvpath = "E:\\private_project\\codebase\\src\\main\\resource\\demo_vod.csv";

        FileReader reader = null;
        try {
            reader = new FileReader(csvpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader breader = new BufferedReader(reader);

        try {
            List<String> lines = FileUtils.readLines(new File(csvpath), "UTF-8");

            lines.stream().filter(
                    s->{
                        String[] segs = s.split("\t");
                        String hour = segs[1];
                        String min = segs[2];
                        String cid = segs[5];
                        if (cid.equals("1141") && hour.equals("22") && min.equals("15")) {
                            return true;
                        }
                        else {
                            return false;
                        }
                    }).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 列值 : 0 ~ n
 */
@Retention(RetentionPolicy.RUNTIME)
@interface CvsFieldOrder {
    int value();
}

class TestBean{
    @CvsFieldOrder(1)
    private String strVal;
    @CvsFieldOrder(3)
    private Long longVal;
    @CvsFieldOrder(2)
    private Integer integerVal;
    @CvsFieldOrder(0)
    private Double doubleVal;
}

