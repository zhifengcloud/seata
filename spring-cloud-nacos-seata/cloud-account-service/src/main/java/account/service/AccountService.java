package account.service;


import account.entity.Account;
import account.repository.AccountDAO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Program Name: springcloud-nacos-seata
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/8/28 4:05 PM
 */
@Service
public class AccountService {

    private static final String ERROR_USER_ID = "1002";

    @Resource
    private AccountDAO accountDAO;

    /**
     * 减库存
     * @param userId
     * @param num
     */
    @Transactional(rollbackFor = Exception.class)
    public void debit(String userId, BigDecimal num) {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Account().setUserId(userId));
        Account account = accountDAO.selectOne(wrapper);
        account.setMoney(account.getMoney().subtract(num));
        accountDAO.updateById(account);
        if (ERROR_USER_ID.equals(userId)) {
            throw new RuntimeException("account branch exception");
        }
    }
}
