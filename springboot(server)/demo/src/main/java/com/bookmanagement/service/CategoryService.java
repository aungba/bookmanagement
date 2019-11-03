package com.bookmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanagement.dao.CategoryDao;
import com.bookmanagement.entity.Category;

/**
 * This is CategoryService class
 * 
 * @author Aung Ba
 *
 */
@Service
public class CategoryService {

	/**
	 * declare categoryDao
	 */
	@Autowired
	private CategoryDao categoryDao;

	/**
	 * This method is used to retrieve categoryList from db
	 * 
	 * @return categoryList
	 */
	public List<Category> getCategoryList() {
		return this.categoryDao.getCategoryList();
	}

	/**
	 * This method is used to add category into db
	 * 
	 * @param category category
	 * @return 0 or 1
	 */
	public int addCategory(Category category) {
		return this.categoryDao.addCategory(category);
	}

	/**
	 * This method is used to retrieve category by id from db
	 * 
	 * @param id category id
	 * @return category
	 */
	public Category getCategory(int id) {
		return this.categoryDao.getCategory(id);
	}

	/**
	 * This method is used to update category by id into db
	 * 
	 * @param category category
	 * @param id       category id
	 * @return 0 or 1
	 */
	public int updateCategory(Category category, int id) {
		return this.categoryDao.updateCategory(category, id);
	}

	/**
	 * This method is used to delete category by id from db
	 * 
	 * @param id category id
	 * @return 0 or 1
	 */
	public int deleteCategory(int id) {
		return this.categoryDao.deleteCategory(id);
	}

}
