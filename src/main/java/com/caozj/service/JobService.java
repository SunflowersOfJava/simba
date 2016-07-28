package com.caozj.service;

import java.text.ParseException;
import java.util.List;

import org.quartz.SchedulerException;

import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.Job;

/**
 * 任务 Service
 * 
 * @author caozj
 * 
 */
public interface JobService {

  /**
   * 执行
   * 
   * @param job
   */
  void execute(Job job);

  /**
   * 把所有可以启动的待启动任务启动
   */
  void startAllJobs();

  /**
   * 初始化要执行的任务对象数据
   */
  void initJobData();

  /**
   * 启动所有可以启动的任务
   */
  void initStartJobs();

  void add(Job job) throws SchedulerException, ParseException;

  void update(Job job) throws SchedulerException, ParseException;

  void delete(int id);

  List<Job> listAll();

  int count();

  int countBy(String field, Object value);

  List<Job> page(Pager page);

  Job get(int id);

  void batchDelete(List<Integer> idList);

  Job getBy(String field, Object value);

  Job getByAnd(String field1, Object value1, String field2, Object value2);

  Job getByOr(String field1, Object value1, String field2, Object value2);

  List<Job> listBy(String field, Object value);

  List<Job> listByAnd(String field1, Object value1, String field2, Object value2);

  List<Job> listByOr(String field1, Object value1, String field2, Object value2);

  List<Job> pageBy(String field, Object value, Pager page);

  List<Job> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page);

  List<Job> pageByOr(String field1, Object value1, String field2, Object value2, Pager page);
}
