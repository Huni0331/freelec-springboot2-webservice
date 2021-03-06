package com.huni.book.springboot.web;


import com.huni.book.springboot.config.auth.LoginUser;
import com.huni.book.springboot.config.auth.dto.SessionUser;
import com.huni.book.springboot.domain.user.User;
import com.huni.book.springboot.service.posts.PostsService;
import com.huni.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("googleName", user.getName());
        }

        return "index";
    }

//    @GetMapping("/")
//    public String index() {
//        return "index"; //자동으로 index.mustache로 전환됨
//    }
//
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
