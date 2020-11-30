package com.hongyuchang.generatemillionrecord.hikari;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author hongyuchang
 */
@Data
public class OrderDTO {
    private Long id;
    private String order_code;
    private Date order_time;
    private String address;
    private BigDecimal total_amount;
    private Long user_id;
    private Date create_date;
    private Date update_date;
}
