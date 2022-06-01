
package ru.llax.jnews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.llax.jnews.model.News;


public interface NewsRepository extends JpaRepository<News, Long> {
    
}
