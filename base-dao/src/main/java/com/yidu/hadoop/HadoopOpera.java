package com.yidu.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Administrator on 2017/1/3.
 */
public class HadoopOpera {
    public void insertData(){
        System.out.print("aa");
    }
    private static final String DIR_PATH = "/test2";
    private static final String FILE_PATH = "/1.txt";
    private static final String HDFS_PATH = "hdfs://192.168.4.71:9000";
    public static void main(String[] args) throws Exception {
        Configuration conf=new Configuration();
        Path path=new Path(HDFS_PATH+DIR_PATH);
        FileSystem fileSystem = FileSystem.get(URI.create(HDFS_PATH), conf);
//        fileSystem.copyFromLocalFile(new Path("d:/2.txt"),new Path(DIR_PATH));
        fileSystem.access(path, FsAction.ALL);
        //创建文件夹
//        makeDir(fileSystem);
        //上传文件
        uploadData(fileSystem);
        //下载文件
//        downloadData(fileSystem);
        //删除文件
//        deleteData(fileSystem);
    }

    public static void get(){
        try{
            //给URL配置解析器，使其能解析hdfs协议
            URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
            URL url = new URL(HDFS_PATH+DIR_PATH+FILE_PATH);
            InputStream in = url.openStream();
            //将文件的内容copy到控制台
            IOUtils.copyBytes(in, System.out, 1024, true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除文件
     * @param fileSystem
     * @throws IOException
     */
    private static void deleteData(FileSystem fileSystem) throws IOException {
        fileSystem.delete(new Path(DIR_PATH+FILE_PATH), true);
    }

    /**
     * 下载文件
     * @param fileSystem
     * @throws IOException
     */
    private static void downloadData(FileSystem fileSystem) throws IOException {
        FSDataInputStream in = fileSystem.open(new Path(DIR_PATH+FILE_PATH));
        IOUtils.copyBytes(in, System.out, 1024, true);
    }

    /**
     * 创建文件夹
     * @param fileSystem
     * @throws IOException
     */
    private static void makeDir(FileSystem fileSystem) throws IOException {
        fileSystem.mkdirs(new Path(DIR_PATH));
    }

    /**
     * 上传文件
     * @param fileSystem
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static void uploadData(FileSystem fileSystem) throws IOException,
            FileNotFoundException {
        FSDataOutputStream out = fileSystem.create(new Path(DIR_PATH+FILE_PATH));
        InputStream in = new FileInputStream(new File("d:/1.txt"));
        IOUtils.copyBytes(in, out, 1024, true);
    }

}
