package camt.cbsd.repository;

import camt.cbsd.entity.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by CAMT on 4/7/2017.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findById(long id);
}
