package cn.tedu.straw.portal.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
@Data
public class StudentRegisterDTO implements Serializable {
    private String username;
    @NotNull(message = "注册失败!必须提交昵称才可以注册!")
    @Size(min = 2,max = 10,message = "注册失败!昵称必须由2-10个字符组成")
    private String nickName;
    @NotNull(message = "注册失败!必须提交密码才可以注册!")
    @Size(min = 4,max = 20,message = "注册失败!密码必须由4-20个字符组成")
    private String password;
    private Integer gender;
    private LocalDate dayOfBirth;
    @NotNull(message = "注册失败,必须提交手机号码才可以注册!")
    private String phone;
    @NotNull(message = "注册失败!必须提交邀请码才可以注册!")
    @Size(min = 8,max = 20,message = "注册失败!邀请码必须由8-20个字符组成")
    private String inviteCode;
}
