package cn.tedu.straw.api.user.mapper;




import cn.tedu.straw.commons.model.ClassInfo;
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
