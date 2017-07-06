package com.dt.cms.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 与hbase建立连接的类
 * 
 * @author 岳海亮
 * @date 2017年4月28日
 */
//@Component
//@EnableConfigurationProperties(HBaseConfig.class)
public class HBaseConn {
	private Connection conn;

	@Autowired
	HBaseConfig hBaseConfig;

	public HBaseConn(HBaseConfig hBaseConfig) {
		Configuration conf = HBaseConfiguration.create(); // 获得配置文件对象
		conf.set("hbase.zookeeper.quorum", hBaseConfig.getQuorum());
		conf.set("hbase.zookeeper.property.clientPort", hBaseConfig.getClientPort());
		conf.set("hbase.table.sanity.checks", "false");
		try {
			setConn(ConnectionFactory.createConnection(conf));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
