package ru.geekbrains.service;

import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;

import java.util.List;
import java.util.Optional;

public interface PictureService {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    PictureData createPictureData(byte[] picture);

    // TODO перенести сюда функционал получения списка картинок

    List<Picture> findAll();

    // TODO перенести сюда функционал для удаления картинок

    void deleteById(Long id);
}
