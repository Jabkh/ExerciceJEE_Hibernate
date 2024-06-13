package org.example.exerciceProduct.repository;

import org.example.exerciceProduct.model.User;
import org.hibernate.Session;

import java.util.List;

public class UserRepository extends Repository<User> {

    public UserRepository(Session session) {
        super(session);
    }

    @Override
    public User findById(int id) {
        return _session.get(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return _session.createQuery("from User", User.class).list();
    }

    public User findByEmail(String email) {
        return _session.createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .uniqueResult();
    }

    public void delete(User user) {
        _session.delete(user);
    }

    public void update(User user) {
        _session.update(user);
    }
}
