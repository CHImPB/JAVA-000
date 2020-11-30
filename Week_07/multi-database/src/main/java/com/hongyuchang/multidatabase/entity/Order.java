package com.hongyuchang.multidatabase.entity;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class Order implements Serializable {
    private Long id;
    private String orderCode;
    private Date orderTime;
    private String address;
    private BigDecimal totalAmount;
    private Long userId;
    private Date createDate;
    private Date updateDate;
}
