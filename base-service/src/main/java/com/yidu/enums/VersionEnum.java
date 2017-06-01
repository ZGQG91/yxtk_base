package com.yidu.enums;

import org.apache.commons.lang.StringUtils;

public enum VersionEnum {

	V1("1.0.0.0", null), V1_1("1.1.0.0", V1),
	/** 当前版本 */
	CURRENT_VERSION(V1_1),;

	private VersionEnum(String version, VersionEnum parent) {
		this.version = version;
		this.parent = parent;
	}

	private VersionEnum(VersionEnum version) {
		this.version = version.version;
		this.parent = version.parent;
	}

	/** 版本号 */
	private String version;
	/** 前一个版本 */
	private VersionEnum parent;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public VersionEnum getParent() {
		return parent;
	}

	public void setParent(VersionEnum parent) {
		this.parent = parent;
	}

	/**
	 * 获取指定字符串对应的版本对象
	 * 
	 * @param version
	 * @return
	 */
	public static VersionEnum getVersion(String version) {
		if (StringUtils.isBlank(version))
			return null;
		for (VersionEnum ver : values()) {
			if(version.equals(ver.version)){
				return ver;
			}
		}
		return null;
	}
}
