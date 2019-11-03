package com.bookmanagement.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.Category;
import com.bookmanagement.service.CategoryService;

/**
 * This is CategoryController class
 * 
 * @author Aung Ba
 *
 */
@RestController
@RequestMapping(path = "/categories")
public class CategoryController {

	/**
	 * declare categoryService
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * This method is used to retrieve all category list from db
	 * 
	 * @return categoryList
	 */
	@GetMapping("/")
	public List<Category> getCategoryList() {
		return this.categoryService.getCategoryList();
	}

	/**
	 * This method is used to retrieve category by id from db
	 * 
	 * @param id category id
	 * @return category
	 */
	@GetMapping("/{id}")
	public Category getCategory(@PathVariable int id) {
		return this.categoryService.getCategory(id);
	}

	/**
	 * This method is used to update category by id into db
	 * 
	 * @param id       category id
	 * @param category category
	 * @return 0 or 1
	 */
	@PutMapping("/{id}")
	public int updateCategory(@PathVariable int id, @Valid @RequestBody Category category) {
		return this.categoryService.updateCategory(category, id);
	}

	/**
	 * This method is used to add category into db
	 * 
	 * @param category category
	 * @return 0 or 1
	 */
	@PostMapping("/add")
	public int addCategory(@Valid @RequestBody Category category) {
		return this.categoryService.addCategory(category);
	}

	/**
	 * This method is used to delete category by id from db
	 * 
	 * @param id category id
	 * @return 0 or 1
	 */
	@DeleteMapping("/{id}")
	public int deleteCategory(@PathVariable int id) {
		return this.categoryService.deleteCategory(id);
	}

}
