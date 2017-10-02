package Others;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by robertpicyu on 2017/9/30.
 1.@Target：PACKAGE-> TYPE -> METHOD-> CONSTRUCTOR ->FIELD......
 2.@Retention : SOURCE->CLASS->RUNTIME
 3.@Documented: 用于doc
 4.@Inherited：注解能被之类继承
    可设默认值
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoAnnotation {
    public int value() default 1;
}
