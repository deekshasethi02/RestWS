package com.yash.blog_v3.dao;

import java.util.List;

import com.yash.blog_v3.domain.Category;


/**
 * This is used to perform all CRUD operations related to Category.
 * @author arju.yadav
 *
 */
public interface CategoryDAO {
	
	/**
	 * This method will insert the Category in database.
	 * @param blog
	 */
	Category insert(Category category);
	
	/**
	 * It will return the found category of the given id.
	 * @param id of category.
	 * @return Category if found otherwise null.
	 */
	Category show(int id);

	/**
	 * It will update the Category detail.
	 * @param blog
	 */
	Category update(Category category);
	
	/**
	 * This method will delete the Category of specified id.
	 * @param id
	 */
	Category delete(int id);
	
	/**
	 * This method will return the list of Category if found.
	 * @return 
	 * @return list of Category if found else null.
	 */
	List<Category> list();
}