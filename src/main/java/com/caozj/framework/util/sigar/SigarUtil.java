package com.caozj.framework.util.sigar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;

import com.caozj.framework.util.common.ServerUtil;
import com.caozj.framework.util.common.SystemUtil;

/**
 * 利用sigar获取系统性能相关信息工具类(memory,cpu,disk,io network等)
 * 
 * @author caozj
 *
 */
public class SigarUtil {

  int cpuCount = 0;

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

  /**
   * 获取cpu核数
   * 
   * @return
   * @throws SigarException
   */
  public int getCpuCount() throws SigarException {
    if (cpuCount > 0) {
      return cpuCount;
    }
    cpuCount = getCpuInfo().length;
    return cpuCount;
  }

  /**
   * 获取cpu使用率
   * 
   * @return
   * @throws SigarException
   */
  public double getCpuUsedPercent() throws SigarException {
    CpuPerc cpu = sigar.getCpuPerc();
    return (cpu.getUser() + cpu.getSys()) * 100;
  }

  /**
   * 获取cpu信息
   * 
   * @return
   * @throws SigarException
   */
  public CpuInfo[] getCpuInfo() throws SigarException {
    return sigar.getCpuInfoList();
  }

  /**
   * 获取内存使用信息
   * 
   * @return
   * @throws SigarException
   */
  public Mem getMemory() throws SigarException {
    return sigar.getMem();
  }

  /**
   * 获取内存使用率
   * 
   * @return
   * @throws SigarException
   */
  public double getMemoryUsedPercent() throws SigarException {
    return getMemory().getUsedPercent();
  }

  /**
   * 系统页面文件交换区信息
   * 
   * @return
   * @throws SigarException
   */
  public Swap getSwap() throws SigarException {
    return sigar.getSwap();
  }

  /**
   * 获取所有磁盘信息
   * 
   * @return
   * @throws SigarException
   */
  public FileSystem[] getFileSystem() throws SigarException {
    return sigar.getFileSystemList();
  }

  /**
   * 获取本地磁盘信息
   * 
   * @return
   * @throws SigarException
   */
  public List<FileSystem> getAllLocalFileSystem() throws SigarException {
    FileSystem[] fsl = getFileSystem();
    List<FileSystem> list = new ArrayList<>(fsl.length);
    for (FileSystem fs : fsl) {
      if ("local".equals(fs.getTypeName())) {
        list.add(fs);
      }
    }
    return list;
  }

  /**
   * 获取磁盘使用率
   * 
   * @return
   * @throws SigarException
   */
  public Map<String, Double> getFileSystemUsedPercent() throws SigarException {
    List<FileSystem> fsList = getAllLocalFileSystem();
    Map<String, Double> usedPercentMap = new HashMap<>(fsList.size());
    for (FileSystem fs : fsList) {
      String fsName = fs.getDirName();
      FileSystemUsage usage = sigar.getFileSystemUsage(fsName);
      double usedPercent = usage.getUsePercent() * 100D;
      usedPercentMap.put(fsName, usedPercent);
    }
    return usedPercentMap;
  }

  /**
   * 获取当前系统进程表中的用户信息
   * 
   * @return
   * @throws SigarException
   */
  public List<String> getProcessUsers() throws SigarException {
    Who[] whoList = sigar.getWhoList();
    List<String> users = new ArrayList<>(whoList.length);
    for (Who who : whoList) {
      users.add(who.getUser());
    }
    return users;
  }

  /**
   * 获取当前机器的MAC地址
   * 
   * @return
   * @throws SigarException
   */
  public Set<String> getMacAddress() throws SigarException {
    String[] ifaces = sigar.getNetInterfaceList();
    Set<String> macSet = new HashSet<>(ifaces.length);
    for (String iface : ifaces) {
      NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(iface);
      if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
          || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
          || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
        continue;
      }
      macSet.add(cfg.getHwaddr());
    }
    return macSet;
  }

  /**
   * 获取网络IO数据信息
   * 
   * @return [receive:接收字节数,send:发送字节数]
   * @throws SigarException
   */
  public Map<String, Double> getNetworkIO() throws SigarException {
    Map<String, Double> ioMap = new HashMap<>(2);
    double receive = 0;
    double send = 0;
    String[] ifNames = sigar.getNetInterfaceList();
    for (String name : ifNames) {
      NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
      if ((ifconfig.getFlags() & 1L) <= 0L) {
        continue;
      }
      NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
      receive += ifstat.getRxBytes();
      send += ifstat.getTxBytes();
    }
    ioMap.put("receive", receive);
    ioMap.put("send", send);
    return ioMap;
  }

}
