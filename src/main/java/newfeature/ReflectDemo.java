package newfeature;

import com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ReflectDemo {

    static Map<String,Long> map = new HashMap<String, Long>();
    public static void main(String[] args) throws NoSuchFieldException {
        /* 泛函 */
        Type superClass = map.getClass().getGenericSuperclass();
        Field filed = ReflectDemo.class.getDeclaredField("map");
        System.out.println(superClass.toString());

        Type[] paraTypes = ((ParameterizedType) superClass).getActualTypeArguments();
        Type genericType = filed.getGenericType();
        System.out.println("ReflectDemo.main" + genericType.getTypeName());

        TypeReference<Map<String, InternalError>> test = new TypeReference<Map<String, InternalError>>(){};
        genericType = test.getClass().getGenericSuperclass();
        System.out.printf("");

    }
}
