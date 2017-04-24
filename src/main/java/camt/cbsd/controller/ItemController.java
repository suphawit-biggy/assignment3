package camt.cbsd.controller;

import camt.cbsd.entity.Item;
import camt.cbsd.services.ItemService;
import camt.cbsd.services.ItemServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response.ResponseBuilder;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@Path("/item")
@ConfigurationProperties(prefix = "server")
public class ItemController {
    ItemService itemService;
    String imageServerDir;
    String imageUrl;
    String baseUrl;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageServerDir(String imageServerDir) {
        this.imageServerDir = imageServerDir;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        List<Item> items = itemService.getItems();
        return Response.ok(items).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") long id) {
        Item item = itemService.findById(id);
        if (item != null)
            return Response.ok(item).build();
        else
            return Response.status(Response.Status.NO_CONTENT).build();

    }

    @OPTIONS
    public Response checkOK() {
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response uploadOnlyItem(Item item) {

        itemService.addItem(item);
        return Response.ok().entity(item).build();

    }

    @POST
    @Path("/image")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces({MediaType.TEXT_PLAIN})
    public Response uploadImage(@FormDataParam("file") InputStream fileInputStream,
                                @FormDataParam("file") FormDataContentDisposition cdh) throws IOException {
        try {
            BufferedImage img = ImageIO.read(fileInputStream);
            String oldFilename = cdh.getFileName();
            String ext = FilenameUtils.getExtension(oldFilename);
            String newFilename = Integer.toString(LocalTime.now().hashCode(), 16) + Integer.toString(oldFilename.hashCode(), 16) + "." + ext;
            File targetFile = Files.createFile(Paths.get(imageServerDir + newFilename)).toFile();
            ImageIO.write(img, ext, targetFile);

            return Response.ok(baseUrl + imageUrl + newFilename).build();
        } catch (NullPointerException e) {
            return Response.status(202).build();
        }
    }


    @POST
    @Path("/item")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadItem(@FormDataParam("file") InputStream fileInputStream,
                               @FormDataParam("file") FormDataContentDisposition cdh,
                               @FormDataParam("student") FormDataBodyPart dataBodyPart) throws Exception {

        BufferedImage img = ImageIO.read(fileInputStream);

        dataBodyPart.setMediaType(MediaType.APPLICATION_JSON_TYPE);
        Item item = dataBodyPart.getValueAs(Item.class);

        return Response.ok(item).build();
    }

    @GET
    @Path("/images/{fileName}")
    @Produces({"image/png", "image/jpg", "image/gif"})
    public Response getItemImage(@PathParam("fileName") String filename) {
        File file = Paths.get(imageServerDir + filename).toFile();
        if (file.exists()) {
            ResponseBuilder responseBuilder = Response.ok((Object) file);
            responseBuilder.header("Content-Disposition", "attachment; filename=" + filename);
            return responseBuilder.build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
