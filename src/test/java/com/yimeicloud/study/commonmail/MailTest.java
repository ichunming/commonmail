package com.yimeicloud.study.commonmail;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yimeicloud.study.commonmail.util.MailUtil;

/**
 * Unit test for simple App.
 */
public class MailTest {
	@Test
	public void test() {
		// send simple email
		//boolean result = MailUtil.send("主题", "内容", "407201244@qq.com");
		
		// send email with attachment
		List<Map<String, String>> attachs = new ArrayList<Map<String, String>>();
		Map<String, String> attach = new HashMap<String, String>();
		attach.put("name", "test.txt");
		attach.put("path", "test.txt");
		attachs.add(attach);
		boolean result = MailUtil.send("主题", "内容", "***@qq.com", attachs);
		assertTrue(result);
	}
}
