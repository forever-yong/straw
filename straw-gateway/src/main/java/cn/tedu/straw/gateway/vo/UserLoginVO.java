package cn.tedu.straw.gateway.vo;

import cn.tedu.straw.commons.model.Permission;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserLoginVO implements Serializable {

    private Integer id;
    private String username;
    private String nickName;
    private String password;
    private String phone;
    private Integer isEnabled;
    private Integer isLocked;
    private Integer accountType;
    private List<Permission> permissions;

}