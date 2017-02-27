package com.dhcc.bussiness.sxydidc.quality;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {

	private static JedisPool pool = null;
	static{
	 pool = new JedisPool(new JedisPoolConfig(),"localhost");
	}
	/**
	 * @return
	 * @see redis.clients.jedis.JedisPool#getResource()
	 */
	public static Jedis getResource() {
		return pool.getResource();
	}
	
}
