package com.caozj.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.caozj.service.JobService;

/**
 * 启动定时任务
 * 
 * @author caozj
 *
 */
@Component
public class StartJob {

  @Autowired
  private JobService jobService;

  @Scheduled(fixedDelay = 60000, fixedRate = 1000)
  private void initJobs() {
    jobService.startAllWaitingJobs();
  }

}
