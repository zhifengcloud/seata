package io.seata.order.service;


import io.seata.order.client.ProductClient;
import io.seata.order.entity.Order;
import io.seata.order.mapper.OrderMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderMapper orderMapper;

    //这里切记不要加@GlobalTransactional
    @Transactional
    @ShardingTransactionType(TransactionType.BASE)
    public boolean seataDemo(Long productId, int stockNum, Boolean hasError) {
        //下单操作
        Order order = new Order();
        order.setOrderName("测试订单:" + productId);
        order.setBuyNum(stockNum);
        order.setProductId(productId);
        int result = orderMapper.insert(order);
        System.out.println("插入订单结果：" + result);

        //减库存
        productClient.minusStock(productId.intValue(), stockNum);

        //异常模拟
        if (hasError) {
            int i = 1 / 0;
        }
        return true;
    }


}
