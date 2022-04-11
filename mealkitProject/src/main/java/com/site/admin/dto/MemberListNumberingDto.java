package com.site.admin.dto;

import com.site.admin.AdminMapper;
import com.site.admin.vo.AdminMemberPageVo;
import com.site.vo.MemberVo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Getter
public class MemberListNumberingDto {
    @Autowired
    private AdminMapper adminMapper;

    private AdminMemberPageVo memberListVo;
    private ArrayList<MemberVo> memberList;
    private int listCount;
    private int limit;
    private int numLimit;
    private int maxPage;
    private int startPage;
    private int endPage;
    private int startRow;
    private int endRow;
    private int flag;

    public MemberListNumberingDto(AdminMemberPageVo memberListVo, int limit, int numLimit) {
        this.memberListVo = memberListVo;
        this.listCount = adminMapper.countMemberList(
                memberListVo.getCategory()
                ,memberListVo.getOrder()
                ,memberListVo.getSearchWord());

        this.limit = limit;
        this.numLimit = numLimit;

        this.maxPage = (int)((double)listCount/limit + 0.9);
        this.startPage = (((int)((double)memberListVo.getPage() / numLimit+0.9)) - 1) * numLimit + 1;
        this.endPage = this.startPage+numLimit-1;

        if (this.endPage > this.maxPage){
            this.endPage = this.maxPage;
        }

        this.startRow = (memberListVo.getPage()-1) * limit + 1;
        this.endRow = this.startRow + limit - 1;

        this.memberList = adminMapper.memberList(
                this.startRow
                ,this.endRow
                ,memberListVo.getCategory()
                ,memberListVo.getOrder()
                ,memberListVo.getSearchWord());

        if(this.memberList.isEmpty()) {
            this.flag = 1;
        }
    }
}
