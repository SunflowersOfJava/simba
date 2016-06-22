package com.caozj.framework.distributed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 分布式工具类
 * 
 * @author caozj
 *
 */
@Component
public class DistributedUtil {

  @Autowired
  private DistributedInterface di;
  

}
