package org.szpinc.api.cache;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.szpinc.api.utils.SerializeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtils {

	private static Log log = LogFactory.getLog(ConfigUtils.class);

	private static JedisPool JEDISPOOL;

	public static void getInstance() {
		if (JEDISPOOL == null) {
			log.info(" JedisUtils getInstance ");
			Properties props = new Properties();
			try {
				// TODO:配置文件固定，换成使用spring加载的方式
				JedisPoolConfig conf = new JedisPoolConfig();
				conf.setMaxIdle(ConfigUtils.maxIdle);
				conf.setTestOnBorrow(ConfigUtils.testOnBorrow);
				conf.setTestOnReturn(ConfigUtils.testOnReturn);
				JEDISPOOL = new JedisPool(conf, ConfigUtils.ip, ConfigUtils.port);
			} catch (Exception e) {
				log.error("加载[jedis.properties]异常[" + e.getMessage() + "]", e);
			}
		}
	}

	public static Jedis getJedis() {
		try {
			return JEDISPOOL.getResource();
		} catch (Exception e) {
			// throw new JedisConnectionException("Could not get a resource from the pool");
			return null;
		}
	}

	public static void recycleJedis(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	public static void closeJedisPool() {
		if (JEDISPOOL != null) {
			JEDISPOOL.close();
		}
	}

	/**
	 * Redis存储Object序列化流
	 */
	public static void put(String id, Object key, Object value) {
		Jedis jedis = getJedis();
		try {
			log.info(" redis put ... key = [" + key + "]");
			// jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(value));
			jedis.hset(id.toString().getBytes(), key.toString().getBytes(), SerializeUtils.serialize(value));
			// TODO:考虑用切片处理
			ConfigUtils.setSucc();
		} catch (Exception e) {
			// TODO:异常放在utils中捕捉，还是在NTSRedisCache捕捉
			// TODO:统一异常处理方法
			ConfigUtils.setFailCount();
			log.error("Redis执行异常[" + e.getMessage() + "]", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	public static <T> T get(String id, Object key) {
		Jedis jedis = getJedis();
		try {
			// T value =
			// SerializeUtils.unserialize(jedis.get(SerializeUtils.serialize(key)));
			T value = SerializeUtils.unserialize(jedis.hget(id.toString().getBytes(), key.toString().getBytes()));
			log.info(" redis get ... key = [" + key + "] , value = [" + value + "]");

			ConfigUtils.setSucc();
			return value;
		} catch (Exception e) {
			ConfigUtils.setFailCount();
			log.error("Redis执行异常[" + e.getMessage() + "]", e);
		} finally {
			recycleJedis(jedis);
		}
		return null;
	}

	public static Long remove(String id, Object key) {
		Jedis jedis = getJedis();
		try {
			Long num = jedis.hdel(id.toString(), key.toString());
			;
			// Long num = jedis.del(SerializeUtils.serialize(key));
			ConfigUtils.setSucc();
			return num;
		} catch (Exception e) {
			ConfigUtils.setFailCount();
			log.error("Redis执行异常[" + e.getMessage() + "]", e);
		} finally {
			recycleJedis(jedis);
		}
		return 0L;
	}

	public static void removeAll(String id) {
		Jedis jedis = getJedis();
		try {
			jedis.del(id.toString());
			// jedis.flushDB();
			ConfigUtils.setSucc();
		} catch (Exception e) {
			ConfigUtils.setFailCount();
			log.error("Redis执行异常[" + e.getMessage() + "]", e);
		} finally {
			recycleJedis(jedis);
		}
	}

	public static int getSize(String id) {
		return 0;
		/*
		 * Jedis jedis = getJedis(); try{ Map<byte[], byte[]> result =
		 * jedis.hgetAll(id.toString().getBytes()); return result.size();
		 * }catch(Exception e){ ConfigUtils.setFailCount(); log.error("Redis执行异常[" +
		 * e.getMessage() + "]" , e); }finally { recycleJedis(jedis); } return -1 ;
		 */
	}

}
