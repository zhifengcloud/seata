package account.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@TableName("account_tbl")
public class Account {
    private Integer id;

    private String userId;

    private BigDecimal money;

}