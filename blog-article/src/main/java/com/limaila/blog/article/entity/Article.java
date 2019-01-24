package com.limaila.blog.article.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Article implements Serializable {
    private Integer id;

    private String title;

    private String memo;

    private Integer userId;

    private Date releaseTime;
}