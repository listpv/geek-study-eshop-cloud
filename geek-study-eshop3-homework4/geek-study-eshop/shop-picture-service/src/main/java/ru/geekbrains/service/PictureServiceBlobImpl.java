package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.PictureData;
import ru.geekbrains.persist.repo.PictureRepository;

import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "picture.storage.type", havingValue = "database")
public class PictureServiceBlobImpl implements PictureService {

    private final PictureRepository repository;

    @Autowired
    public PictureServiceBlobImpl(PictureRepository repository) {
        this.repository = repository;
    }

//    @Override
//    public Optional<String> getPictureContentTypeById(long id) {
//        return repository.findById(id)
//                // TODO перенести проверку на уровень JPQL запроса
//                .filter(pic -> pic.getPictureData().getData() != null)
//                .map(Picture::getContentType);
//    }
//
//    @Override
//    public Optional<byte[]> getPictureDataById(long id) {
//        return repository.findById(id)
//                // TODO перенести проверку на уровень JPQL запроса
//                .filter(pic -> pic.getPictureData().getData() != null)
//                .map(pic -> pic.getPictureData().getData());
//    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return repository.findByIdDataNotNull(id)
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {
        return repository.findByIdDataNotNull(id)
                .map(pic -> pic.getPictureData().getData());
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }

    @Override
    public List<Picture> findAll() {
        return repository.findAllDataNotNull();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
