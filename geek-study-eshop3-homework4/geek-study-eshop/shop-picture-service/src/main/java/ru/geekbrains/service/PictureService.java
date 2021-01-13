package ru.geekbrains.service;

import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.model.Product;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);

    Optional<Product> getProductByPictureId(long id);

    // TODO перенести сюда функционал получения списка картинок

    List<Picture> findAll();

    // TODO перенести сюда функционал для удаления картинок

    void deleteById(Long id);

    void removePicture(Long id);
}
