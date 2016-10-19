package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.MenuType;

import java.util.List;

/**
 * Created by rumman on 10/18/16.
 */
public interface MenuTypeDao {

    public void initMenuType();

    public List<MenuType> findAll();
}
