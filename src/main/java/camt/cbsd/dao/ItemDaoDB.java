package camt.cbsd.dao;

import camt.cbsd.dao.ItemDao;
import camt.cbsd.entity.Item;
import camt.cbsd.repository.ItemRepository;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CAMT on 4/7/2017.
 */
@Repository
@Profile("DBDataSource")
public class ItemDaoDB implements ItemDao {
    ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems() {
        return Lists.newArrayList(itemRepository.findAll());
    }

    @Override
    public Item findById(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }
}
