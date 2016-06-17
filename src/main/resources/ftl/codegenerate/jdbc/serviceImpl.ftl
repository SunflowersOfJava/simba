package ${packageName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ${packageName}.dao.${className}Dao;
import ${packageName}.framework.util.jdbc.Pager;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
	private ${className}Dao ${firstLower}Dao;

	@Override
	public void add(${className} ${firstLower}) {
		${firstLower}Dao.add(${firstLower});
	}

	@Override
	public void delete(int id) {
		${firstLower}Dao.delete(id);
	}

	@Override
	public ${className} get(int id) {
		return ${firstLower}Dao.get(id);
	}

	@Override
	public List<${className}> page(Pager page) {
		return ${firstLower}Dao.page(page);
	}

	@Override
	public int count() {
		return ${firstLower}Dao.count();
	}
	
	@Override
	public int countBy(String field, Object value){
		return ${firstLower}Dao.countBy(field,value);
	}

	@Override
	public List<${className}> listAll() {
		return ${firstLower}Dao.listAll();
	}

	@Override
	public void update(${className} ${firstLower}) {
		${firstLower}Dao.update(${firstLower});
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
	
	@Override
	public ${className} getBy(String field, Object value) {
		return ${firstLower}Dao.getBy(field, value);
	}

	@Override
	public ${className} getByAnd(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.getByAnd(field1, value1, field2, value2);
	}

	@Override
	public ${className} getByOr(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.getByOr(field1, value1, field2, value2);
	}

	@Override
	public List<${className}> listBy(String field, Object value) {
		return ${firstLower}Dao.listBy(field, value);
	}

	@Override
	public List<${className}> listByAnd(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.listByAnd(field1, value1, field2, value2);
	}

	@Override
	public List<${className}> listByOr(String field1, Object value1, String field2, Object value2) {
		return ${firstLower}Dao.listByOr(field1, value1, field2, value2);
	}

	@Override
	public List<${className}> pageBy(String field, Object value, Pager page) {
		return ${firstLower}Dao.pageBy(field, value, page);
	}

	@Override
	public List<${className}> pageByAnd(String field1, Object value1, String field2, Object value2, Pager page) {
		return ${firstLower}Dao.pageByAnd(field1, value1, field2, value2, page);
	}

	@Override
	public List<${className}> pageByOr(String field1, Object value1, String field2, Object value2, Pager page) {
		return ${firstLower}Dao.pageByOr(field1, value1, field2, value2, page);
	}
	
	<#if pageType=="treeTable">
	@Override
	public List<${className}> listChildren(int parentID) {
		List<${className}> children = ${firstLower}Dao.listBy("parentID", parentID);
		children.forEach((${firstLower}) -> {
			int childrenCount = ${firstLower}Dao.countBy("parentID", ${firstLower}.getId());
			if (childrenCount > 0) {
				${firstLower}.setState("closed");
			} else {
				${firstLower}.setState("open");
			}
		});
		return children;
	}
	</#if> 
}
