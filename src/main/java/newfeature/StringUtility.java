package newfeature;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertpicyu on 2017/9/22.
 */
public class StringUtility {

    static public String[] getLines(String string){
        String[] result;

        /* 多种方式分行 1: bufferReader / scanner*/
        List<String> lines = new ArrayList<String>();
        String line = null;
        StringReader reader = new StringReader(string);
        BufferedReader breader = new BufferedReader(reader);
        try {
            while ((line = breader.readLine()) != null){
                lines.add(line);
            };
        } catch (IOException e) {
        }
        result = lines.toArray(new String[]{});

        /* 2、String.split: 缺点，换行可能有遗漏，换行符有三种 \n  \r  \n\r*/
        string.split(System.getProperty("line.separator"));

        /* 3、IOUtile */
        try {
            List<String> csvLines = IOUtils.readLines(new StringReader(string));
        } catch (IOException e) {
            // impossible here!
        }

        return result;
    }
    public static void main(String[] args) {
        String foo = System.getProperty("line.separator");
        System.out.printf("start: \n" +System.getProperty("line.separator") + "\nend");
    }
}
