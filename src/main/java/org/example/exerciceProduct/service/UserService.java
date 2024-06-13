package org.example.exerciceProduct.service;

import org.example.exerciceProduct.model.User;
import org.example.exerciceProduct.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {

    private UserRepository userRepository;
    private SessionFactory _sessionFactory;
    private Session session;

    public UserService(SessionFactory sessionFactory) {
        _sessionFactory = sessionFactory;
    }

    public User getUser(int id) {
        User user = null;
        session = _sessionFactory.openSession();
        userRepository = new UserRepository(session);
        try {
            user = userRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public boolean createUser(String email, String name, String password) {
        boolean result = false;
        session = _sessionFactory.openSession();
        session.beginTransaction();
        userRepository = new UserRepository(session);
        User user = new User(email, name, password);
        try {
            userRepository.create(user);
            session.getTransaction().commit();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public List<User> getUsers() {
        List<User> users = null;
        session = _sessionFactory.openSession();
        userRepository = new UserRepository(session);
        try {
            users = userRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }
}
