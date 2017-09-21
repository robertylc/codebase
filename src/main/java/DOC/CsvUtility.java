package DOC;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by robertpicyu on 2017/9/21.
 */
public class CsvUtility {
    public static void main(String[] args) {
        String csvpath = "E:\\private_project\\codebase\\src\\main\\resource\\demo.csv";
        FileReader reader = null;
        try {
            reader = new FileReader(csvpath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader breader = new BufferedReader(reader);

        try {
            for(String s = breader.readLine(); StringUtils.isNotEmpty(s); s = breader.readLine()) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
