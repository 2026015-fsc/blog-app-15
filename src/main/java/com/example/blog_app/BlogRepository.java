package com.example.blog_app;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class BlogRepository {
    private JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Blog> preview() {
        return jdbcClient.sql("SELECT id, title, notes FROM blog")
                .query(Blog.class)
                .list();
    }

    public List<Blog> previewMe() {
        return jdbcClient.sql("SELECT id, title, notes FROM blog WHERE isMe = true")
                .query(Blog.class)
                .list();
    }

    public List<Blog> searchMyByTitle(String keyword) {
        return jdbcClient.sql("SELECT id, title, notes FROM blog WHERE title LIKE :keyword AND isMe = true")
                .param("keyword", "%" + keyword + "%")
                .query(Blog.class)
                .list();
    }

    // public List<Blog> previewLimit() {
    // return jdbcClient.sql("SELECT id, title, notes FROM blog LIMIT 6")
    // .query(Blog.class)
    // .list();
    // }

    public void addBlog(Blog blog) {
        jdbcClient.sql("INSERT INTO blog (title, notes) VALUES (:title, :notes)")
                .param("title", blog.getTitle())
                .param("notes", blog.getNotes())
                .update();
    }

    // public int countBlog() {
    // return jdbcClient.sql("SELECT COUNT(*) FROM blog WHERE completed = false")
    // .query(Integer.class)
    // .single();
    // }

    // public void deleteBlog() {

    // }

    public List<Blog> searchByTitle(String keyword) {
        return jdbcClient.sql("SELECT id, title, notes FROM blog WHERE title LIKE :keyword")
                .param("keyword", "%" + keyword + "%")
                .query(Blog.class)
                .list();
    }

    public Optional<Blog> findById(Long id) {
        return jdbcClient.sql("SELECT id, title, notes FROM blog WHERE id = :id")
                .param("id", id)
                .query(Blog.class)
                .optional();
    }

    public void update(Long id, String title, String notes) {
        jdbcClient.sql("UPDATE blog SET title = :title, notes = :notes WHERE id = :id")
                .param("title", title)
                .param("notes", notes)
                .param("id", id)
                .update();
    }

}
