package cn.tedu.straw.redis.tag.service;


import cn.tedu.straw.commons.vo.TagVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TagServiceTests {
    @Autowired
    ITagService service;
    @Test
    void getTagList(){
        List<TagVO> tags = service.getTagList();
        System.out.println("Tag Count ="+tags.size());
        System.out.println(tags);
        for (TagVO tag:tags) {
            System.out.println("Tag >>>"+tag);
        }
    }
}
