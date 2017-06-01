package com.yidu;

import com.hankcs.hanlp.seg.common.Term;
import com.yidu.utils.PageDataInter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface HanlpBaseServiceInter {
    boolean insertHanlp(Map<String,Object> map);
    List<Term> splitWords(Map<String,Object> map);
}
