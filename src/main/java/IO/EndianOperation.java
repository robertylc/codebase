package IO;

import org.apache.commons.io.EndianUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class EndianOperation
{
    /* 大小端转换 */
    public static void main(String[] args) {
        try {
            System.out.printf(String.format("%H\n",EndianUtils.swapLong(Integer.valueOf("ff",16))));

            int result = EndianUtils.readSwappedInteger(IOUtils.toInputStream("1052688", "utf-8"));
            System.out.printf(result+"\n");
            System.out.printf(String.format("%H\n",result));
            //EndianUtils.writeSwappedFloat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
