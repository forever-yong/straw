package cn.tedu.straw.redis.tag.service;



import cn.tedu.straw.commons.model.Tag;
import cn.tedu.straw.commons.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
public interface ITagService extends IService<Tag> {
    List<TagVO> getTagList();
}
