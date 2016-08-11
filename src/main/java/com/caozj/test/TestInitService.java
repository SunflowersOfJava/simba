package com.caozj.test;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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

  @Autowired
  private JobLauncher jobLauncher;

  @Resource
  private Job testJob;


  @PostConstruct
  private void init() throws IOException, URISyntaxException, JobExecutionAlreadyRunningException,
      JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
    JobExecution result = jobLauncher.run(testJob, new JobParameters());
    // /* 处理结束，控制台打印处理结果 */
    System.out.println("**********************" + result.toString());
  }
}
