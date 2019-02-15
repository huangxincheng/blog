package com.limaila.blog.user.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable,Cloneable {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Date createTime;

    private Date activeTime;

    private Boolean isCanUse;


    public static void main(String[] args) throws CloneNotSupportedException {
        User u1 = new User();
        u1.setId(1).setUsername("111").setPassword("111");
        User u2 = u1;
        User u3 = (User)u1.clone();
        System.out.println(u1.hashCode());
        System.out.println(u2.hashCode());
        System.out.println(u3.hashCode());
        User u4 = new User();
        BeanUtils.copyProperties(u1, u4);
        System.out.println(u4.hashCode());
    }
}