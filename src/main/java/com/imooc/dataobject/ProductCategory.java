package com.imooc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by msi on 2019/7/14.
 */
@Entity
@Data
@DynamicUpdate  //动态更新    在Hibernate中可以利用@DynamicInsert和@DynamicUpdate生成动态SQL语句，即在插入和修改数据的时候,语句中只包括要插入或者修改的字段。
public class ProductCategory {


    /** 类目id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.updateTime = new Date();
    }
    public  ProductCategory(){

    }
}
