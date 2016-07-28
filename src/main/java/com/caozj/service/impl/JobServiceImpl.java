package com.caozj.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caozj.cluster.job.JobClusterData;
import com.caozj.cluster.job.JobClusterExecute;
import com.caozj.dao.JobDao;
import com.caozj.framework.distributed.ClusterMessage;
import com.caozj.framework.distributed.DistributedUtil;
import com.caozj.framework.util.common.ReflectUtil;
import com.caozj.framework.util.jdbc.Pager;
import com.caozj.framework.util.schedule.ScheduleUtil;
import com.caozj.model.Job;
import com.caozj.model.constant.JobData;
import com.caozj.model.enums.JobStatus;
import com.caozj.service.JobService;

/**
 * 任务 Service实现类
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

  private static final Log logger = LogFactory.getLog(JobServiceImpl.class);

  @Autowired
  private JobDao jobDao;

  @Value("${session.isEnableDistributedSession}")
  private String distributedEnable;

  @Autowired
  private DistributedUtil distributedUtil;

  @Override
  public void add(Job job) throws SchedulerException, ParseException {
    job.setStatus(JobStatus.WAITING.getName());
    jobDao.add(job);
    executeCluster(new JobClusterData(job, "add"));
  }

  @Override
  public void delete(int id) {
    jobDao.delete(id);
    ScheduleUtil.getInstance().deleteJob(id);
    executeCluster(new JobClusterData(id, "remove"));
  }

  @Override
  @Transactional(readOnly = true)
  public Job get(int id) {
    return jobDao.get(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> page(Pager page) {
    return jobDao.page(page);
  }

  @Override
  @Transactional(readOnly = true)
  public int count() {
    return jobDao.count();
  }

  @Override
  @Transactional(readOnly = true)
  public int countBy(String field, Object value) {
    return jobDao.countBy(field, value);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> listAll() {
    return jobDao.listAll();
  }

  @Override
  public void update(Job job) throws SchedulerException, ParseException {
    jobDao.update(job);
    ScheduleUtil.getInstance().deleteJob(job);
    ScheduleUtil.getInstance().addJob(job);
  }

  @Override
  public void batchDelete(List<Integer> idList) {
    for (Integer id : idList) {
      this.delete(id);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Job getBy(String field, Object value) {
    return jobDao.getBy(field, value);
  }

  @Override
  @Transactional(readOnly = true)
  public Job getByAnd(String field1, Object value1, String field2, Object value2) {
    return jobDao.getByAnd(field1, value1, field2, value2);
  }

  @Override
  @Transactional(readOnly = true)
  public Job getByOr(String field1, Object value1, String field2, Object value2) {
    return jobDao.getByOr(field1, value1, field2, value2);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> listBy(String field, Object value) {
    return jobDao.listBy(field, value);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> listByAnd(String field1, Object value1, String field2, Object value2) {
    return jobDao.listByAnd(field1, value1, field2, value2);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> listByOr(String field1, Object value1, String field2, Object value2) {
    return jobDao.listByOr(field1, value1, field2, value2);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> pageBy(String field, Object value, Pager page) {
    return jobDao.pageBy(field, value, page);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> pageByAnd(String field1, Object value1, String field2, Object value2,
      Pager page) {
    return jobDao.pageByAnd(field1, value1, field2, value2, page);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Job> pageByOr(String field1, Object value1, String field2, Object value2,
      Pager page) {
    return jobDao.pageByOr(field1, value1, field2, value2, page);
  }

  @Override
  public void execute(Job job) {
    try {
      ReflectUtil.invokeObjectMethod(job.getClassName(), job.getMethodName());
    } catch (Exception e) {}
  }

  @Override
  public void startAllJobs() {
    List<Job> allJobs = JobData.getInstance().getAll();
    allJobs.forEach((job) -> {
      try {
        ScheduleUtil.getInstance().addJob(job);
      } catch (Exception e) {
        logger.error("启动任务异常:" + job.toString(), e);
      }
    });
  }

  private void executeCluster(JobClusterData clustData) {
    if (!"true".equalsIgnoreCase(distributedEnable)) {
      if (clustData.getMethod().equals("add")) {
        JobData.getInstance().add(clustData.getJob());
      } else if (clustData.getMethod().equals("remove")) {
        JobData.getInstance().remove(clustData.getJob());
      }
    } else {
      distributedUtil.executeInCluster(
          new ClusterMessage(JobClusterExecute.class.getCanonicalName(), clustData));
    }
  }

  @Override
  public void initJobData() {
    List<Job> list = this.listBy("status", JobStatus.WAITING.getName());
    JobData.getInstance().add(list);
  }

  @Override
  public void initStartJobs() {
    List<Job> allJobs = this.listAll();
    allJobs.forEach((job) -> {
      try {
        ScheduleUtil.getInstance().addJob(job);
      } catch (Exception e) {
        logger.error("启动任务异常:" + job.toString(), e);
      }
    });
  }

}
