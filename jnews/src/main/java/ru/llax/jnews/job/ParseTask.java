package ru.llax.jnews.job;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.llax.jnews.model.News;
import ru.llax.jnews.service.NewsService;


@Component
public class ParseTask {
    
    @Autowired
    NewsService newsService;
    
    @Scheduled(fixedDelay = 10000)
    public void parseNewNews() {
        String url = "https://yandex.ru/news";
        
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Microsoft Edge")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();
            Elements news = doc.getElementsByClass("mg-card__link");
            for (Element el : news) {
                String title = el.ownText();
                if (!newsService.isExist(title)) {
                    News obj = new News();
                    obj.setTitle(title);
                    newsService.save(obj);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ParseTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
