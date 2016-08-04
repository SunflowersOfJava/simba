package com.caozj.test;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private JobLauncher jobLauncher;

  @Resource
  private Job helloWorldJob;


  @PostConstruct
  private void init() throws IOException, URISyntaxException, JobExecutionAlreadyRunningException,
      JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
    JobExecution result = jobLauncher.run(helloWorldJob, new JobParameters());
    /* 处理结束，控制台打印处理结果 */
    System.out.println("**********************" + result.toString());
  }
}
