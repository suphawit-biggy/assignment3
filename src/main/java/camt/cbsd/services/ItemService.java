package camt.cbsd.services;

import camt.cbsd.entity.Item;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Dto on 3/11/2017.
 */
public interface ItemService {
    List<Item> getItems();
    Item findById(long id);
    Item addItem(Item item);
}
