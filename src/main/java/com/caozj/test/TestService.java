package com.caozj.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	private static final Log logger = LogFactory.getLog(TestController.class);

	@Cacheable(value = "${cache.type}", key = "#info")
	public String testCache(String info) {
		logger.info("receive==>" + info);
		return "test:" + info;
	}

	@CacheEvict(value = "${cache.type}", allEntries = true)
	public void clearCache() {

	}

	@CacheEvict(value = "${cache.type}", key = "#info")
	public void clear(String info) {

	}

}
