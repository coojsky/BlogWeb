package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.domain.Article;
import org.example.dto.AddArticleRequest;
import org.example.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    @Autowired //final BlogRepository blogRepository와 동일한 기능
    private BlogRepository blogRepository;

    public Article save(AddArticleRequest dto) {
        System.out.println("save()");
        return blogRepository.save(dto.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional //진행하는 동안 방해를 안받도록 설정
    public Article update(long id, Article a){
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(a.getTitle(), a.getContent());

        return article;
    }
}
