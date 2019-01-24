package com.limaila.blog.article.service;

import com.limaila.blog.article.dao.ArticleMapper;
import com.limaila.blog.article.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 添加文章
     */
    public int addArticle(Article article) {
        return articleMapper.insertSelective(article);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    public int updateArticle(Article article) {
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    public int deleteArticle(Integer articleId) {
        return articleMapper.deleteByPrimaryKey(articleId);
    }

    /**
     * 获取单个文章
     * @param articleId
     * @return
     */
    public Article getOneArticle(Integer articleId) {
        return articleMapper.selectByPrimaryKey(articleId);
    }



}
