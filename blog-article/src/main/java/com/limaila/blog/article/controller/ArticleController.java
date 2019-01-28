package com.limaila.blog.article.controller;

import com.limaila.blog.article.entity.Article;
import com.limaila.blog.article.service.ArticleService;
import com.limaila.blog.user.entity.User;
import com.limaila.blog.user.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/get/{id}")
    public Article get(@PathVariable Integer id) {
        return articleService.getOneArticle(id);
    }

    @PostMapping("/add")
    public Integer add(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PutMapping("/update")
    public Integer update(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id) {
        return articleService.deleteArticle(id);
    }

    @GetMapping("/get/user/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userFeign.get(id);
        System.out.println(user);
        return user;
    }

}
