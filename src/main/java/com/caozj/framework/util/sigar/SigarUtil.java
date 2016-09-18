package com.caozj.framework.util.sigar;

import org.hyperic.sigar.Sigar;

import com.caozj.framework.util.common.ServerUtil;
import com.caozj.framework.util.common.SystemUtil;

/**
 * 利用sigar获取系统性能相关信息工具类(memory,cpu,disk,io network等)
 * 
 * @author caozj
 *
 */
public class SigarUtil {

  int cpuCoreCount = 0;

  Sigar sigar = null;

  private SigarUtil() {
    init();
  }

  /**
   * 初始化
   */
  private void init() {
    // 处理java.library.path路径，保证sigar能够找到对应操作系统的库文件
    String sigarFile = ServerUtil.getWebRoot() + "/sigar";
    String path = SystemUtil.getSystemEnvPath();
    // 为防止java.library.path重复加，此处判断了一下
    if (!path.contains(sigarFile)) {
      if (SystemUtil.isWindowsOs()) {
        path += ";" + sigarFile;
      } else {
        path += ":" + sigarFile;
      }
      System.setProperty("java.library.path", path);
    }

    // 初始化sigar对象
    sigar = new Sigar();
  }

  private static final class SigarUtilHolder {
    private static SigarUtil instance = new SigarUtil();
  }

  public static SigarUtil getInstance() {
    return SigarUtilHolder.instance;
  }

}
