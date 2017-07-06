package com.dt.cms.hbase;

/**
 * hbase配置信息填充
 * @author 岳海亮
 * @date 2017年4月28日
 */
//@ConfigurationProperties(prefix = "hbase.zookeeper.property")
public class HBaseConfig {

	private String quorum;
	private String clientPort;

	public String getQuorum() {
		return quorum;
	}

	public void setQuorum(String quorum) {
		this.quorum = quorum;
	}

	public String getClientPort() {
		return clientPort;
	}

	public void setClientPort(String clientPort) {
		this.clientPort = clientPort;
	}

	

}
