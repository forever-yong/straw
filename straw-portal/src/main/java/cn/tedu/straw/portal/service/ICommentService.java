package cn.tedu.straw.portal.service;

import cn.tedu.straw.portal.dto.PostCommentDTO;
import cn.tedu.straw.portal.model.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2020-10-26
 */
public interface ICommentService extends IService<Comment> {
    void post(PostCommentDTO postCommentDTO,Integer userId,String userNickName);

}
