package com.yf.mydemo.infrastructure.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class PagedResult<T> {
    private List<T> datas;
    private long totals;
    private long currentPageIndex;
    private long pageSize;
}
