package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.model.Tag;
import cn.tedu.straw.portal.vo.TagVO;
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
