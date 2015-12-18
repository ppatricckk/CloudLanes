package com.cloudlanes.db.dao;

import java.util.List;

public interface GenericDao<E,K> {
	/**
	 * Add the Entity into databse
	 * @param entity
	 */
    public boolean add(E entity) ;
    /**
     * Update the entity
     * @param entity
     */
   
    public void update(E entity) ;
    /**
     * Remove/Delete the entity
     * @param entity
     */
    public void remove(E entity);
    /**
     * Find the Entity
     * @param key
     * @return
     */
    public E find(K key);
    /**
     * Return the list of data from the corresponding entity
     * @return
     */
    public List<E> getAll() ;
    /**
     * Returns list of values in the entity that matches column value of specified column
     * @param entityType
     * @param columnName
     * @param columnValue
     * @return
     */
    public List<E> getByColumnValue(Class<E> entityType, String columnName, String columnValue);

    /**
     * Returns first entity that matches column value of specified column
     *
     * @param entityType
     * @param columnName
     * @param columnValue
     * @return
     */
  public  E getFirstByMatchedColumnValue(Class<E> entityType, String columnName, String columnValue);

  public E getByPrimaryKeyId(K id);  
}
