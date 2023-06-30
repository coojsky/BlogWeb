package org.example.controller;

import org.example.domain.Article;
import org.example.dto.AddArticleRequest;
import org.example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlogApiController {
    @Autowired
    BlogService blogService;


    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest dto /* HttpRequest 객체를 직렬화를 통해 class에 넣어준다. */) {
        System.out.println("addArticle() In");

        Article savedArticle = blogService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);

    }

    @GetMapping("/api/articles/find")
    public ResponseEntity<List<Article>> findArticles() {
        System.out.println("findArticle()In");

        List<Article> articleList = blogService.findAll();

        System.out.println("findArticle() Out");

        return ResponseEntity.status(HttpStatus.OK).body(articleList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> findArticle(@PathVariable long id){
        System.out.println("findArticle()In id[" + id + "" + "]");

        Article article = blogService.findById(id);

        System.out.println("findArticle() Out");

        return ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        blogService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable long id, @RequestBody Article a) {
        Article article = blogService.update(id, a);
        return ResponseEntity.status(HttpStatus.OK).body(article);
    }


}
