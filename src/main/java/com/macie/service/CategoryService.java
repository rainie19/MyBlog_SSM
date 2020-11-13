package com.macie.service;

import com.macie.dao.ArticleDao;
import com.macie.dao.CategoryDao;
import com.macie.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2020/9/29 -16:37
 */
@Service
public class CategoryService implements CategoryDao {
    @Autowired
    public CategoryDao categoryDao;

    @Autowired
    public ArticleDao articleDao;

    @Override
    public ArrayList<Category> listAllCategories() {
        return categoryDao.listAllCategories();
    }

    @Override
    public Long countAllCategories() {
        return categoryDao.countAllCategories();
    }

    @Override
    public int updateCategory2Default(Integer categoryId) {
        return categoryDao.updateCategory2Default(categoryId);
    }

    @Override
    public int deleteCategoryById(Integer categoryId) {
        return categoryDao.deleteCategoryById(categoryId);
    }

    @Override
    public int insertNewCategory(Category Category) {
        return categoryDao.insertNewCategory(Category);
    }

    @Override
    public Boolean isCategoryExists(String categoryName) {
        return categoryDao.isCategoryExists(categoryName);
    }

    public TreeMap<String, Long> countArticlesEachCategory(ArrayList<Category> Categorys) {
        TreeMap<String, Long> countMap = new TreeMap<>();
        if(Categorys != null) {
            for (Category category : Categorys) {
                String categoryName = category.getCategoryName();
                Long count = articleDao.countAllArticles("category", categoryName);
                countMap.put(categoryName, count);
            }
        }
            return countMap;
    }

}
