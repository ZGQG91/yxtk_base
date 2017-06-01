package com.yidu.compone.centor;

import com.yidu.compone.IConnection;
import com.yidu.hadoop.HadoopOpera;

/**
 * Created by Administrator on 2017/1/3.
 */
public class HadoopConnection implements IConnection {
    HadoopOpera testHadoop;
    public void insert(String connMethod) {
        testHadoop.insertData();
    }

    public void update(String connMethod) {

    }
}
