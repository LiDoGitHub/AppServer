package com.gjyl.appserver.pojo;

import java.util.List;

/**
 * Created by LiD on 2017/4/21.
 */
public class ECDetailResult {

    private Essay essay;

    private List<EssayAgree> essayAgreeList;

    private List<EssayComment> essayCommentList;

    public Essay getEssay() {
        return essay;
    }

    public void setEssay(Essay essay) {
        this.essay = essay;
    }

    public List<EssayAgree> getEssayAgreeList() {
        return essayAgreeList;
    }

    public void setEssayAgreeList(List<EssayAgree> essayAgreeList) {
        this.essayAgreeList = essayAgreeList;
    }

    public List<EssayComment> getEssayCommentList() {
        return essayCommentList;
    }

    public void setEssayCommentList(List<EssayComment> essayCommentList) {
        this.essayCommentList = essayCommentList;
    }
}
