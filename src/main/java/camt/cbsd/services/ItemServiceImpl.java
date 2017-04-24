package camt.cbsd.services;

import camt.cbsd.dao.ItemDao;
import camt.cbsd.entity.Item;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
@ConfigurationProperties(prefix = "server")
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;


    String imageServerDir;

    public void setImageServerDir(String imageServerDir) {
        this.imageServerDir = imageServerDir;
    }

    public List<Item> getItems() {

        return itemDao.getItems();
    }

    @Override
    public Item findById(long id) {
        return itemDao.findById(id);
    }

    @Override
    public Item addItem(Item item) {
        return itemDao.addItem(item);
    }
}
