package hax.expwnge.dao;

import java.util.List;

public interface GenericDAO<T> {
  List<T> listAll();
  T readById(long l);
}
