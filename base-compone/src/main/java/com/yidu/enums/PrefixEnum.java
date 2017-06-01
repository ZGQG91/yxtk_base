package com.yidu.enums;

import com.yidu.cache.CacheInterceptor;

public enum PrefixEnum {

	/** 用户权限 */
	USER_PERMISSION("user", "permission")
	;
	/** 缓存前缀所属域 */
	private String domain;

	/** 缓存前缀值 */
	private String value;

	/**
	 * constructor
	 *
	 * @param domain
	 * @param value
	 */
	PrefixEnum(String domain, String value) {
		this.domain = domain;
		this.value = value;
	}

	/**
	 * getter
	 *
	 * @return
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * getter
	 *
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 获得完整前缀
	 *
	 * @return
	 */
	public String getPrefix() {
		return domain + CacheInterceptor.KEY_SEPERATOR + value;
	}
}
