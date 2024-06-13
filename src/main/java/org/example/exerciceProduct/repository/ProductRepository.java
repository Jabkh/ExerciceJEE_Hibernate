package org.example.exerciceProduct.repository;

import org.example.exerciceProduct.model.Product;
import org.hibernate.Session;

import java.util.List;

public class ProductRepository extends Repository<Product> {

    public ProductRepository(Session session) {
        super(session);
    }

    @Override
    public Product findById(int id) {
        return _session.get(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return _session.createQuery("from Product ", Product.class).list();
    }

    public List<Product> findByBrand(String brand) {
        return _session.createQuery("from Product where brand = :brand", Product.class)
                .setParameter("brand", brand)
                .list();
    }

    public List<Product> findByReference(String reference) {
        return _session.createQuery("from Product where reference = :reference", Product.class)
                .setParameter("reference", reference)
                .list();
    }

    public void delete(Product product) {
        _session.delete(product);
    }

    public void update(Product product) {
        _session.update(product);
    }
}
