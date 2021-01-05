package cn.tedu.straw.gateway.mapper;

import cn.tedu.straw.commons.model.User;
import cn.tedu.straw.gateway.vo.UserLoginVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    UserLoginVO findLoginVOByPhone(String phone);

}
