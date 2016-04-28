package com.caozj.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.caozj.annotation.TimeAnnotation;
import com.caozj.framework.util.upload.UploadUtil;

/**
 * 初始化类
 * 
 * @author caozj
 * 
 */
@Service
public class InitService {

	// private static final Log logger = LogFactory.getLog(InitService.class);

	// @Autowired
	// private BussMapper bussMapper;

	@PostConstruct
	@TimeAnnotation
	private void init() {
		UploadUtil.init();
		// Buss buss = new Buss();
		// buss.setName(System.currentTimeMillis() + "");
		// buss.setDescription("test");
		// buss.setScript("scriptTest");
		// bussMapper.insert(buss);
		// logger.info(bussMapper.listAll().toString());
	}
}
