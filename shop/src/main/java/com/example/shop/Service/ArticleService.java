package com.example.shop.Service;
import com.example.shop.entity.Articles;
import com.example.shop.repository.ArticlesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticleService {
    @Autowired
    private ArticlesRepository articlesRepository;

    public Articles getArticleById(int id) {
        return articlesRepository.findByArticleId(id);
    }

    public boolean createArticle(Articles article) {
        try {
            articlesRepository.saveAndFlush(article);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteArticle(long id){
        try {
            articlesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
