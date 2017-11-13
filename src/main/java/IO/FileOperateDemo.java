package IO;

import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileOperateDemo {
    private void demo(){

        try {
            /* apache FileUtils:
              * 1、copy、move、compare
              * 2、property
              * 3、create、delete；  open、close；  read、write
              * */
            File file = new File("/commons/io/project.properties");

            /**
             * creat:
             *      创建文件
             *      创建多级目录；
             *      创建多级父目录
             * delete:
             *      时间：  是否jvm退出时才删除
             *      是否异常： 是否抛异常
             */
            FileUtils.touch(file);
            FileUtils.forceMkdir(file);
            FileUtils.forceMkdirParent(file);

            FileUtils.forceDelete(file);
            FileUtils.deleteQuietly(file);  // no exception!
            FileUtils.forceDeleteOnExit(file);

            /**
             * read\write :
             *      file <-> (byte[]、String、String[])
             */
            FileUtils.readFileToByteArray(file);
            FileUtils.readFileToString(file,"UTF-8");
            List lines = FileUtils.readLines(file, "UTF-8");


            /**
             *  compare：文件内容比较
             *  copy 操作：
             *      dir-->dir、
             *      file-->dir、  file--> stream、 file-->file、
             *      stream-->file、 stream-->stream;
             *  move 移动：
             *      (dir * file) <--->  (dir * file)
             */
            FileUtils.contentEquals(file,file);
            FileUtils.copyDirectory(new File(""),new File(""));
            FileUtils.copyFile(new File(""),new File(""));
            FileUtils.copyFileToDirectory(new File(""),new File(""));
            FileUtils.copyToFile(IOUtils.toInputStream("test","UTF-8"),new File(""));

            FileUtils.moveDirectory(file,file);

            /**
             * 属性：
             *      file or dir
             *      name
             *      大小
             *      符号链接
             *      创建时间 : older、newwer
             *      权限：可读、可写、隐藏
             *  列出 或者 遍历多个文件 : （file + dirs）
             *      name：name_array、 prefix_array、suffix_array、正则表达式、通配符
             *      其它属性：权限、创建时间、大小
             *      多个条件组合： OrFileFilter（）、NotFileFilter
             */
            FileUtils.sizeOf(file);
            FileUtils.isFileNewer(file,System.currentTimeMillis());
            FileUtils.isSymlink(file);

            FileUtils.listFiles(file, Arrays.asList("java","xml").toArray(new String[]{}),false);
            IOFileFilter fileFilter = new NameFileFilter("");
            FileUtils.listFilesAndDirs(file,null,null);
            FileUtils.iterateFilesAndDirs(file,null,null);

            ThreadLocal threadLocal = new ThreadLocal();
            threadLocal.get();


            /**
             * file name 工具：
             *      1、去掉路径中的 ../. 等符号。
             *      2、兼容unix 和 windowns 的 / \\
             */
            FilenameUtils.normalize("c://path/to/somewhere");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        /**
         * 几个路径：
         * 1、user.dir、user.tmp。
         * 2、project root。
         * 3、class root。
         */
        System.out.println(Paths.get("").toAbsolutePath());

    }
}
