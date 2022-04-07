package com.example.demo.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName("`order`")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long merId;
    private Long goodsId;
    private Long groupId;
    private String name;
    private Double unitPrice;
    private Integer num;
    private String consigneeName;
    private String consigneeAddress;
    private String consigneePhone;
    private String state;
    private Date createDate;
    private String evaluate;
    private String picture;
    private Double dealPrice;
    private Integer score;
    private Integer deleteState;

    public Order() {

    }

    public Order(String state) {
        this.setState(state);
    }
}

