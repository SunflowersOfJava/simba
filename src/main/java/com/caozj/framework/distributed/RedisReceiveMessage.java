package com.caozj.framework.distributed;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.caozj.framework.util.common.SerializeUtil;

import redis.clients.jedis.BinaryJedisPubSub;

/**
 * 订阅Redis的消息
 * 
 * @author caozj
 *
 */
public class RedisReceiveMessage extends BinaryJedisPubSub implements ReceiveMessageInterface {

  private static final Log logger = LogFactory.getLog(RedisReceiveMessage.class);

  @Override
  public void onMessage(byte[] channel, byte[] message) {
    logger.info(
        "接收到" + SerializeUtil.unserialize(channel) + "的消息:" + SerializeUtil.unserialize(message));
  }



}
