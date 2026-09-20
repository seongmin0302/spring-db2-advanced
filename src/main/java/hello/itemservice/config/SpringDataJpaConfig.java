package hello.itemservice.config;

import hello.itemservice.repository.jpa.JpaRepositoryV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepositoryV1;
import hello.itemservice.repository.jpa.SpringDataJpaItemRepository;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Configuration 
@RequiredArgsConstructor 
public class SpringDataJpaConfig {

    private final SpringDataJpaItemRepository springDataJpaItemRepository;

    @Bean 
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean ItemRepository itemRepository() {
        return new JpaRepositoryV2(springDataJpaItemRepository);
    }
}
