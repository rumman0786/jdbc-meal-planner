package net.therap.mealplanner.dao;

import net.therap.mealplanner.connection_manager.MysqlConnector;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.MenuType;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public class DishDaoImpl implements DishDao {

    @Override
    public List<Dish> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Dish> dishList = session.createCriteria(Dish.class).list();
        tx.commit();
        session.close();
        return dishList;
    }

    @Override
    public List<Dish> findById(int dishId) {
        return null;
    }

    @Override
    public List<Dish> findByName(String dishName) {
        return null;
    }

    @Override
    public boolean insertDish(Dish dish) {
        List<Dish> dishList = findAll();
        if (!dishList.contains(dish)) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(dish);
            tx.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDish(Dish dish) {
        List<Dish> dishList = findAll();
        if (dishList.contains(dish)) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(dish);
            tx.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDish(Dish dish) {

        List<Dish> dishList = findAll();
        if (dishList.contains(dish)) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(dish);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }
}
