
package ru.llax.jnews.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.llax.jnews.model.News;
import ru.llax.jnews.service.NewsService;

@RestController
public class NewsController {
    
    @Autowired
    NewsService newsService;
    
    @GetMapping(value = "/news")
    public List <News> getAllNews() {
        return newsService.getAllNews();
    }
}
