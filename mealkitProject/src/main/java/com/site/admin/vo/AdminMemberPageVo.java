package com.site.admin.vo;

import lombok.Getter;

@Getter
public class AdminMemberPageVo {
    private int page;
    private String category;
    private String order;
    private String searchWord;
}
