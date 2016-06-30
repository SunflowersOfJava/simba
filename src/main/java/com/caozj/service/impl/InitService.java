package com.caozj.service.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caozj.annotation.TimeAnnotation;
import com.caozj.framework.util.upload.UploadUtil;
import com.caozj.service.RegistryTableService;
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

	@Autowired
	private RegistryTableService registryTableService;

	@PostConstruct
	@TimeAnnotation
	private void init() {
		logger.info("================start to init====================");
		UploadUtil.init();
		userService.checkAndCreateUserExt();
		orgService.checkAndCreateOrgExt();
		registryTableService.init();
		printlnlog();
		logger.info("================end to init====================");
	}

  private void printlnlog() {
    System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    System.out.println("+                 _._                                                 +");
    System.out.println("+            _.-``-.-''-._                                            +");
    System.out.println("+       _.-``     `  `    ''-._                                       +");
    System.out.println("+    .-`` .-```. ```````  ```-.''-._                                  +");
    System.out.println("+  (    '          Web        '     )                                 +");
    System.out.println("+  |`-._`-...-- __...-...__ - ` _.-'|                                 +");
    System.out.println("+  |    `-._                _.-'    |                                 +");
    System.out.println("+   `-._    `-._  caozj  .-'    _.-'                                  +");
    System.out.println("+  |`-._`-._    `-.__.-'    _.-'_.-'|                                 +");
    System.out.println("+  |    `-._`-._        _.-'_.-'    |                                 +");
    System.out.println("+   `-._    `-._`-.__.-'_.-'    _.-'                                  +");
    System.out.println("+       `-._    `-.__.-'    _.-'                                      +");
    System.out.println("+           `-._        _.-'                                          +");
    System.out.println("+               `-.__.-'        loceme.student@163.com                +");
    System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
  }
}
