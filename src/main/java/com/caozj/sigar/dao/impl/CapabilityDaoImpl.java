package com.caozj.sigar.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caozj.framework.util.jdbc.Jdbc;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.framework.util.jdbc.StatementParameter;
import com.caozj.sigar.dao.CapabilityDao;
import com.caozj.sigar.model.Capability;

/**
 * 性能Dao实现类
 * 
 * @author caozj
 * 
 */
@Repository
public class CapabilityDaoImpl implements CapabilityDao {

  @Autowired
  private Jdbc jdbc;

  private static final String table = "capability";

  @Override
  public void add(Capability capability) {
    String sql = "insert into " + table + "( ip, type, subType, dateTime, value) values(?,?,?,?,?)";
    jdbc.updateForBoolean(sql, capability.getIp(), capability.getType(), capability.getSubType(),
        capability.getDateTime(), capability.getValue());
  }

  @Override
  public void update(Capability capability) {
    String sql = "update " + table
        + " set  ip = ? , type = ? , subType = ? , dateTime = ? , value = ?  where id = ?  ";
    jdbc.updateForBoolean(sql, capability.getIp(), capability.getType(), capability.getSubType(),
        capability.getDateTime(), capability.getValue(), capability.getId());
  }

  @Override
  public void delete(long id) {
    String sql = "delete from " + table + " where id = ? ";
    jdbc.updateForBoolean(sql, id);
  }

  @Override
  public List<Capability> page(Pager page) {
    String sql = "select * from " + table;
    return jdbc.queryForPage(sql, Capability.class, page);
  }

  @Override
  public List<Capability> listAll() {
    String sql = "select * from " + table;
    return jdbc.queryForList(sql, Capability.class);
  }

  @Override
  public int count() {
    String sql = "select count(*) from " + table;
    return jdbc.queryForInt(sql);
  }

  @Override
  public Capability get(long id) {
    String sql = "select * from " + table + " where id = ? ";
    return jdbc.query(sql, Capability.class, id);
  }

  @Override
  public Capability getBy(String field, Object value) {
    String sql = "select * from " + table + " where " + field + " = ? ";
    return jdbc.query(sql, Capability.class, value);
  }

  @Override
  public Capability getByAnd(String field1, Object value1, String field2, Object value2) {
    String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
    return jdbc.query(sql, Capability.class, value1, value2);
  }

  @Override
  public Capability getByOr(String field1, Object value1, String field2, Object value2) {
    String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
    return jdbc.query(sql, Capability.class, value1, value2);
  }

  @Override
  public List<Capability> listBy(String field, Object value) {
    String sql = "select * from " + table + " where " + field + " = ? ";
    return jdbc.queryForList(sql, Capability.class, value);
  }

  @Override
  public List<Capability> listByAnd(String field1, Object value1, String field2, Object value2) {
    String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
    return jdbc.queryForList(sql, Capability.class, value1, value2);
  }

  @Override
  public List<Capability> listByOr(String field1, Object value1, String field2, Object value2) {
    String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
    return jdbc.queryForList(sql, Capability.class, value1, value2);
  }

  @Override
  public List<Capability> pageBy(String field, Object value, Pager page) {
    String sql = "select * from " + table + " where " + field + " = ? ";
    StatementParameter param = new StatementParameter();
    param.set(value);
    return jdbc.queryForPage(sql, Capability.class, page, param);
  }

  @Override
  public List<Capability> pageByAnd(String field1, Object value1, String field2, Object value2,
      Pager page) {
    String sql = "select * from " + table + " where " + field1 + " = ? and " + field2 + " = ? ";
    StatementParameter param = new StatementParameter();
    param.set(value1);
    param.set(value2);
    return jdbc.queryForPage(sql, Capability.class, page, param);
  }

  @Override
  public List<Capability> pageByOr(String field1, Object value1, String field2, Object value2,
      Pager page) {
    String sql = "select * from " + table + " where " + field1 + " = ? or " + field2 + " = ? ";
    StatementParameter param = new StatementParameter();
    param.set(value1);
    param.set(value2);
    return jdbc.queryForPage(sql, Capability.class, page, param);
  }

  @Override
  public int countBy(String field, Object value) {
    String sql = "select count(*) from " + table + " where " + field + " = ? ";
    return jdbc.queryForInt(sql, value);
  }

  @Override
  public void deleteByTime(String startTime, String endTime) {
    String sql = "delete from " + table + " where dateTime >= ? and dateTime<= ? ";
    jdbc.updateForBoolean(sql, startTime, endTime);
  }

}
