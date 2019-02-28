package com.yash.blog_v3.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This blog is a model that is used to travel data from one layer to other layer.
 */
@XmlRootElement
public class Blog {

	/**
	 * Id of blog.
	 * This will be used as primary key.
	 */
	private int id;
	
	/**
	 * Title of blog.
	 */
	private String title;
	
	/**
	 * Body of blog that is used to hold blog content.
	 */
	private String body;
	
	/**
	 * Created date of blog.
	 */
	private String created_at;
	
	/**
	 * Updated date of blog.
	 */
	private String updated_at;

	/**
	 * Category of the blog
	 */
	private List<Integer> category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public List<Integer> getCategory() {
		return category;
	}

	public void setCategory(List<Integer> category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", body=" + body + ", created_at=" + created_at + ", updated_at="
				+ updated_at + ", category=" + category + "]";
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	
}
