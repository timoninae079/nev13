package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {

    Book book1 = new Book(11, "bookName1", 1, "bookAuthor1");
    Book book2 = new Book(22, "bookName2", 12, "bookAuthor1");
    Book book3 = new Book(333, "book3", 100, "bookAuthor3");
    Book book4 = new Book(4, "bookName2", 90, "bookAuthor2");


    Smartphone smartphone1 = new Smartphone(1, "smartphoneName1", 1, "smartphoneManufacturer1");
    Smartphone smartphone2 = new Smartphone(2, "smartphoneName2", 2, "smartphoneManufacturer2");
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    @Test
    public void shouldAddNothing() {

        Product[] expected = {};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddOnlyOneProduct() {
        manager.add(book1);
        Product[] expected = {book1};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddOnlyBooks() {
        manager.add(book1);
        manager.add(book2);
        Product[] expected = {book1, book2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddOnlySmartphones() {
        manager.add(smartphone1);
        manager.add(smartphone2);
        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddBooksAndSmartphones() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
        Product[] expected = {book1, book2, smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    //поиск по продуктам search by
    @Test
    public void shouldSearchByWithOnlyOneResult() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = {book1};
        Product[] actual = manager.searchBy("bookName1");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWithNoResults() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = {};
        Product[] actual = manager.searchBy("bookName3");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSeveralResults() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);

        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = {book1, book2, book4};
        Product[] actual = manager.searchBy("bookName");

        Assertions.assertArrayEquals(expected, actual);
    }

    // метод определения соответствия товара product запросу search
    @Test
    public void shouldMatch() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        boolean expected = true;
        boolean actual = manager.matches(smartphone1, "smartphoneName1");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotMatch() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        boolean expected = false;
        boolean actual = manager.matches(book1, "smartphoneManufacturer1");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSaveOnlyBooks() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(book2);
        Product[] expected = {book1, book2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSaveOnlySmartphones() {
        ProductRepository repo = new ProductRepository();
        repo.save(smartphone1);
        repo.save(smartphone2);
        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSaveBooksAndSmartphones() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(smartphone1);
        repo.save(book2);
        repo.save(smartphone2);
        Product[] expected = {book1, smartphone1, book2, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSaveNothing() {
        ProductRepository repo = new ProductRepository();
        Product[] expected = {};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveOneProductIfIdCorrect() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(smartphone1);
        repo.save(book2);
        repo.save(smartphone2);
        repo.removeById(book2.getId());

        Product[] expected = {book1, smartphone1, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldRemoveSeveralProductsIfIdCorrect() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(smartphone1);
        repo.save(book2);
        repo.save(smartphone2);
        repo.removeById(book2.getId());
        repo.removeById(smartphone2.getId());

        Product[] expected = {book1, smartphone1};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldRemoveNothingIfIdNotCorrect() {
        ProductRepository repo = new ProductRepository();
        repo.save(book1);
        repo.save(smartphone1);
        repo.save(book2);
        repo.save(smartphone2);

        Assertions.assertThrows(RuntimeException.class, () -> {
            repo.removeById(28);
        });

        Product[] expected = {book1, smartphone1, book2, smartphone2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}




