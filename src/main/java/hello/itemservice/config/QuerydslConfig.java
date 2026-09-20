package hello.itemservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepositoryV1;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Configuration 
@RequiredArgsConstructor 
public class QuerydslConfig {

    private final EntityManager em;

    @Bean 
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em);
    }
}
