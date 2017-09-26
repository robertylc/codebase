package IO;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputAndOutputDemo {
    /* 主要是Apache Commons 相关工具包使用 */
    private void demo(){
        try {
            /** read :
             *       来源 ：(file\memory);
             *       编码：byte\char ;
             *       格式: print
             */
            InputStream in = null;
            in = new FileInputStream("");
            in = new ByteArrayInputStream(new byte[]{});
            in.read();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
            bufferedInputStream.read(new byte[]{},0,100);
            DataInputStream dataInputStream = new DataInputStream(in);
            dataInputStream.readLong();
            dataInputStream.readFloat();

            Reader reader = new FileReader("xxx");
            reader = new StringReader(String.valueOf(""));
            reader = new InputStreamReader(in);
            reader.read();
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.readLine();
            Scanner scanner = new Scanner(in);// == Scanner(new InputStreamReader(in));
            scanner = new Scanner(reader);
            scanner.next("xxxx");

            /** write:
             *       来源 ：(file\memory);
             *       编码：byte\char ;
             *       格式: print
             *       */
            OutputStream out = null;
            out = new FileOutputStream("");
            out = new ByteArrayOutputStream();
            BufferedOutputStream bout = new BufferedOutputStream(out);
            out.write(new byte[]{},0,200);
            bout.write(new byte[]{},0,200);
            bout.flush();
            DataOutputStream dataOutputStream = new DataOutputStream(bout);
            dataOutputStream.writeDouble(2.3);

            Writer writer = null;
            writer = new FileWriter("");
            writer = new StringWriter();
            BufferedWriter bwrite = new BufferedWriter(writer);
            writer.write("");
            bwrite.newLine();
            PrintWriter printWriter = new PrintWriter(out);
            printWriter.format("","");


            /** IOUtils的使用：
             强化：
                read：一次性全部读取；返回一段数据(bytes\chars)；返回List<String>
                write: byte[]、list 全写入
                close: 可靠关闭，可重复调用  ;
             构建input、output: 从String、已有input中，还可限制长度
             添加功能：
                copy、compare
             */
            IOUtils.toByteArray(reader,"UTF-8");
            IOUtils.toString(in,"UTF-8");
            IOUtils.readFully(in,100000);
            IOUtils.readLines(reader);

            IOUtils.write("".toCharArray(),writer);
            IOUtils.writeLines(new ArrayList<>(),null, writer);

            IOUtils.toInputStream("test","UTF-8");
            IOUtils.toBufferedReader(reader,100);

            IOUtils.buffer(reader);
            IOUtils.closeQuietly();
            IOUtils.contentEquals(in,in);
            IOUtils.copy(in,new ByteArrayOutputStream());
            IOUtils.copyLarge(in,new ByteArrayOutputStream());

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }


    public static void main (String[] a){

    }
}
