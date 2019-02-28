package com.yash.blog_v3.daoimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yash.blog_v3.dao.BlogDAO;
import com.yash.blog_v3.domain.Blog;
import com.yash.blog_v3.domain.User;
import com.yash.blog_v3.rowmapper.AllUserRowMapper;
import com.yash.blog_v3.rowmapper.BlogRowMapper;
import com.yash.blog_v3.util.DateUtil;
import com.yash.blog_v3.util.JNDIConnectionPooling;
import com.yash.blog_v3.util.SimpleJDBCUtil;

public class BlogDAOImpl extends JNDIConnectionPooling implements BlogDAO {

	BlogCategoriesDAOImpl blogcat = new BlogCategoriesDAOImpl();

	@Override
	public Blog insert(Blog blog) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String created_at = new DateUtil().convertDateToString(formatter,new Date());
		String updated_at = new DateUtil().convertDateToString(formatter,new Date());
		
		String query = "insert into blogs(title,body,created_at,updated_at) values('" + blog.getTitle() + "','"
				+ blog.getBody() + "','" + created_at+ "','" + updated_at + "')";

		try {
			createPreparedStatement(query).execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blog;
	}

	@Override
	public Blog show(int id) {
		Blog blog = new Blog();
		try {

			PreparedStatement pstmt = createPreparedStatement("select * from blogs where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			BlogRowMapper blogRowMapper = new BlogRowMapper();
			blog = blogRowMapper.mapRow(rs, blog);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs2;
		try {
			rs2 = createPreparedStatement("select * from blogcategories where blog_id=" + blog.getId()).executeQuery();
			List<Integer> categories = new ArrayList<>();
			while (rs2.next()) {

				categories.add(rs2.getInt("category_id"));
			}
			blog.setCategory(categories);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return blog;
	}

	@Override
	public Blog update(Blog blog) {
		String query = "UPDATE blogs set title=?,body=?,updated_at=? where id=?";
        //String query = "UPDATE blogs set title=?,created_at=? where id=?";
        PreparedStatement pstmt = createPreparedStatement(query);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updated_at = new DateUtil().convertDateToString(formatter,new Date());
        try {
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getBody());
            pstmt.setString(3, updated_at);
            pstmt.setInt(4, blog.getId());
            pstmt.execute();
            //blogcat.update(blog.getId(), blog.getCategory());
        } catch (SQLException ex) {
            ex.printStackTrace();
            blog = null;
        }
        return blog;
        
//		// String query = "UPDATE blogs set title=?,body=?,updated_at=? where id=?";
//		String query = "UPDATE blogs set title=?,created_at=? where id=?";
//		PreparedStatement pstmt = createPreparedStatement(query);
//
//		try {
//			pstmt.setString(1, blog.getTitle());
//			pstmt.setString(2, blog.getCreated_at());
//			pstmt.setInt(3, blog.getId());
//
//			// pstmt.setString(3, blog.getUpdated_at());
//			// pstmt.setInt(4, blog.getId());
//			pstmt.execute();
//			blogcat.update(blog.getId(), blog.getCategory());
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//		return blog;
	}

	@Override
	public Blog delete(int id) {

		blogcat.delete(id);

		String query = "DELETE from blogs where id=" + id;
		Blog blog = show(id);
		try {

			createPreparedStatement(query).execute();
			System.out.println("blog query");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blog;
	}

	@Override
	public List<Blog> list() {

		List<Blog> blogs = new ArrayList<>();
		try {
			ResultSet rs = createPreparedStatement(
					"SELECT * FROM blogcategories AS c, blogs AS b WHERE b.`id` = c.`blog_id` GROUP BY c.blog_id")
							.executeQuery();

			while (rs.next()) {
				Blog blog = new Blog();
				List<Integer> categories = new ArrayList<>();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setBody(rs.getString("body"));

				String created = new DateUtil().convertDateStringToString(rs.getString("created_at"));

				blog.setCreated_at(created);
				blog.setUpdated_at(rs.getString("updated_at"));

				ResultSet rs2 = createPreparedStatement("SELECT c.category_id FROM blogcategories AS c, blogs AS b "
						+ "WHERE b.`id` = c.`blog_id` AND id=" + blog.getId()).executeQuery();
				while (rs2.next()) {

					categories.add(rs2.getInt("category_id"));
				}
				blog.setCategory(categories);

				blogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return blogs;
	}

	@Override
	public List<Blog> deletedBlog() {
		List<Blog> list = new ArrayList<>();
		Blog blog = new Blog();
		String query = "select count(*) from deleted_blogs";
		try {
			ResultSet rs = createPreparedStatement(query).executeQuery();
			while (rs.next()) {

				blog.setId(rs.getInt("id"));
				blog.setBody(rs.getString("body"));
				blog.setTitle(rs.getString("title"));
				blog.setCreated_at("created_at");
				blog.setUpdated_at(rs.getString("updated_at"));

				list.add(blog);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Integer> deletedBlogAcrMonths() {
		int count = 0;
		String query = null;
		List<Integer> list = new ArrayList<>(13);
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {

				query = "SELECT * FROM deleted_blogs WHERE deleted_at LIKE '%-0" + i + "-%' ";

			} else {
				query = "SELECT * FROM deleted_blogs WHERE deleted_at LIKE '%-" + (i) + "-%' ";
			}
			ResultSet rs;
			try {
				rs = createPreparedStatement(query).executeQuery();
				while (rs.next()) {
					count++;
				}
				if (i == 1) {
					list.add(0, count);
				} else {
					list.add(i - 1, count);
				}
				count = 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	public List<Blog> listAllBlogs() {
		List<Blog> blogs = new ArrayList<>();

		try {
			ResultSet rs = createPreparedStatement("select * from blogs").executeQuery();
			AllUserRowMapper allUserRowMapper = new AllUserRowMapper();
			blogs = allUserRowMapper.mapRow(rs);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return blogs;
	}

	@Override
	public List<Integer> createdBlogAcrMonths() {
		int count = 0;
		String query = null;
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {

			if (i < 10) {

				query = "SELECT * FROM blogs WHERE created_at LIKE '%-0" + i + "-%' ";

			} else {
				query = "SELECT * FROM blogs WHERE created_at LIKE '%-" + (i) + "-%' ";
			}

			ResultSet rs;
			try {
				rs = createPreparedStatement(query).executeQuery();
				while (rs.next()) {
					count++;
				}
				if (i == 1) {
					list.add(0, count);
				} else {
					list.add(i - 1, count);
				}
				count = 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return list;
	}

	@Override
	public int createdBlogAcrMonthsCount() {
		int tempCount = 0;
		int count = 0;
		String query;
		for (int i = 1; i <= 12; i++) {

			if (i < 10) {

				query = "SELECT * FROM blogs WHERE created_at LIKE '%-0" + i + "-%' ";

			} else {
				query = "SELECT * FROM blogs WHERE created_at LIKE '%-" + (i) + "-%' ";
			}
			ResultSet rs;
			try {
				rs = createPreparedStatement(query).executeQuery();
				while (rs.next()) {
					tempCount++;
				}

				count += tempCount;
				tempCount = 0;

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return count;
	}

	@Override
	public int deletedBlogAcrMonthsCount() {
		int tempCount = 0;
		int count = 0;
		String query = null;
		for (int i = 0; i <= 12; i++) {
			if (i < 10) {

				query = "SELECT * FROM deleted_blogs WHERE deleted_at LIKE '%-0" + i + "-%' ";

			} else {
				query = "SELECT * FROM deleted_blogs WHERE deleted_at LIKE '%-" + (i) + "-%' ";
			}
			ResultSet rs;
			try {
				rs = createPreparedStatement(query).executeQuery();
				while (rs.next()) {
					tempCount++;
				}
				count += tempCount;
				tempCount = 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return count;
	}

	@Override
	public void createBlog(Blog blog, String[] categories) {
		insert(blog);
		String query;
		int categories_id = 0;

		List<Blog> blogs = new ArrayList<>();
		try {
			ResultSet rs = createPreparedStatement("SELECT * FROM blogs;").executeQuery();

			while (rs.next()) {
				Blog blog1 = new Blog();
				blog1.setId(rs.getInt("id"));
				blog1.setTitle(rs.getString("title"));
				blog1.setBody(rs.getString("body"));
				String created = new DateUtil().convertDateStringToString(rs.getString("created_at"));
				blog1.setCreated_at(created);
				String updated = new DateUtil().convertDateStringToString(rs.getString("updated_at"));
				blog1.setCreated_at(updated);
				blogs.add(blog1);
			}
		} catch (SQLException ex) {

		}

		for (Blog blogTemp : blogs) {
			/*
			 * if(blogTemp.getBody().equalsIgnoreCase(blog.getBody())&&(blogTemp.
			 * getCreated_at().equalsIgnoreCase(blog.getCreated_at()))
			 * &&blogTemp.getTitle().equalsIgnoreCase(blog.getTitle())&&blogTemp.
			 * getUpdated_at().equalsIgnoreCase(blog.getUpdated_at()) ) {
			 */
			String created = new DateUtil().convertDateStringToString(blog.getCreated_at());
			System.out.println("new:" + blogTemp.getCreated_at() + ":new:" + created);
			if (blogTemp.getTitle().equalsIgnoreCase(blog.getTitle())
					&& blogTemp.getCreated_at().equalsIgnoreCase(created)) {
				blog.setId(blogTemp.getId());
				System.out.println("id setted");
			}

		}

		for (int i = 0; i < categories.length; i++) {
			categories_id = Integer.parseInt(categories[i]);
			query = "insert into blogcategories(blog_id,category_id) values(" + blog.getId() + "," + categories_id
					+ ")";
			try {
				createPreparedStatement(query).execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
