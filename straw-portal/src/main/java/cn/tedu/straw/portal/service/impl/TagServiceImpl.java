package cn.tedu.straw.portal.service.impl;

import cn.tedu.straw.portal.mapper.TagMapper;
import cn.tedu.straw.portal.model.Tag;
import cn.tedu.straw.portal.service.ITagService;
import cn.tedu.straw.portal.vo.TagVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    TagMapper tagMapper;
    @Override
    public List<TagVO> getTagList() {
        return tagMapper.findAll();
    }


}
