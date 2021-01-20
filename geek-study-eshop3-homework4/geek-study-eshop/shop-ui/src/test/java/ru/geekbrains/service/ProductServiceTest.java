package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        productRepository = mock(ProductRepository.class);
        ProductServiceImpl impl = new ProductServiceImpl();
        impl.setProductRepository(productRepository);
        productService = impl;
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("Product name");
        expectedProduct.setCategory(expectedCategory);
        expectedProduct.setBrand(expectedBrand);
        expectedProduct.setPictures(new ArrayList<>());
        expectedProduct.setPrice(new BigDecimal(12345));

        when(productRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedProduct));

        Optional<ProductRepr> opt = productService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedProduct.getId(), opt.get().getId());
        assertEquals(expectedProduct.getName(), opt.get().getName());
    }

    @Test
    public void testFindAllAndSplitProductsByGroupSize(){

        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");

        Brand expectedBrand = new Brand();
        expectedBrand.setId(1L);
        expectedBrand.setName("Brand name");

        Product expectedProduct1 = new Product();
        expectedProduct1.setId(1L);
        expectedProduct1.setName("Product name");
        expectedProduct1.setCategory(expectedCategory);
        expectedProduct1.setBrand(expectedBrand);
        expectedProduct1.setPictures(new ArrayList<>());
        expectedProduct1.setPrice(new BigDecimal(12345));

        Product expectedProduct2 = new Product();
        expectedProduct2.setId(2L);
        expectedProduct2.setName("Product name");
        expectedProduct2.setCategory(expectedCategory);
        expectedProduct2.setBrand(expectedBrand);
        expectedProduct2.setPictures(new ArrayList<>());
        expectedProduct2.setPrice(new BigDecimal(12345));

        Product expectedProduct3 = new Product();
        expectedProduct3.setId(3L);
        expectedProduct3.setName("Product name");
        expectedProduct3.setCategory(expectedCategory);
        expectedProduct3.setBrand(expectedBrand);
        expectedProduct3.setPictures(new ArrayList<>());
        expectedProduct3.setPrice(new BigDecimal(12345));

        Product expectedProduct4 = new Product();
        expectedProduct4.setId(4L);
        expectedProduct4.setName("Product name");
        expectedProduct4.setCategory(expectedCategory);
        expectedProduct4.setBrand(expectedBrand);
        expectedProduct4.setPictures(new ArrayList<>());
        expectedProduct4.setPrice(new BigDecimal(12345));

        Product expectedProduct5 = new Product();
        expectedProduct5.setId(5L);
        expectedProduct5.setName("Product name");
        expectedProduct5.setCategory(expectedCategory);
        expectedProduct5.setBrand(expectedBrand);
        expectedProduct5.setPictures(new ArrayList<>());
        expectedProduct5.setPrice(new BigDecimal(12345));

        List<Product> productList = new ArrayList<>();
        productList.add(expectedProduct1);
        productList.add(expectedProduct2);
        productList.add(expectedProduct3);
        productList.add(expectedProduct4);
        productList.add(expectedProduct5);

        when(productRepository.findAll())
                .thenReturn(productList);

        List<List<ProductRepr>> result = productService.findAllAndSplitProductsBy(2);

        assertTrue(!result.isEmpty());
        assertEquals(3, result.size());
        assertEquals(2, result.get(0).size());
        assertEquals(1, result.get(2).size());

    }
}
