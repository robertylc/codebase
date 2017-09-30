package newfeature;

import Others.DerivedBean;
import Others.InnerBean;
import Others.SupperBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilityDemo {
    public static void main(String[] args)  {


        /**
         *
         */
        DerivedBean derivedBean = new DerivedBean();
        derivedBean.setInnerBean(new InnerBean());
        SupperBean supperBean = new SupperBean();
        supperBean.setSupperStr("new supper string");
        try {
            Object newBean = BeanUtils.cloneBean(derivedBean);        // 深度clone
            BeanUtils.copyProperties(newBean,supperBean);
            newBean.toString();
//          BeanUtils.populate();
//          BeanUtils.
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        /**
         * bean property set and get operation
         */
        try {
            PropertyUtils.getProperty(null,null);
            PropertyUtils.setProperty(null,null,null);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}




