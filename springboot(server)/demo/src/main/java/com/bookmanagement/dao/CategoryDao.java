package com.bookmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bookmanagement.entity.Category;

/**
 * This is CategoryDao interface
 * 
 * @author Aung Ba
 *
 */
@Mapper
public interface CategoryDao {

	/**
	 * This method is used to get all categories from db
	 * 
	 * @return categoryList
	 */
	List<Category> getCategoryList();

	/**
	 * This method is used to add category to db
	 * 
	 * @param category category
	 * @return 0 or 1
	 */
	int addCategory(Category category);

	/**
	 * This method is used to get category from db by category_id
	 * 
	 * @param category_id category id
	 * @return category
	 */
	Category getCategory(int category_id);

	/**
	 * This method is used to update category
	 * 
	 * @param category
	 * @param category_id
	 * @return 0 or 1
	 */
	int updateCategory(Category category, int category_id);

	/**
	 * This method is used to delete category by category_id
	 * 
	 * @param category_id category id
	 * @return 0 or 1
	 */
	int deleteCategory(int category_id);

}
