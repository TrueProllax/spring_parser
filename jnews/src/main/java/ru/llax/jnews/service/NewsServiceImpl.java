
package ru.llax.jnews.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.llax.jnews.model.News;
import ru.llax.jnews.repository.NewsRepository;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository repository;
    
    public void save(News news) {
        repository.save(news);
    }

    public boolean isExist(String newsTitle) {
        List <News> allNews = repository.findAll();
        
        for (News n: allNews) {
            if (n.getTitle().equals(newsTitle)) {
                return true;
            }
        }
        
        return false;
    }

    public List<News> getAllNews() {
        return repository.findAll();
    }
    
}
