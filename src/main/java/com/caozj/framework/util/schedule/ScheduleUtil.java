package com.caozj.framework.util.schedule;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

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

  private Map<Integer, ScheduledFuture<?>> SCHEDULED_FUTURE = null;

  private ScheduleUtil() {
    init();
  }

  private void init() {
    ct = new ConcurrentTaskScheduler(
        Executors.newScheduledThreadPool(ConstantData.SCHEDULE_POLL_SIZE));
    SCHEDULED_FUTURE = new HashMap<Integer, ScheduledFuture<?>>();
  }

  private static final class ScheduleUtilHolder {
    private static final ScheduleUtil instance = new ScheduleUtil();
  }

  public static ScheduleUtil getInstance() {
    return ScheduleUtilHolder.instance;
  }



  /**
   * 新增任务
   * 
   * @param job 任务对象
   * @throws SchedulerException
   */
  public void addJob(Job job) throws SchedulerException {

    ScheduledFuture<?> scheduledFuture = ct.schedule(() -> {

    }, new CronTrigger(job.getCronExpression()));
    SCHEDULED_FUTURE.put(job.getId(), scheduledFuture);
  }


}
