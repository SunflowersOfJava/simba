package com.caozj.service.impl.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caozj.dao.permission.OrgDao;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.model.permission.Org;
import com.caozj.service.permission.OrgService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgDao orgDao;

	@Override
	public void add(Org org) {
		orgDao.add(org);
	}

	@Override
	public void delete(int id) {
		orgDao.delete(id);
	}

	@Override
	public Org get(int id) {
		return orgDao.get(id);
	}

	@Override
	public List<Org> page(Pager page) {
		return orgDao.page(page);
	}

	@Override
	public int count() {
		return orgDao.count();
	}

	@Override
	public List<Org> listAll() {
		return orgDao.listAll();
	}

	@Override
	public void update(Org org) {
		orgDao.update(org);
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
	
	@Override
	public Org getBy(String field, Object value) {
		return orgDao.getBy(field, value);
	}

	@Override
	public Org getByAnd(String field1, Object value1, String field2, Object value2) {
		return orgDao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	public Org getByOr(String field1, Object value1, String field2, Object value2) {
		return orgDao.getByOr(field1, value1, field2, value2);
	}

	@Override
	public List<Org> listBy(String field, Object value) {
		return orgDao.listBy(field, value);
	}

	@Override
	public List<Org> listByAnd(String field1, Object value1, String field2, Object value2) {
		return orgDao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	public List<Org> listByOr(String field1, Object value1, String field2, Object value2) {
		return orgDao.listByOr(field1, value1, field2, value2);
	}

	@Override
	public List<Org> pageBy(String field, Object value, Pager page) {
		return orgDao.pageBy(field, value, page);
	}

	@Override
	public List<Org> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return orgDao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	public List<Org> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return orgDao.pageByOr(field1, value1, field2, value2, page);
	}
}
