package hello.itemservice.repository.v2;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.QItem;
import hello.itemservice.repository.ItemSearchCond;
import jakarta.persistence.EntityManager;

@Repository 
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory query;

    public ItemQueryRepositoryV2(EntityManager em) {
        this.query=new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond) {

        Integer maxPrice = cond.getMaxPrice();
        String itemName = cond.getItemName();

        List<Item> result = query
                            .select(QItem.item)
                            .from(QItem.item)
                            .where(likeItemName(itemName), maxPrice(maxPrice))
                            .fetch();
        
        return  result;
    }

    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return QItem.item.itemName.like("%" + itemName + "%");
        }
        return null;
    }
    
    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return QItem.item.price.loe(maxPrice);
        }
        return null;
    }  
}
