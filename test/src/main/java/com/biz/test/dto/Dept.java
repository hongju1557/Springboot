
package com.biz.test.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Dept {

    private String deptId;
    private String deptTitle;
    private String locationId;

    private String nationalName;
    private int cnt;
    private BigDecimal avg;
}