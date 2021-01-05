package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.Tag;
import cn.tedu.straw.portal.vo.TagVO;
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
public interface TagMapper extends BaseMapper<Tag> {
    List<TagVO> findAll();

}
