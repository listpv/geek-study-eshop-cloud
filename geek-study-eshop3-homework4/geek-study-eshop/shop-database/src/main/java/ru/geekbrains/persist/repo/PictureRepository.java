package ru.geekbrains.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persist.model.Picture;

import java.util.List;
import java.util.Optional;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT p FROM Picture p JOIN p.pictureData pc WHERE p.id = :pictureId AND pc.fileName IS NOT NULL")
    Optional<Picture> findByIdFilenameNotNull(@Param("pictureId") Long pictureId);

    @Query("SELECT p FROM Picture p JOIN p.pictureData pc WHERE p.id = :pictureId AND pc.data IS NOT EMPTY")
    Optional<Picture> findByIdDataNotNull(@Param("pictureId") Long pictureId);

    @Query("SELECT p FROM Picture p JOIN p.pictureData pc WHERE pc.data IS NOT EMPTY")
    List<Picture> findAllDataNotNull();

    @Query("SELECT p FROM Picture p JOIN p.pictureData pc WHERE pc.fileName IS NOT NULL")
    List<Picture> findAllFilenameNotNull();

    @Query("SELECT pc.fileName From Picture p Join p.pictureData pc WHERE p.id = :pictureId")
    String findFileNameById(@Param("pictureId") Long pictureId);

}
