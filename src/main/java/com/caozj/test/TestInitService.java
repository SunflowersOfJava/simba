package com.caozj.test;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caozj.dao.permission.OrgDao;
import com.caozj.model.permission.Org;

/**
 * 测试使用
 * 
 * @author caozj
 *
 */
@Service
public class TestInitService {

	private static final Log logger = LogFactory.getLog(TestInitService.class);

	@Autowired
	private OrgDao orgDao;

	@PostConstruct
	private void init() throws IOException, URISyntaxException {
		Org org = new Org();
		org.setName("org");
		logger.info("================================new org and its id is " + orgDao.add(org));
	}
}
