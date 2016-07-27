package com.caozj.framework.util.schedule;

import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import com.caozj.model.Job;
import com.caozj.model.constant.ConstantData;

/**
 * Spring定时器工具类(单例)
 * 
 * @author caozj
 *
 */
public class ScheduleUtil {

  private static final Log logger = LogFactory.getLog(ScheduleUtil.class);

  private ConcurrentTaskScheduler ct = null;

  private ScheduleUtil() {
    init();
  }

  private void init() {
    ct = new ConcurrentTaskScheduler(
        Executors.newScheduledThreadPool(ConstantData.SCHEDULE_POLL_SIZE));
  }

  private static final class ScheduleUtilHolder {
    private static final ScheduleUtil instance = new ScheduleUtil();
  }

  public static ScheduleUtil getInstance() {
    return ScheduleUtilHolder.instance;
  }



  public void addJob(Job job) throws SchedulerException {

  }
}
