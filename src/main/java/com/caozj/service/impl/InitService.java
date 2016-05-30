package com.caozj.service.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caozj.annotation.TimeAnnotation;
import com.caozj.framework.util.upload.UploadUtil;
import com.caozj.service.permission.OrgService;
import com.caozj.service.permission.UserService;

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
	private UserService userService;

	@Autowired
	private OrgService orgService;

	@PostConstruct
	@TimeAnnotation
	private void init() {
		logger.info("================start to init====================");
		UploadUtil.init();
		userService.checkAndCreateUserExt();
		orgService.checkAndCreateOrgExt();
		logger.info("================end to init====================");
	}
}
