package com.grupo2.projeto.repository.jdbc;

import com.grupo2.projeto.model.JDBCTable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface GenericRepository<T extends JDBCTable>
{
    void insert(T dto);

    void update(T dto);

    void deleteById(Long id);
    T findById(Long id) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;

    List<T> findAll() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException;
}
