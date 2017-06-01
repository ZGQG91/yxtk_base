package com.yidu.dao;

import com.yidu.MybatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/18.
 */
@MybatisDao
public interface RedBagActiveMapper {
    List<Map<String,Object>> getRedBagActiveList(Map<String,Object> map);
    int getRedBagActiveCount(Map<String,Object> map);
    Map<String,Object> getRedBagActiveByActiveId(Map<String,Object> map);
    List<Map<String,Object>> getReadyRedBagActiveList(Map<String,Object> map);
    List<Map<String,Object>> getReadyRedBagActiveListByFlag(@Param("activeFlag") int activeFlag);
    void updateActive(Map<String,Object> map);
}
