package com.gjyl.appserver.pojo;

import java.util.List;

/**
 * Created by LiD on 2017/4/21.
 */
public class EssayResult {

    private List<Essay> list;

    private Integer maxPage;

    public List<Essay> getList() {
        return list;
    }

    public void setList(List<Essay> list) {
        this.list = list;
    }

    public Integer getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }
}
