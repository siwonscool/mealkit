package com.site.admin.vo;

import com.site.vo.ChartVo;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public class ChartListVo {
    private ArrayList<ChartVo> list ;
    private ArrayList<ChartVo> tableList;
    private ArrayList<ChartVo> tableSummary;
}
