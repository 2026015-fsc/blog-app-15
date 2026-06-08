package com.example.blog_app;

public class BlogForm {
    private Long id;
    private String title;
    private String notes;

    public BlogForm(Long id, String title, String notes) {
        this.id = id;
        this.title = title;
        this.notes = notes;
    }

    public BlogForm() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

}
