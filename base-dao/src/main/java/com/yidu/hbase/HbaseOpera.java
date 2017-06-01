package com.yidu.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/1/20.
 */
public class HbaseOpera {
    public static Configuration configuration;
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("hbase.zookeeper.quorum", "192.168.4.71");
        configuration.set("hbase.master", "192.168.4.71:60000");
    }
    public static void main(String [] args){
        String [] str=new String[]{"ability_id"};
        writeRow("yxtk_collection",str);
        selectRow("yxtk_collection","rows1");
    }

    /**
     * 插入一行记录
     * @param tablename
     * @param cfs
     */
    public static void writeRow(String tablename, String[] cfs) {
        try {
            HTable table = new HTable(configuration, tablename);
            Put put = new Put(Bytes.toBytes("rows1"));
            for (int j = 0; j < cfs.length; j++) {
                put.add(Bytes.toBytes(cfs[j]),
                        Bytes.toBytes(String.valueOf(1)),
                        Bytes.toBytes("value_1"));
                table.put(put);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一行记录
     * @param tablename
     * @param rowkey
     * @throws IOException
     */
    public static void deleteRow(String tablename, String rowkey) throws IOException {
        HTable table = new HTable(configuration, tablename);
        List list = new ArrayList();
        Delete d1 = new Delete(rowkey.getBytes());
        list.add(d1);
        table.delete(list);
        System.out.println("删除行成功！");
    }


    /**
     * 查找一行记录
     * @param tablename
     * @param rowKey
     */
    public static void selectRow(String tablename, String rowKey){
        try{
            HTable table = new HTable(configuration, tablename);
            Get g = new Get(rowKey.getBytes());
            Result rs = table.get(g);
            for (KeyValue kv : rs.raw()) {
                System.out.print(new String(kv.getRow()) + "  ");
                System.out.print(new String(kv.getFamily()) + ":");
                System.out.print(new String(kv.getQualifier()) + "  ");
                System.out.print(kv.getTimestamp() + "  ");
                System.out.println(new String(kv.getValue()));
            }
        }catch(Exception e){
            e.getMessage();
        }

    }
}
