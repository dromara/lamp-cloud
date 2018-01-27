package com.github.zuihou.file.support;

import org.csource.common.NameValuePair;

/**
 * @author ShenHuaJie
 * @version 2016年6月27日 上午9:36:30
 */
public class FastDfsFile {
	private String groupName;
	private String fileName;
	private NameValuePair[] nameValuePairs;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public NameValuePair[] getNameValuePairs() {
		return nameValuePairs;
	}

	public void setNameValuePairs(NameValuePair[] nameValuePairs) {
		this.nameValuePairs = nameValuePairs;
	}
}
