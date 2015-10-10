package com.horadrim.talrasha.common.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {

    public T save(T entity);

    public T update(T entity);

    public T saveOrUpdate(T entity);

    public void delete(T entity);

    public void delete(K id);

    public List<T> findAll();

    public T get(K id);

    public T query(final String hql, final Object[] params);

    public List<T> list(final String hql, final Object[] params);

    public List<T> list(final String hql, int start, int size, final Object[] params);

    public Long count(final String hql, final Object[] params);

    public Long count();

    public List<T> listBySQL(final String sql, final Object[] params);

    public List<T> listBySQL(String sql, int start, int size, Object[] params);

    public List listFieldBySQL(final String sql, final Object[] params);

    public List listFieldBySQL(final String sql, int start, int size, final Object[] params);

    public Long countBySQL(final String sql, final Object[] params);
//    public void executeUpdateSQL(final String sql , final Object[] params);

    public void executeUpdate(final String sql, final Object[] params);

}
