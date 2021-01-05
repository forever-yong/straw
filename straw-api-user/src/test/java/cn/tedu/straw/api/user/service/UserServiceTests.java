package cn.tedu.straw.api.user.service;


import cn.tedu.straw.api.user.dto.StudentRegisterDTO;
import cn.tedu.straw.commons.ex.ClassDisabledException;
import cn.tedu.straw.commons.ex.InsertException;
import cn.tedu.straw.commons.ex.InviteCodeException;
import cn.tedu.straw.commons.ex.PhoneDuplicateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    IUserService service;


    @Test
    void studentRegister(){
        try{
            StudentRegisterDTO student = new StudentRegisterDTO();
            student.setInviteCode("JSD2006-666666");
            student.setPhone("11001001231");
            student.setNickName("Liam");
            student.setPassword("098765");
            service.studentRegister(student);
            System.out.println("注册成功！");
        }catch (InviteCodeException e) {
            System.out.println("注册失败！邀请码错误！");
        } catch (ClassDisabledException e) {
            System.out.println("注册失败！班级已被禁用！");
        } catch (PhoneDuplicateException e) {
            System.out.println("注册失败！手机号码已经被注册！");
        } catch (InsertException e) {
            System.out.println("注册失败！服务器忙，请稍后再次尝试！");
        } catch (Throwable e) {
            System.out.println("注册失败！请通过以下日志分析问题：");
            e.printStackTrace();
        }
    }


}
