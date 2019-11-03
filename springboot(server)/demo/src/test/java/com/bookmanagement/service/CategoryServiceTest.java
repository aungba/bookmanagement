package com.bookmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bookmanagement.dao.CategoryDao;
import com.bookmanagement.entity.Category;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	@Mock
	private CategoryDao categoryDao;

	@InjectMocks
	private CategoryService categoryService;

	@Test
	public void TestGetCategoryList_normal() {
		List<Category> expected_result = new ArrayList<Category>();
		expected_result.add(new Category(1, "develop"));

		doReturn(expected_result).when(categoryDao).getCategoryList();

		List<Category> actual_result = categoryService.getCategoryList();
		assertThat(actual_result).isEqualTo(expected_result);
	}

	@Test
	public void TestGetCategory_normal() {
		Category expected_result = new Category(1, "develop");
		int category_id = 1;

		doReturn(expected_result).when(categoryDao).getCategory(category_id);

		Category actual_result = categoryService.getCategory(category_id);
		assertThat(actual_result).isEqualTo(expected_result);

	}

	@Test
	public void TestUpdateCategory_normal() {
		Category category = new Category(1, "develop");
		int category_id = 1;
		int expected_result = 1;

		doReturn(expected_result).when(categoryDao).updateCategory(category, category_id);

		int actual_result = categoryService.updateCategory(category, category_id);
		assertThat(actual_result).isEqualTo(expected_result);
	}

	@Test
	public void TestAddCategory_normal() {
		Category category = new Category(1, "develop");
		int expected_result = 1;

		doReturn(expected_result).when(categoryDao).addCategory(category);

		int actual_result = categoryService.addCategory(category);
		assertThat(actual_result).isEqualTo(expected_result);
	}

	@Test
	public void TestDeleteCategory_normal() {
		int category_id = 1;
		int expected_result = 1;

		doReturn(expected_result).when(categoryDao).deleteCategory(category_id);

		int actual_result = categoryService.deleteCategory(category_id);
		assertThat(actual_result).isEqualTo(expected_result);
	}

}
