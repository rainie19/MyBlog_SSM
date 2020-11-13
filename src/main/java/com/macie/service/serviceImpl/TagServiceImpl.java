package com.macie.service.serviceImpl;


import com.macie.dao.TagDao;
import com.macie.entity.Article;
import com.macie.entity.Tag;
import com.macie.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2020/10/29 -17:59
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    public TagDao tagDao;

    @Override
    public TreeMap<Integer, ArrayList<Tag>> getArticleTagMap(ArrayList<Article> Articles) {
        TreeMap<Integer, ArrayList<Tag>>  tMap = new TreeMap<>();
        for(Article Article : Articles) {
            Integer article_id = Article.getArticleId();
            ArrayList<Tag> TagArrayList = tagDao.getTagsByArticleId(article_id);
            tMap.put(article_id, TagArrayList);
        }
        return tMap;
    }

    @Override
    public ArrayList<Tag> retrieveAllTags() {
        return tagDao.listAllTags();
    }

    @Override
    public Long countAllTags() {
        return tagDao.countAllTags();
    }

    @Override
    public ArrayList<Tag> retrieveTagsByArticleId(Integer articleId) {
        return tagDao.getTagsByArticleId(articleId);
    }

    @Override
    public int deleteTagsByArticleId(Integer articleId) {
        return tagDao.deleteTagsByArticleId(articleId);
    }

    @Override
    public int insertTagByArticleId(Integer articleId, String tagName) {
        return tagDao.insertTagByArticleId(articleId, tagName);
    }

    @Override
    public int insertNewTag(String tagName) {
        return tagDao.insertNewTag(tagName);
    }

    @Override
    public int deleteTag(String tagName) {
        return tagDao.deleteTag(tagName);
    }
}
