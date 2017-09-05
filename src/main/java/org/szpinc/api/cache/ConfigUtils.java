package org.szpinc.api.cache;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigUtils {
	
	    private static Log log = LogFactory.getLog(ConfigUtils.class);

	    public static boolean redisSwitch ;
	    public static int maxIdle ;
	    public static boolean testOnBorrow;
	    public static boolean testOnReturn;
	    public static String ip;
	    public static int port;
	    public static String key;

	    public static int fail_count = 0 ;

	    static {
	        Properties props = new Properties();
	        try {
	            props.load(JedisUtils.class.getResourceAsStream("/properties/redis.properties"));

	            redisSwitch = Boolean.valueOf(props.getProperty("redis.switch"));
	            maxIdle = Integer.valueOf(props.getProperty("jedis.pool.maxIdle"));
	            testOnBorrow = Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow"));
	            testOnReturn = Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn"));
	            ip = props.getProperty("redis.ip") ;
	            port = Integer.valueOf(props.getProperty("redis.port"));
	            key = props.getProperty("redis.key") ;
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	    }

	    public static void setSwitch(boolean redisSwitch){
	        if(true == ConfigUtils.redisSwitch && false == redisSwitch){
	            //switch : open --> close
	        	log.info("switch : open --> close");
	            JedisUtils.closeJedisPool();
	        }else if(false == ConfigUtils.redisSwitch && true == redisSwitch){
	            //switch : close --> open
	        	log.info("switch : close --> open");
	            JedisUtils.getInstance();
	        }

	        ConfigUtils.redisSwitch = redisSwitch ;
	    }

	    //TODO:当redis连接异常超过一定数量之后， 不再走redis,但是没有一个机制，当redis恢复之后会重新使用redis
	    public static void setFailCount(){
	        if(redisSwitch){
	            fail_count += 1;

	            //TODO:失败数量配置到配置文件中
	            if(fail_count > 10){
	            	log.info(" setSwitch(false) ");
	                setSwitch(false);
	            }
	        }
	    }

	    public static void setSucc(){
	        if(fail_count > 0 ){
	            fail_count = 0 ;
	        }

	        if(!redisSwitch){
	            setSwitch(true);
	        }
	    }
	
}
