package org.example.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.example.domain.Article;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {
    @Column(name="title", updatable = false)
    private String title;

    @Column(name="content", updatable = false)
    private String content;

    public Article toEntity()
    {
        //builder는 static 메서드
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
