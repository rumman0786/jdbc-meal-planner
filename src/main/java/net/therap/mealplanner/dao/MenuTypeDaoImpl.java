package net.therap.mealplanner.dao;

import net.therap.mealplanner.connection_manager.MysqlConnector;
import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.entity.MenuType;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rumman on 10/18/16.
 */
public class MenuTypeDaoImpl implements MenuTypeDao {
    String[] menuTypes = {"BREAKFAST", "LUNCH"};

    @Override
    public void initMenuType() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<MenuType> menuTypeList = session.createCriteria(MenuType.class).list();
        sessionFactory.close();

        if (menuTypeList.size() == 0) {
            insertMenuType(menuTypes);
        }
    }

    public boolean insertMenuType(String[] menuTypes) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        for (String menuTypeString : menuTypes) {
            //Needs to get current session for every save otherwise doesnt work.
            Session session = sessionFactory.getCurrentSession();
            MenuType menuType = new MenuType(menuTypeString);
            //start transaction
            session.beginTransaction();
            //Save the Model object
            session.save(menuType);
            //Commit transaction
            session.getTransaction().commit();
            System.out.println("MenuType ="+menuType.getCategory());

            //terminate session factory, otherwise program won't end
        }
        sessionFactory.close();
        return true;
    }

    @Override
    public List<MenuType> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<MenuType> menuTypeList = session.createCriteria(MenuType.class).list();
        sessionFactory.close();
        return menuTypeList;
    }

    public MenuType getMenuType(String type) {
        List<MenuType> menuTypeList = findAll();
        for (MenuType menuType : menuTypeList) {
            if (menuType.getCategory().equals(type)) {
                return menuType;
            }
        }
        return null;
    }

    public MenuType getMenuType(int menuTypeId) {
        List<MenuType> menuTypeList = findAll();
        for (MenuType menuType : menuTypeList) {
            if (menuType.getId() == menuTypeId) {
                return menuType;
            }
        }
        return null;
    }

}
