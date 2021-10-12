package com.tianshu.dao;

import com.tianshu.domain.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<GroceryItem,String> {

    @Query("{name:'?0'}")
    public GroceryItem findItemByName(String name);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1, 'category' : 1}")
    public List<GroceryItem> findAllByCategory(String category);

    public long count();
}
