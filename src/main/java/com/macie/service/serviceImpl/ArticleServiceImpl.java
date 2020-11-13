package com.macie.service.serviceImpl;

import com.macie.dao.ArticleDao;
import com.macie.dao.CategoryDao;
import com.macie.dao.TagDao;
import com.macie.entity.Article;
import com.macie.entity.Category;
import com.macie.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/10/29 -16:57
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Autowired
    public TagDao tagDao;

    @Autowired
    public CategoryDao categoryDao;

    @Override
    public ArrayList<Article> listArticles(String queryType, Integer queryPage, Integer pageSize, String queryName) {
        ArrayList<Article> Articles = null;

        // 检索所有文章或某个tag,category下的所有文章
        if("all".equals(queryPage)) {
            if("category".equals(queryType) && queryName != null) {
                Articles = articleDao.listArticlesByCategoryName(queryName);
            }
            else if("tag".equals(queryType) && queryName != null) {
                Articles = articleDao.listArticlesByTagName(queryName);
            }
            else{
                Articles = articleDao.listAllArticles();
            }
        }
        // 检索某一页的文章或某个tag,category下的某一页的文章
        else if(queryPage != null && queryPage != null && pageSize != null) {
            Integer limit = pageSize;
            Integer offset = pageSize * (queryPage - 1);
            if("category".equals(queryType) && queryName != null){
                Articles = articleDao.listArticlesByCategoryNamePerPage(queryName, limit, offset);
            }
            else if("tag".equals(queryType) && queryName != null){
                Articles = articleDao.listArticlesByTagNamePerPage(queryName, limit, offset);
            }
            else if("article".equals(queryType)){
                Articles = articleDao.listArticlesPerPage(limit, offset);
            }
        }
        return Articles;
    }

    @Override
    public Article getArticle(Integer articleId) {
        if(articleId == null) {
            return null;
        }
        Article Article = articleDao.getArticleById(articleId);
        return Article;
    }

    @Override
    public Article getPreviousArticle(Integer articleId) {
        if(articleId == null) {
            return null;
        }
        Article prevArticle = articleDao.getPreviousArticle(articleId);
        return prevArticle;
    }

    @Override
    public Article getNextArticle(Integer articleId) {
        if(articleId == null) {
            return null;
        }
        Article nextArticle = articleDao.getNextArticle(articleId);
        return nextArticle;
    }

    @Override
    public Long countArticles(String queryType, String queryName) {
        Long totalCount = articleDao.countAllArticles();
        if("category".equals(queryType) && queryName != null){

            totalCount = articleDao.countAllArticles("category", queryName);
        }
        else if("tag".equals(queryType) && queryName != null){
            totalCount = articleDao.countAllArticles("tag", queryName);
        }
        return totalCount;
    }

    @Override
    public int updateArticleViewCount(Integer articleId) {
        return articleDao.updateArticleViewCount(articleId);
    }

    @Override
    public int deleteArticle(Integer articleId) {
        int ret = -1;
        if(articleId == null) {
            return ret;
        }
        ret = articleDao.deleteArticle(articleId);
        // 删除文章后需要把文章对应的标签也删掉
        tagDao.deleteTagsByArticleId(articleId);
        return ret;
    }

    @Override
    public Article publishArticle(Article Article, ArrayList<String> tagList, String publishType) {
        if(Article == null || publishType == null) {
            return null;
        }
        Integer articleId = null;
        // 若是创造了新的分类，先将此分类信息插入数据库中
        String categoryName = Article.getCategoryName();
        if(!categoryDao.isCategoryExists(categoryName)) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            categoryDao.insertNewCategory(category);
        }

        if("create".equals(publishType)) {
            articleId = articleDao.insertNewArticle(Article);
        }
        else if("modify".equals(publishType)) {
            articleDao.updateArticle(Article);
            articleId = Article.getArticleId();
        }
        // 插入文章的tags
        if(articleId != null) {
            tagDao.deleteTagsByArticleId(articleId);
            for (String tag : tagList) {
                tagDao.insertTagByArticleId(articleId, tag);
            }
        }
        Article reponseArticle = new Article();
        reponseArticle.setArticleSlug(Article.getArticleSlug());
        reponseArticle.setArticleId(articleId);
        return reponseArticle;
    }
}
