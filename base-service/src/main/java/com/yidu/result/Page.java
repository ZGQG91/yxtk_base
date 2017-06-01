package com.yidu.result;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */
public class Page{
    private int pageNo=1;
    private int pageSize=20;

    private int pageIndex;

    public Page(Map<String,Object> map) {
        String pageNoStr= (String) map.get("pageNo");
        String pageSizeStr= (String) map.get("pageSize");
        if(!StringUtils.isEmpty(pageNoStr)){
            this.pageSize=Integer.parseInt(pageSizeStr);
            this.setPageNo(Integer.parseInt(pageNoStr));
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        this.pageIndex=(this.pageNo-1)*this.pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
