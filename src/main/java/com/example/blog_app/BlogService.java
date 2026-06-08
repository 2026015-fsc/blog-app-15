package com.example.blog_app;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> preview() {
        return blogRepository.preview();
    }

    public List<Blog> previewMe() {
        return blogRepository.previewMe();
    }

    public List<Blog> serchMe(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return blogRepository.previewMe();
        } else {
            return blogRepository.searchMyByTitle(keyword);
        }
    }

    // public List<Blog> previewLimit() {
    // return blogRepository.previewLimit();
    // }

    public void addBlog(BlogForm form) {
        if (form.getTitle().equals(null)) {
            throw new IllegalArgumentException("タイトルかけや");
        }

        blogRepository.addBlog(new Blog(null, form.getTitle(), form.getNotes()));
    }

    // formに値がない場合はfindAll(),ある場合はserchByTitle
    // isBlank() == スペースが入っている
    public List<Blog> serch(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return blogRepository.preview();
        } else {
            return blogRepository.searchByTitle(keyword);
        }
    }

    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    public void update(Long id, BlogForm form) {
        blogRepository.update(id, form.getTitle(), form.getNotes());
    }

}
