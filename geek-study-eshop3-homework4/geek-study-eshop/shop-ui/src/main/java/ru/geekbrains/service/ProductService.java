package ru.geekbrains.service;

import ru.geekbrains.controllers.repr.ProductRepr;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ProductService extends Serializable {

    Optional<ProductRepr> findById(Long id);

    List<List<ProductRepr>> findAllAndSplitProductsBy(int groupSize);
}
