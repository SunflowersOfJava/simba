package com.caozj.test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarNotImplementedException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.Who;

import com.caozj.framework.util.common.ServerUtil;
import com.caozj.framework.util.common.SystemUtil;
import com.caozj.framework.util.common.ThreadUtil;

public class TSigar {

  public static void main(String[] args) throws IOException, SigarException {
    String sigarFile = ServerUtil.getWebRoot() + "/sigar";
    String path = SystemUtil.getSystemEnvPath();
    System.out.println(path);
    // 为防止java.library.path重复加，此处判断了一下
    if (!path.contains(sigarFile)) {
      if (SystemUtil.isWindowsOs()) {
        path += ";" + sigarFile;
      } else {
        path += ":" + sigarFile;
      }
      System.setProperty("java.library.path", path);
    }
    System.out.println(SystemUtil.getSystemEnvPath());
    System.out.println("=========操作系统========");
    OperatingSystem OS = OperatingSystem.getInstance();
    // 操作系统内核类型如： 386、486、586等x86
    System.out.println("操作系统内核 = " + OS.getArch());
    System.out.println("OS.getCpuEndian() = " + OS.getCpuEndian());//
    System.out.println("OS.getDataModel() = " + OS.getDataModel());//
    // 系统描述
    System.out.println("系统描述 = " + OS.getDescription());

    // 操作系统类型
    System.out.println("系统类型 = " + OS.getName());
    // 操作系统的卖主
    System.out.println("系统卖主 = " + OS.getVendor());
    // 卖主名称
    System.out.println("卖主名称 = " + OS.getVendorCodeName());
    // 操作系统名称
    System.out.println("系统名称 = " + OS.getVendorName());
    // 操作系统卖主类型
    System.out.println("系统类型 = " + OS.getVendorVersion());
    // 操作系统的版本号
    System.out.println("版本号 = " + OS.getVersion());

    System.out.println("==============cpu===================");
    Sigar sigar = new Sigar();
    CpuInfo infos[] = sigar.getCpuInfoList();
    CpuPerc cpu = sigar.getCpuPerc();
    String address = InetAddress.getLocalHost().getHostAddress();
    System.out.println("本机IP地址" + address);
    for (int i = 0; i < infos.length; i++) {
      CpuInfo info = infos[i];
      // CPU的总量MHz
      System.out.println("CPU总量mhz=" + info.getMhz());
      // 获得CPU的卖主，如：Intel
      System.out.println("vendor=" + info.getVendor());
      // 获得CPU的类别，如：Celeron
      System.out.println("model=" + info.getModel());
      // 缓冲存储器数量
      System.out.println("cache size=" + info.getCacheSize());
      // 用户使用率
      System.out.println("User :" + CpuPerc.format(cpu.getUser()));
      // 系统使用率
      System.out.println("Sys :" + CpuPerc.format(cpu.getSys()));
    }
    System.out.println("============cpu信息============");
    for (int i = 0; i < 10; i++) {
      cpu = sigar.getCpuPerc();
      double used = cpu.getSys() + cpu.getUser();
      System.out.println("============cpu使用============" + i + "," + used);
      ThreadUtil.sleep(1000);
    }

    // 物理内存信息
    Mem mem = sigar.getMem();
    // 内存总量
    System.out.println("Total = " + mem.getTotal() / 1024L / 1024 + "M av");
    // 当前内存使用量
    System.out.println("Used = " + mem.getUsed() / 1024L / 1024 + "M used");
    // 当前内存剩余量
    System.out.println("Free = " + mem.getFree() / 1024L / 1024 + "M free");

    System.out.println("============memory信息============");
    for (int i = 0; i < 10; i++) {
      mem = sigar.getMem();
      double used = mem.getUsedPercent();
      System.out.println("============memory使用============" + i + "," + used);
      ThreadUtil.sleep(1000);
    }

    // 系统页面文件交换区信息
    Swap swap = sigar.getSwap();
    // 交换区总量
    System.out.println("Total = " + swap.getTotal() / 1024L + "K av");
    // 当前交换区使用量
    System.out.println("Used = " + swap.getUsed() / 1024L + "K used");
    // 当前交换区剩余量
    System.out.println("Free = " + swap.getFree() / 1024L + "K free");

    FileSystem fslist[] = sigar.getFileSystemList();
    System.out.println("长度为什么是:" + fslist.length);

    for (int i = 0; i < fslist.length - 2; i++) {
      System.out.println("============硬盘描述============");
      System.out.println("\n~~~~~~~~~~" + i + "~~~~~~~~~~");
      FileSystem fs = fslist[i];
      // 分区的盘符名称
      System.out.println("fs.getDevName() = " + fs.getDevName());
      // 分区的盘符名称
      // System.out.println("fs.getDirName() = " + fs.getDirName());

      // 文件系统类型，比如 FAT32、NTFS
      System.out.println("fs.getSysTypeName() = " + fs.getSysTypeName());
      // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
      System.out.println("fs.getTypeName() = " + fs.getTypeName());
      // 文件系统类型

      FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
      String sub = fs.getDevName().substring(0, 1);
      // 文件系统总大小
      System.out.println(" 硬盘 " + sub + "=" + usage.getTotal() / 1024 / 1024 + "GB");

      // 文件系统剩余大小
      System.out.println(" 硬盘剩余大小= " + usage.getFree() / 1024 / 1024 + "GB");
      // 文件系统可用大小
      System.out.println(" 硬盘可用大小 = " + usage.getAvail() / 1024 / 1024 + "GB");
      // 文件系统已经使用量
      System.out.println(" 经使用量 = " + usage.getUsed() / 1024 / 1024 + "GB");
      double usePercent = usage.getUsePercent() * 100D;
      // 文件系统资源的利用率
      System.out.println(" 利用率 = " + usePercent + "%");

      System.out.println(" DiskReads = " + usage.getDiskReads());
      System.out.println(" DiskWrites = " + usage.getDiskWrites());
    }

    // 获取当前系统桌面扩展
    java.awt.Desktop dp = java.awt.Desktop.getDesktop();
    // 获得系统属性集
    Properties props = System.getProperties();
    // 操作系统名称
    String osName = props.getProperty("os.name");
    System.out.println("操作系统名称:" + osName);
    // 操作系统构架
    String osArch = props.getProperty("os.arch");
    System.out.println("系统架构:" + osArch);
    // 操作系统版本
    String osVersion = props.getProperty("os.version");
    System.out.println("操作系统版本:" + osVersion);
    // Java安装目录
    String home = props.getProperty("java.home");
    System.out.println("java安装目录:" + home);
    // 用户的账户名称
    String user = props.getProperty("user.home");
    System.out.println("用户帐户名称:" + user);
    // Java 运行时环境规范名称
    String version = props.getProperty("java.specification.name");
    System.out.println("Java 运行时环境规范名称 :" + version);
    // 获取当前jdk的版本号
    String number = props.getProperty("java.specification.version");
    System.out.println("jdk的版本号:" + number);

    // 判断系统桌面是否支持要执行的功能
    if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
      // 获取系统默认浏览器打开链接
    }

    // 取当前系统进程表中的用户信息
    Who who[] = sigar.getWhoList();
    if (who != null && who.length > 0) {
      for (int i = 0; i < who.length; i++) {
        System.out.println("\n~~~~~~~~~" + String.valueOf(i) + "~~~~~~~~~");
        Who _who = who[i];
        System.out.println("getDevice() = " + _who.getDevice());
        System.out.println("getHost() = " + _who.getHost());
        System.out.println("getTime() = " + _who.getTime());
        // 当前系统进程表中的用户名
        System.out.println("getUser() = " + _who.getUser());
      }
    }

    System.out.println(SystemUtil.getServerDomain());

    // 取到当前机器的MAC地址
    String[] ifaces = sigar.getNetInterfaceList();
    String hwaddr = null;
    for (int i = 0; i < ifaces.length; i++) {
      NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(ifaces[i]);
      if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
          || (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
          || NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
        continue;
      }
      hwaddr = cfg.getHwaddr();
      System.out.println(hwaddr);
    }

    // 获取网络流量等信息
    String ifNames[] = sigar.getNetInterfaceList();
    for (int i = 0; i < ifNames.length; i++) {
      String name = ifNames[i];
      NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
      System.out.println("\nname = " + name);// 网络设备名
      System.out.println("Address = " + ifconfig.getAddress());// IP地址
      System.out.println("Netmask = " + ifconfig.getNetmask());// 子网掩码
      if ((ifconfig.getFlags() & 1L) <= 0L) {
        System.out.println("!IFF_UP...skipping getNetInterfaceStat");
        continue;
      }
      try {
        NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
        System.out.println("RxPackets = " + ifstat.getRxPackets());// 接收的总包裹数
        System.out.println("TxPackets = " + ifstat.getTxPackets());// 发送的总包裹数
        System.out.println("RxBytes = " + ifstat.getRxBytes());// 接收到的总字节数
        System.out.println("TxBytes = " + ifstat.getTxBytes());// 发送的总字节数
        System.out.println("RxErrors = " + ifstat.getRxErrors());// 接收到的错误包数
        System.out.println("TxErrors = " + ifstat.getTxErrors());// 发送数据包时的错误数
        System.out.println("RxDropped = " + ifstat.getRxDropped());// 接收时丢弃的包数
        System.out.println("TxDropped = " + ifstat.getTxDropped());// 发送时丢弃的包数
      } catch (SigarNotImplementedException e) {} catch (SigarException e) {
        System.out.println(e.getMessage());
      }
    }


  }
}
