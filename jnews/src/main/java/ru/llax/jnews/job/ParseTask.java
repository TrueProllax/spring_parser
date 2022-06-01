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
        //String url = "https://news.ycombinator.com/";
        String url = "https://elenaruvel.com/50-faktov-o-yaponii/";
        
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Microsoft Edge")
                    .timeout(5000)
                    .referrer("https://yandex.ru/")
                    .get();
            
            Elements news = doc.getElementsByClass("entry-content");
            
            Elements newsSite = doc.getElementsByClass("sitestr");
            for (Element el : news) {
                String title = el.ownText();
                
                for (Element fact : el.getElementsByTag("li")) {
                    try {
                        String text = fact.ownText();
                            if (!newsService.isExist(text) && (text.length() < 255)) {
                                News obj = new News();
                                obj.setTitle(text);
                                newsService.save(obj);
                            }
                    } catch (Exception e) {
                        
                    }
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ParseTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
