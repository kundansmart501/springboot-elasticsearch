package com.kundan.elasticsearchdemo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.apache.lucene.index.IndexNotFoundException;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kundan.elasticsearchdemo.service.ElasticsearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	ElasticsearchService elasticSearchService;

	@Test
	public void testIndexSettings() throws IndexNotFoundException {

		Map<String, String> map = elasticSearchService.getIndexSettings("imti");
		assertThat(map.isEmpty(), is(false));

	}

	@Test
	public void testIfIndexExists() {
		try {
			elasticSearchService.getIndexSettings("lilly");
		} catch (IndexNotFoundException e) {
			assertThat("lilly does not exist", e instanceof IndexNotFoundException, is(true));
		}

	}

	@Test
	public void testCreateIndex() {
		try {
			elasticSearchService.createIndex("lil");
			elasticSearchService.createIndex("imteyaz");
			System.out.println("Creating indexes....");
		} catch (Exception e) {
			assertThat("Index already exists", e instanceof IndexAlreadyExistsException, is(true));
			System.out.println("Index already created ....");
		}
	}

	@Test
	public void bulkIndex() {
		long count = 0;
		try {
			count = elasticSearchService.bulkIndex();
			System.out.println("Count of index "+count);
		}catch(Exception e) {

		}
		assertEquals(0, -1);
	}


}
