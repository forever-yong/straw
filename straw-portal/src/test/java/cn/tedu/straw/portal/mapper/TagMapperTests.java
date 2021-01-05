package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.vo.TagVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TagMapperTests {
    @Autowired
    TagMapper mapper;
    @Test
    void findAll(){
        List<TagVO> tags = mapper.findAll();
        System.out.println("Tag Count ="+tags.size());
        System.out.println(tags);
        for (TagVO tag:tags) {
            System.out.println("Tag >>>"+tag);
        }
    }
}
