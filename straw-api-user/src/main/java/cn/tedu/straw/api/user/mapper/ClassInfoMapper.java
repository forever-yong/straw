package cn.tedu.straw.api.user.mapper;


import cn.tedu.straw.commons.model.ClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Repository
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    ClassInfo findByInviteCode(String inviteCode);


}
