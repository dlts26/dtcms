package com.dt.cms;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dt.cms.hbase.HBaseDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HBaseTest {

	@Autowired
	HBaseDAO hBaseDAO;

	@Test
	public void testCreateTable() {
		try {
			hBaseDAO.createTable("infoTable", "origin", "result");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testPut() {
		hBaseDAO.put("infoTable", "NEWS_111", "origin", "authorName", "东方财富网");

	}

	@Test
	public void testGet() {
		hBaseDAO.getByRowKey("infoTable", "NEWS_111");
	}
}
