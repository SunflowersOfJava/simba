package com.caozj.mybatisDao;

import java.util.List;

import com.caozj.buss.model.Buss;

public interface BussMapper {

	void insert(Buss buss);

	List<Buss> listAll();
}
