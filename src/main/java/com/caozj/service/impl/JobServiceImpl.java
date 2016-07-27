package com.caozj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.caozj.dao.JobDao;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.Job;
import com.caozj.service.JobService;

/**
 * 任务 Service实现类
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

	@Autowired
	private JobDao jobDao;

	@Override
	public void add(Job job) {
		jobDao.add(job);
	}

	@Override
	public void delete(int id) {
		jobDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Job get(int id) {
		return jobDao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> page(Pager page) {
		return jobDao.page(page);
	}

	@Override
	@Transactional(readOnly = true)
	public int count() {
		return jobDao.count();
	}
	
	@Override
	@Transactional(readOnly = true)
	public int countBy(String field, Object value){
		return jobDao.countBy(field,value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> listAll() {
		return jobDao.listAll();
	}

	@Override
	public void update(Job job) {
		jobDao.update(job);
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Job getBy(String field, Object value) {
		return jobDao.getBy(field, value);
	}

	@Override
	@Transactional(readOnly = true)
	public Job getByAnd(String field1, Object value1, String field2, Object value2) {
		return jobDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public Job getByOr(String field1, Object value1, String field2, Object value2) {
		return jobDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> listBy(String field, Object value) {
		return jobDao.listBy(field, value);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> listByAnd(String field1, Object value1, String field2, Object value2) {
		return jobDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> listByOr(String field1, Object value1, String field2, Object value2) {
		return jobDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> pageBy(String field, Object value, Pager page) {
		return jobDao.pageBy(field, value, page);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return jobDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Job> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return jobDao.pageByOr(field1, value1, field2, value2, page);
	}
	
}
