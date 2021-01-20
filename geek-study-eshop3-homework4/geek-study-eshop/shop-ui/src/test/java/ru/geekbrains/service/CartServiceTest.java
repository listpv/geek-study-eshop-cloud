package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.model.LineItem;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertNotNull(cartService.getLineItems());
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOnProduct() {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 1);

        List<LineItem> lineItems = cartService.getLineItems();
        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals("color", lineItem.getColor());
        assertEquals("material", lineItem.getMaterial());
        assertEquals(1, lineItem.getQty());

        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());
    }

    @Test
    public void testRemoveProduct(){
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 1);
        List<LineItem> lineItemsBefore = cartService.getLineItems();

        cartService.removeProduct(expectedProduct, "color", "material");
        List<LineItem> lineItemsAfter = cartService.getLineItems();

        assertEquals(lineItemsBefore.size() - 1, lineItemsAfter.size());
    }

    @Test
    public void testRemoveProductQty(){
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal(123));
        expectedProduct.setName("Product name");

        cartService.addProductQty(expectedProduct, "color", "material", 5);
        List<LineItem> lineItemsBefore = cartService.getLineItems();
        LineItem lineItemBefore = new LineItem(expectedProduct, "color", "material");
        for(LineItem o : lineItemsBefore){
            if(o.equals(lineItemBefore)){
                lineItemBefore.setQty(o.getQty());
                break;
            }
        }

        int removeQty = 2;
        cartService.removeProductQty(expectedProduct, "color", "material", removeQty);
        List<LineItem> lineItemsAfter = cartService.getLineItems();
        LineItem lineItemAfter = new LineItem(expectedProduct, "color", "material");
        for(LineItem o : lineItemsAfter){
            if(o.equals(lineItemAfter)){
                lineItemAfter.setQty(o.getQty());
                break;
            }
        }

        assertEquals(lineItemBefore.getQty() - removeQty, lineItemAfter.getQty());

    }

    @Test
    public void testGetSubTotal(){

        int prise1 = 123;
        int prise2 = 456;
        int prise3 = 789;


        ProductRepr expectedProduct1 = new ProductRepr();
        expectedProduct1.setId(1L);
        expectedProduct1.setPrice(new BigDecimal(prise1));
        expectedProduct1.setName("Product name1");

        ProductRepr expectedProduct2 = new ProductRepr();
        expectedProduct2.setId(2L);
        expectedProduct2.setPrice(new BigDecimal(prise2));
        expectedProduct2.setName("Product name2");

        ProductRepr expectedProduct3= new ProductRepr();
        expectedProduct3.setId(3L);
        expectedProduct3.setPrice(new BigDecimal(prise3));
        expectedProduct3.setName("Product name3");

        cartService.addProductQty(expectedProduct1, "color", "material", 1);
        cartService.addProductQty(expectedProduct2, "color", "material", 2);
        cartService.addProductQty(expectedProduct3, "color", "material", 3);

        assertEquals(new BigDecimal(prise1 + prise2 * 2 + prise3 * 3), cartService.getSubTotal());
    }
}
