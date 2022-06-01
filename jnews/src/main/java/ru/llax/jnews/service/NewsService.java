package ru.llax.jnews.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.llax.jnews.model.News;


@Service
public interface NewsService {
    
    public void save(News news);
    public boolean isExist(String newsTitle);
    public List<News> getAllNews();
    
}
