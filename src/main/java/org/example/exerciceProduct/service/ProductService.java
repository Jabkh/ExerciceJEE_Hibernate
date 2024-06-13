package org.example.exerciceProduct.service;

import org.example.exerciceProduct.model.Product;
import org.example.exerciceProduct.repository.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class ProductService {

    private ProductRepository productRepository;
    private SessionFactory sessionFactory;

    public ProductService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product getProduct(int id) {
        Product product = null;
        try (Session session = sessionFactory.openSession()) {
            productRepository = new ProductRepository(session);
            product = productRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean createProduct(String brand, String reference, LocalDate purchaseDate, double price, int stock) {
        boolean result = false;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            productRepository = new ProductRepository(session);
            Product product = new Product(brand, reference, purchaseDate, price, stock);
            productRepository.create(product);
            tx.commit();
            result = true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean updateProduct(Product product) {
        boolean result = false;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            productRepository = new ProductRepository(session);
            productRepository.update(product);
            tx.commit();
            result = true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public boolean deleteProduct(int id) {
        boolean result = false;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            productRepository = new ProductRepository(session);
            Product product = productRepository.findById(id);
            if (product != null) {
                productRepository.delete(product);
                tx.commit();
                result = true;
            }
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public List<Product> getProducts() {
        List<Product> products = null;
        try (Session session = sessionFactory.openSession()) {
            productRepository = new ProductRepository(session);
            products = productRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
}
