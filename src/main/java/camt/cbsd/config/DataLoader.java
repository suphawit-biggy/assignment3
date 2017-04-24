package camt.cbsd.config;

import camt.cbsd.dao.ItemDao;
import camt.cbsd.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by CAMT on 4/7/2017.
 */
@ConfigurationProperties(prefix = "server")
@Component
public class DataLoader implements ApplicationRunner {
    ItemDao itemDao;

    @Autowired
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    String baseUrl;
    String imageUrl;
    String imageBaseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        imageBaseUrl = baseUrl + imageUrl;
        itemDao.addItem(Item.builder().name("ePhone").des("12345678901234567890123456789012345678901234567890123").picture(imageBaseUrl + "[id].jpg").amount(2).rating(2.6).price(10000).build());
        itemDao.addItem(Item.builder().name("iPhone").des("12345678901234567890123456789012345678901234567890").picture(imageBaseUrl + "[id].jpg").amount(3).rating(2.3).price(10000).build());
    }
}
