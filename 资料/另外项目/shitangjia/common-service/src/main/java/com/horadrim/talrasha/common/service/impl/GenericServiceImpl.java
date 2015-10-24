package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.service.GenericService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public abstract class GenericServiceImpl<T, K extends Serializable> implements GenericService<T, K> {

	protected abstract GenericDao<T, K> getGenericDao();

	@Override
	public T save(T entity) {
		return getGenericDao().save(entity);
	}

	@Override
	public T update(T entity) {
		return getGenericDao().update(entity);
	}

	@Override
	public T saveOrUpdate(T entity) {
		return getGenericDao().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		getGenericDao().delete(entity);
	}

	@Override
	public void delete(K id) {
		getGenericDao().delete(id);
	}

	@Override
	public List<T> findAll() {
		return getGenericDao().findAll();
	}

	@Override
	public T get(K id) {
		return getGenericDao().get(id);
	}

	@Override
	public T query(String sql, Object[] params) {
		return getGenericDao().query(sql, params);
	}

	@Override
	public List<T> list(String sql, Object[] params) {
		return getGenericDao().list(sql, params);
	}

	@Override
	public List<T> list(String sql, int start, int size, Object[] params) {
		return getGenericDao().list(sql, start, size, params);
	}

    @Override
    public Long count(String hql, Object[] params) {
        return getGenericDao().count(hql, params);
    }

    @Override
    public Long count() {
        return getGenericDao().count();
    }

    @Override
    public List<T> listBySQL(String sql, Object[] params) {
        return getGenericDao().listBySQL(sql,params);
    }

    @Override
    public List<T> listBySQL(String sql, int start, int size, Object[] params) {
        return getGenericDao().listBySQL(sql,start,size,params);
    }

    @Override
    public List listFieldBySQL(String sql, Object[] params) {
        return getGenericDao().listFieldBySQL(sql,params);
    }

    @Override
    public List listFieldBySQL(String sql, int start, int size, Object[] params) {
        return  getGenericDao().listFieldBySQL(sql,start,size,params);
    }

    @Override
    public void executeUpdate(String sql, Object[] params) {
        getGenericDao().executeUpdate(sql,params);
    }

    @Override
    public Long countBySQL(String sql, Object[] params) {
        return getGenericDao().countBySQL(sql,params);
    }
}
