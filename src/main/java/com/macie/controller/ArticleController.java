package com.macie.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.macie.dto.JsonResponse;
import com.macie.entity.Article;
import com.macie.entity.Tag;
import com.macie.service.ArticleService;
import com.macie.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2021/10/9 -15:36
 */
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    /**
     * 获取某条件下的所有文章
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getArticles")
    public JsonResponse getArticles(@RequestParam Map<String, String> requestMap) {
        Integer queryPage = null, pageSize = null;
        String queryType = requestMap.get("queryType");
        String queryName = requestMap.get(queryType);
        if (requestMap.get("queryPage") != null) {
            queryPage = Integer.valueOf(requestMap.get("queryPage"));
        }
        if (requestMap.get("pageSize") != null) {
            pageSize = Integer.valueOf(requestMap.get("pageSize"));
        }
        Map<String, Object> map = new HashMap();
        Long totalCount = articleService.countArticles(queryType, queryName);
        ArrayList<Article> articleVos = articleService.listArticles(queryType, queryPage, pageSize, queryName);
        TreeMap<Integer, ArrayList<Tag>> articleTagMap = null;
        if (articleVos != null) {
            articleTagMap = tagService.getArticleTagMap(articleVos);
        }

        map.put("articles", articleVos);
        map.put("articleTotalCount", totalCount);
        map.put("articleIdTagsMap", articleTagMap);

        return JsonResponse.responseOK(map);
    }

    /**
     * 获取某篇文章的内容
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/getArticleDetail")
    public JsonResponse getArticleDetail(@RequestParam("articleId") Integer articleId) {

        Article article = articleService.getArticle(articleId);
        // 映射article_id和tags
        ArrayList<Tag> tagVoArrayList = tagService.retrieveTagsByArticleId(articleId);
        //返回上一篇文章和下一篇文章信息
        Article prevArticle = articleService.getPreviousArticle(articleId);
        Article nextArticle = articleService.getNextArticle(articleId);

        Map<String, Object> map = new HashMap();
        map.put("article", article);
        map.put("prevArticle", prevArticle);
        map.put("nextArticle", nextArticle);
        map.put("tags", tagVoArrayList);

        // response返回后增加文章阅读次数
        articleService.updateArticleViewCount(articleId);

        return JsonResponse.responseOK(map);
    }

    /**
     * 删除文章
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/deleteArticle")
    public JsonResponse deleteArticle(@RequestParam("articleId") Integer articleId) {
        articleService.deleteArticle(articleId);
        Map<String, Object> map = new HashMap();
        return JsonResponse.responseOK();
    }

    /**
     * 发布文章
     *
     * @param articleDetail
     * @param dynamicTags
     * @param publishType
     */
    @RequestMapping("/publishArticle")
    public JsonResponse publishArticle(@RequestParam("article") String articleDetail, @RequestParam("dynamicTags") String dynamicTags, @RequestParam("publishType") String publishType) throws JsonProcessingException {
        System.out.println("article:"+ articleDetail);
//        dynamicTags = StringUtils.strip(dynamicTags, "[]");
        ObjectMapper mapper = new ObjectMapper();
        Article article = mapper.readValue(articleDetail, Article.class);
        ArrayList<String> tags = mapper.readValue(dynamicTags, ArrayList.class);
//        ArrayList<String> tags = new ArrayList<>(Arrays.asList(dynamicTags.split(",")));
        Article responseArticle = articleService.publishArticle(article, tags, publishType);

        Map<String, Object> map = new HashMap();
        map.put("article", responseArticle);
        return JsonResponse.responseOK(map);
    }

}
