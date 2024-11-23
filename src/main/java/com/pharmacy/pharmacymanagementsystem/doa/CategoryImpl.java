package com.pharmacy.pharmacymanagementsystem.doa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacymanagementsystem.models.categories;

@Repository
public class CategoryImpl implements CategoryRepo {

    @Autowired
    private JdbcTemplate t;

    @Override
    public List<categories> findAll() {
        return t.query("SELECT * FROM categories", new BeanPropertyRowMapper<>(categories.class));
    }

    @Override
    public void insertCategory(categories c)
    {
        String sql="INSERT INTO categories(cat_name) VALUES (?)";

        t.update(sql, c.getCat_name());
    }

    @Override
    public void updateCategory(categories c)
    {
        String sql="UPDATE categories SET cat_name=? WHERE cat_id=?";

        t.update(sql, c.getCat_name(),c.getCat_id());
    }

    @Override
    public void deleteCategory(int i)
    {
        String sql="DELETE FROM categories WHERE cat_id=?";

        t.update(sql,i);
    }
}
