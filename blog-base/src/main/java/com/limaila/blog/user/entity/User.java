package com.limaila.blog.user.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

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
//        User u1 = new User();
//        u1.setId(1).setUsername("111").setPassword("111");
//        User u2 = u1;
//        User u3 = (User)u1.clone();
//        System.out.println(u1.hashCode());
//        System.out.println(u2.hashCode());
//        System.out.println(u3.hashCode());
//        User u4 = new User();
//        BeanUtils.copyProperties(u1, u4);
//        System.out.println(u4.hashCode());


        User u1 = new User().setUsername("111").setId(111);
        User u2 = new User().setUsername("222").setId(222);
        User u3 = new User().setUsername("333").setId(333);

        HashMap<Integer, User> map = new HashMap<>();
        map.put(1, u2);
        map.put(2, u1);
        map.put(3, u3);
        Map<Integer, User> integerUserMap = sortMap(map);
        for (Map.Entry<Integer, User> integerUserEntry : integerUserMap.entrySet()) {
            System.out.println(integerUserEntry);
        }
    }

    public static Map<Integer, User> sortMap(HashMap<Integer, User> map) {
        List<Map.Entry<Integer, User>> list = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : map.entrySet()) {
            list.add(entry);
        }
        List<Map.Entry<Integer, User>> collect = list.stream().sorted((o1, o2) -> -o1.getValue().getId().compareTo(o2.getValue().getId())).collect(Collectors.toList());
        Map<Integer, User> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> integerUserEntry : collect) {
            result.put(integerUserEntry.getKey(), integerUserEntry.getValue());
        }
        return result;
    }
}