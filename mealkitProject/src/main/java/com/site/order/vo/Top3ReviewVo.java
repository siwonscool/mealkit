package com.site.order.vo;

import com.site.vo.ReviewVo;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Top3ReviewVo {
    private ArrayList<ReviewVo> top3ReviewList;
    private List<String> tep3ReviewPictureList;
}
