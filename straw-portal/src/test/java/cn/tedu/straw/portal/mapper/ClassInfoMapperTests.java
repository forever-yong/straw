package cn.tedu.straw.portal.mapper;

import cn.tedu.straw.portal.model.ClassInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassInfoMapperTests {

    @Autowired
    ClassInfoMapper mapper;
    @Test
    void findByInviteCode(){
        String inviteCode="JSD2006-666666";
        ClassInfo classInfo = mapper.findByInviteCode(inviteCode);
        System.out.println("ClassInfo >>>"+classInfo);
    }

}
