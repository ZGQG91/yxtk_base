package com.yidu.compone.baseau;

import com.yidu.compone.centor.HadoopConnection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/1/3.
 */
public class AbstactConnect {
    @Autowired
    HadoopConnection connection;
}
