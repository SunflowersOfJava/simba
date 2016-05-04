package com.caozj.service.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caozj.annotation.TimeAnnotation;
import com.caozj.framework.util.upload.UploadUtil;
import com.caozj.model.Buss;
import com.caozj.mybatisDao.BussMapper;

/**
 * 初始化类
 * 
 * @author caozj
 * 
 */
@Service
public class InitService {

	private static final Log logger = LogFactory.getLog(InitService.class);

	@Autowired
	private BussMapper bussMapper;

	@PostConstruct
	@TimeAnnotation
	private void init() {
		logger.info("================start to init====================");
		UploadUtil.init();
//		Buss buss = new Buss();
//		buss.setName(System.currentTimeMillis() + "");
//		buss.setDescription("test");
//		buss.setScript("scriptTest");
//		bussMapper.insert(buss);
//		logger.info(bussMapper.listAll().toString());
//		logger.info(bussMapper.listAll().toString());
		logger.info("================end to init====================");
	}
}
