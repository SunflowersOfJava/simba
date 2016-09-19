package com.caozj.sigar.dao;

import java.util.List;

import com.caozj.framework.util.jdbc.Pager;
import com.caozj.sigar.model.Capability;

/**
 * 性能Dao
 * 
 * @author caozj
 * 
 */
public interface CapabilityDao {

  void add(Capability capability);

  void update(Capability capability);

  void delete(long id);

  List<Capability> listAll();

  int count();

  int countBy(String field, Object value);

  List<Capability> page(Pager page);

  Capability get(long id);

  Capability getBy(String field, Object value);

  Capability getByAnd(String field1, Object value1, String field2, Object value2);

  Capability getByOr(String field1, Object value1, String field2, Object value2);

  List<Capability> listBy(String field, Object value);

  List<Capability> listByAnd(String field1, Object value1, String field2, Object value2);

  List<Capability> listByOr(String field1, Object value1, String field2, Object value2);

  List<Capability> pageBy(String field, Object value, Pager page);

  List<Capability> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

  List<Capability> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);

  void deleteByTime(String startTime, String endTime);

}
