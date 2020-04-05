import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Tools {
    public static void main(String[] args) throws IOException {
      //  System.out.println(File.separator);
        String zipPath = "C:\\Users\\zhang\\Desktop\\23456.zip";
        unZipFiles(zipPath);
    }


    /**
     * @param zipPath 解压文件
     */
    // 实现一个解压缩zip文件的方法，

    //输入：zip文件路径

    // 输出：zip中的文件列表，结果按文件名字典序排序

    // 约束：解压后文件总大小不能超过20M，单个文件大小不能超过10M，文件个数不能超过50个（文件夹也包含在计数中），超出约束条件就抛出异常
    public static void unZipFiles(String zipPath) throws IOException {

        final float maxFileSize = 20 * 1024 * 1024;//20m
        final float maxsingleFileSize = 10 * 1024 * 1024;//10m
        final int maxTotalNum = 50;//文件个数不能超过50个
        String descDir = zipPath.substring(0, zipPath.lastIndexOf("."));//解压后的文件存放地址

        int count = 0;//统计文件个数
        ArrayList<String> fileNames = new ArrayList<String>();//文件名

        try {
            File zipFile = new File(zipPath);
            // System.err.println(zipFile.getName());
            if (!zipFile.exists()) {
                throw new IOException("需解压文件不存在.");
            }
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            ZipFile zip = new ZipFile(zipFile, Charset.forName("GBK"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                count++;
                if (count > maxTotalNum) {
                    throw new Exception("约束: (1)解压后文件总大小不能超过20M (2)单个文件大小不能超过10M (3)文件个数不能超过50个（文件夹也包含在计数中）,超出约束条件就抛出异常");
                }
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                //  fileName.add(zipEntryName);
                //  System.err.println(zipEntryName);
                InputStream in = zip.getInputStream(entry);
                String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\*", "/");
                //  System.err.println(outPath);
                // 判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }

                //  System.err.println(zipEntryName.substring(zipEntryName.lastIndexOf("/")+1));
                fileNames.add(zipEntryName.substring(zipEntryName.lastIndexOf("/") + 1));

                // 输出文件路径信息
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
              //  System.err.println((new File(outPath)).length());

                if((new File(outPath)).length()>maxsingleFileSize){
                    throw new Exception("约束: (1)解压后文件总大小不能超过20M (2)单个文件大小不能超过10M (3)文件个数不能超过50个（文件夹也包含在计数中）,超出约束条件就抛出异常");
                }
            }
            if(fileNames.size()>0){
                //排序输出
                Collections.sort(fileNames);
                for (String fileName:fileNames)
                    System.out.println(fileName);
            }

        } catch (Exception e) {
            throw new IOException(e);
        }
    }


}
