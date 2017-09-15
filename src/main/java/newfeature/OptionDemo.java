package newfeature;

import java.util.Optional;

/**
 * Created by robertpicyu on 2017/8/31.
 */
public class OptionDemo {
    /**
     * Optional 主要用于替换掉<s>多级</s>的 if（obj == null），使代码更干净
     */
    public static void main(String[] args) {

        /**
         1、替换
         if (obj == null){
         throws new Exception();
         }
         */
        Optional<String> optNotNull = Optional.of("test");
        Optional<String> optNullable = Optional.ofNullable("test");


        /**
         2、替换
         if (obj ！= null){
            doWork(obj)
         }
         */
        optNullable.ifPresent((val)-> {System.out.println(val);});

        /**
         3、替换
         if (obj ！= null){
            return obj;
         }else{
            return defautVal;
            or throw newe Exception();
         }
         */
        System.out.println(optNullable.orElse("There is no value present!"));
        System.out.println(optNullable.orElseThrow(RuntimeException::new));

        /**
         4、替换
         if (obj ！= null){
            foobar = mapper(obj);
            if(foobar != null){
                do(foobar)
            }else{
                do(dafautValue)
                or throw new Exception();
            }
         }
         */
        Optional<String> foobar = optNullable.map((value) -> value.toUpperCase());
        System.out.println(foobar.orElse("defaultValue"));

        /**
         4、替换
         if (foobar ！= null){
             if(foobar != null && filter(foobar)){
                 do(foobar)
            }else{
                do(dafautValue)
                or throw new Exception();
            }
         }
         */
        Optional<String> test = optNullable.filter((value) -> value.length() > 10);
        System.out.println(test.orElse("defaultValue"));
    }
}
