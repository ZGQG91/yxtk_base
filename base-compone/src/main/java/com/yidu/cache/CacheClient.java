package com.yidu.cache;

public interface CacheClient {
	/**
	 * 存储缓存自定义对象
	 * 
	 * @param key
	 * @param object
	 */
	public void putObject(String key, Object object, long expire);

	public void set(final String key, final String str, final long exprie);
	
	public boolean setNX(final String key, final String str, final long exprie);
	/**
	 * 获取缓存中存储的自定义对象
	 * 
	 * @param key
	 */public <T> T getObject(String key);

	
	/**
	 * 获取缓存中的字符串
	 * @param key
	 * @return
	 */
	public <T>T getString(String key);

	/**
	 * 移除缓存中存储的对象
	 * 
	 * @param key
	 */
	public Long remove(String key);

	/**
	 * @param key
	 * @return
	 */
	public Long incr(String key);

	public Long decr(String key);
}
