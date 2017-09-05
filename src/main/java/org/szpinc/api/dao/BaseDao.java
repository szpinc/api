package org.szpinc.api.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao <T> {
	
	public void add(T t);
	
	public void delete(Serializable id);
	
	public void update(T t) ;
	
	public T get(Serializable id) ;
	
	public List<T> getAll() ;
	
	public List<T> limit(Integer index, Integer size) ;
	
	public Integer size() ;
}
