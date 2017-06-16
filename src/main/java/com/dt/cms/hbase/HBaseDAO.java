package com.dt.cms.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;

/**
 * hbase操作类
 * 
 * @author 岳海亮
 * @date 2017年4月28日
 */
@Repository
public class HBaseDAO {

	private static final Logger logger = LoggerFactory.getLogger(HBaseDAO.class);

	@Autowired
	HBaseConn hbaseConn;

	/**
	 * 创建表
	 * 
	 * @param tableName
	 * @param families
	 * @throws IOException
	 */
	public void createTable(String tableName, String... families) throws IOException {
		TableName tn = TableName.valueOf(tableName); // 创建表名对象
		Admin admin = hbaseConn.getConn().getAdmin();
		if (!admin.tableExists(tn)) {
			HTableDescriptor hbaseTable = new HTableDescriptor(tn);
			if (families != null && families.length > 0)
				for (String family : families) {
					hbaseTable.addFamily(new HColumnDescriptor(family));
				}
			admin.createTable(hbaseTable);
			logger.info(tn.getNameAsString() + "表已经成功创建!----------------");
		}
	}

	/**
	 * 删除表
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	public void deleteTable(String tableName) throws IOException {
		TableName tn = TableName.valueOf(tableName); // 创建表名对象
		Admin admin = hbaseConn.getConn().getAdmin();
		if (admin.tableExists(tn)) {
			admin.deleteTable(tn);
			System.out.println(tn.getNameAsString() + "表已经成功删除!----------------");
		} else {
			System.out.println(tn.getNameAsString() + "表不存在");
		}
	}

	/**
	 * 
	 * 
	 * @param tableName
	 * @param topicName
	 * @return
	 */
	public JSONObject scanWithFilter(String tableName, String topicName, String id) {
		JSONObject jobj = new JSONObject();
		try {
			jobj.put("id", id);
			TableName tn = TableName.valueOf(tableName); // 创建表名对象
			Table table = hbaseConn.getConn().getTable(tn);
			Filter filter = new RowFilter(CompareOp.EQUAL,
					new BinaryPrefixComparator(Bytes.toBytes(topicName + "_" + id + "_")));
			Scan s = new Scan();
			s.setFilter(filter);
			ResultScanner rs = table.getScanner(s);
			for (Result r : rs) {
				for (Cell cell : r.rawCells()) {
					String family = Bytes.toString(CellUtil.cloneFamily(cell));
					if ("origin".equals(family)) {
						String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
						switch (qualifier) {
						case "content":
							String content = Bytes.toString(CellUtil.cloneValue(cell));
							jobj.put("content", content);
							continue;
						case "title":
							String title = Bytes.toString(CellUtil.cloneValue(cell));
							jobj.put("title", title);
							continue;
						default:
							continue;
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return jobj;
	}

	/**
	 * 根据rowkey获取记录
	 * 
	 * @param tableName
	 * @param rowkey
	 * @return
	 */
	public String getByRowKey(String tableName, String rowkey) {
		try {
			TableName tn = TableName.valueOf(tableName); // 创建表名对象
			Table table = hbaseConn.getConn().getTable(tn);
			Get get = new Get(rowkey.getBytes());
			Result result = table.get(get);
			for (Cell cell : result.rawCells()) {
				String qualifier = new String(CellUtil.cloneQualifier(cell));
				if (qualifier.equals("tags")) {
					System.out.println(qualifier);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 单个值插入
	 * 
	 * @param tableName
	 * @param rowKey
	 * @param family
	 * @param qualifier
	 * @param value
	 */
	public void put(String tableName, String rowKey, String family, String qualifier, String value) {
		try {
			createTable(tableName, "origin", "result");
			TableName tn = TableName.valueOf(tableName); // 创建表名对象
			Table table = hbaseConn.getConn().getTable(tn);
			Put put = new Put(rowKey.getBytes());
			put.addColumn(family.getBytes(), qualifier.getBytes(), value.getBytes());
			table.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 多记录插入
	 * 
	 * @param tableName
	 * @param list
	 */
	public void put(String tableName, Put put) {
		try {
			createTable(tableName, "origin", "result");
			TableName tn = TableName.valueOf(tableName); // 创建表名对象
			Table table = hbaseConn.getConn().getTable(tn);
			table.put(put);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
