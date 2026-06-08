package com.example.blog_app;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // @GetMapping("/home")
    // public String home(Model model) {
    // model.addAttribute("home", blogService.preview());
    // return "home";
    // }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("home", blogService.preview());
        return "home";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("blog", blogService.preview());
        return "blog";
    }

    @GetMapping("/myblog")
    public String myBlog(Model model) {
        model.addAttribute("blog", blogService.previewMe());
        return "myblog";
    }

    @GetMapping("/myblog/{id}")
    public String myDetail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/myblog";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blog/detail";// 戻るHTML
    }

    @GetMapping("/myblogSerch")
    public String serchMyTitle(@RequestParam String serchTitle, Model model) {
        model.addAttribute("blog", blogService.serchMe(serchTitle));
        return "myblog";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("write", blogService.preview());
        return "write";
    }

    @GetMapping("/save")
    public String save(Model model) {
        model.addAttribute("save", blogService.preview());
        return "save";
    }

    @GetMapping("/retire")
    public String retire(Model model) {
        model.addAttribute("retire", blogService.preview());
        return "retire";
    }

    @GetMapping("/blog/edit")
    public String rename() {
        // model.addAttribute("ed", blogService.preview());
        // return "home";
        return "/blog/edit";
    }

    // 詳細を表示する
    @GetMapping("/blog/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blog";
        }
        model.addAttribute("blog", blogOpt.get());
        return "blog/detail";// 戻るHTML
    }

    // Blogを追加する
    @PostMapping("/write")
    public String newPara(@ModelAttribute BlogForm form) {
        blogService.addBlog(form);
        return "redirect:/home";
    }

    // Titleキーワード検索
    @GetMapping("/blogSerch")
    public String serchTitle(@RequestParam String serchTitle, Model model) {
        model.addAttribute("blog", blogService.serch(serchTitle));
        return "blog";
    }

    // 編集
    @GetMapping("/blog/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Blog> blogOpt = blogService.findById(id);
        if (blogOpt.isEmpty()) {
            return "redirect:/blog";
        }
        Blog blog = blogOpt.get();

        BlogForm form = new BlogForm();
        form.setTitle(blog.getTitle());
        form.setNotes(blog.getNotes());
        model.addAttribute("blogForm", form);
        model.addAttribute("blogId", id);
        return "blog/edit";
    }

    @PostMapping("/blog/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BlogForm form) {
        blogService.update(id, form);
        return "redirect:/blog";
    }

}
