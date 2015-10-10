package com.horadrim.talrasha.common.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.util.List;

public class GenericHibernateDao<T, K extends Serializable> implements GenericDao<T, K> {
    private final Class<T> clazz;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
	public GenericHibernateDao() {
        clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T save(T entity) {
    	getSession().save(entity);
//    	getSession().flush();
        return entity;
    }

    @Override
    public T update(T entity) {
    	getSession().update(entity);
//    	getSession().flush();
    	return entity;
    }

    @Override
    public T saveOrUpdate(T entity) {
    	getSession().saveOrUpdate(entity);
//    	getSession().flush();
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
	public T get(K id) {
        return (T) getSession().get(clazz, id);
    }

    @Override
    public void delete(T entity) {
    	getSession().delete(entity);
    }

    @Override
    public void delete(K id) {
    	getSession().delete(get(id));
    }

    @Override
	public List<T> findAll() {
    	String hql = "from " + clazz.getSimpleName();
        return list(hql, null);
    }

    @SuppressWarnings("unchecked")
	@Override
    public T query(final String hql, final Object[] params) {
        Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        return (T) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
	public List<T> list(final String hql, final Object[] params) {
    	Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        return query.list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<T> list(final String hql, final int start, final int size, final Object[] params) {
    	Query query = getSession().createQuery(hql);
        setQueryParams(query, params);
        return query.setFirstResult(start).setMaxResults(size).list();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Long count(final String hql, final Object[] params) {
        Query query = this.getSession().createQuery(hql);
        setQueryParams(query, params);
        return ((Long)query.uniqueResult());
    }

    @Override
    public Long count() {
        String hql = "select count(*) from " + clazz.getSimpleName();
        return count(hql, null);
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<T> listBySQL(String sql, Object[] params) {
        Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
        setQueryParams(query,params);
        return query.list();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<T> listBySQL(String sql, int start, int size, Object[] params) {
        Query query = this.getSession().createSQLQuery(sql).addEntity(clazz);
        setQueryParams(query,params);
        return query.setFirstResult(start).setMaxResults(size).list();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List listFieldBySQL(String sql, Object[] params) {
        Query query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        setQueryParams(query,params);
        return query.list();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List listFieldBySQL(String sql, int start, int size, Object[] params) {
        Query query = this.getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        setQueryParams(query, params);
        return query.setFirstResult(start).setMaxResults(size).list();
    }

    @Override
    public Long countBySQL(String sql, Object[] params) {
        Query query = this.getSession().createSQLQuery(sql);
        setQueryParams(query,params);
        return ((BigInteger)query.uniqueResult()).longValue();
    }

//    @Override
//    public void executeUpdateSQL(String sql, Object[] params) {
//        Query query = this.getSession().createSQLQuery(sql);
//        setQueryParams(query,params);
//        query.executeUpdate();
//    }

    @Override
    public void executeUpdate(String sql, Object[] params) {
        Query query = this.getSession().createQuery(sql);
        setQueryParams(query , params);
        query.executeUpdate();
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private void setQueryParams(Query query, Object[] params) {
        if (null == params) {
            return;
        }
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }
    }
}
