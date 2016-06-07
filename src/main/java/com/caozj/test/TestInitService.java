package com.caozj.test;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 测试使用
 * 
 * @author caozj
 *
 */
@Service
public class TestInitService {

	private static final Log logger = LogFactory.getLog(TestInitService.class);

	@PostConstruct
	private void init() throws IOException, URISyntaxException {
	}
}
