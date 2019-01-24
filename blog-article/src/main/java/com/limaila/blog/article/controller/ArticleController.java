package com.limaila.blog.article.controller;

import com.limaila.blog.article.entity.Article;
import com.limaila.blog.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/get/{id}")
    public Article get(@PathVariable Integer id) {
        return articleService.getOneArticle(id);
    }

    @PostMapping("/add")
    public int add(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @PutMapping("/update")
    public int update(@RequestBody Article article) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable Integer id) {
        return articleService.deleteArticle(id);
    }

}
