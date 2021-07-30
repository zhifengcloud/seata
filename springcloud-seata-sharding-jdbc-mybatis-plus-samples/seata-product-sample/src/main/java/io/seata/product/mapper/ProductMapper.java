package io.seata.product.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import io.seata.product.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    @Update("update product_info set stock = stock-#{num} where id=#{productId}")
    void minusStock(@Param("productId") int productId, @Param("num") int num);

    @Select("SELECT * FROM product_info where id = #{productId}")
    Product getByProductId(@Param("productId") int productId);

    /**
     *
     * 如果自定义的方法还希望能够使用MP提供的Wrapper条件构造器，则需要如下写法
     * 原文链接：https://blog.csdn.net/weixin_38111957/article/details/91539019
     * @param userWrapper
     * @return
     */
    @Select("SELECT * FROM product_info ${ew.customSqlSegment}")
    List<Product> selectByMyWrapper(@Param(Constants.WRAPPER) Wrapper<Product> userWrapper);


}
