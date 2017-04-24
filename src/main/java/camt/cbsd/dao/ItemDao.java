package camt.cbsd.dao;


import camt.cbsd.entity.Item;

import java.util.List;

/**
 * Created by Dto on 3/15/2017.
 */
public interface ItemDao {
    List<Item> getItems();
    Item findById(long id);
    Item addItem(Item item);
}
