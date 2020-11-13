package com.macie.service;

import com.macie.entity.Article;
import com.macie.entity.Tag;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2020/10/29 -17:58
 */
public interface TagService {

    /**
     * 将文章下的标签与文章id对应起来
     *
     * @param Articles
     * @return
     */
    TreeMap<Integer, ArrayList<Tag>> getArticleTagMap(ArrayList<Article> Articles);

    /**
     *
     * @return
     */
    public ArrayList<Tag> retrieveAllTags();

    /**
     *
     * @param articleId
     * @return
     */
    public ArrayList<Tag> retrieveTagsByArticleId(Integer articleId);

    /**
     *根据文章标题更新文章的tags
     * @param articleTitle
     * @param tagName
     * @return
     */
    //public int updateTagsByArticleTitle(String articleTitle, String tagName);

    /**
     * 获取标签总数
     * @return
     */
    public Long countAllTags();
    /**
     * 删除文章下所有tag
     * @param articleId
     * @return
     */
    public int deleteTagsByArticleId(Integer articleId);

    /**
     *插入具体文章tag
     * @param articleId
     * @param tagName
     * @return
     */
    public int insertTagByArticleId(Integer articleId, String tagName);
    /**
     * 往Database中添加新标签
     * @param tagName
     * @return
     */
    public int insertNewTag(String tagName);
    /**
     *在所有地方删除此tag
     * @param tagName
     * @return
     */
    public int deleteTag(String tagName);

}
