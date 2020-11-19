package framework.dao;

import java.util.List;

public interface DAO<T, ID> {

  //Customer Data
  List<T> getList();

  T getById(ID id);

  void add(T t);

  void delete(ID id);

  boolean update(T t);

}
