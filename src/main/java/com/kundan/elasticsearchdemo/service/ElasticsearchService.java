/**
 * 
 */
package com.kundan.elasticsearchdemo.service;

import java.util.Map;

import org.apache.lucene.index.IndexNotFoundException;
import org.elasticsearch.indices.IndexAlreadyExistsException;

/**
 * @author kundan
 *
 */
public interface ElasticsearchService {

	boolean createIndex(String indexName) throws IndexAlreadyExistsException;

	Map<String, String> getIndexSettings(String indexName) throws IndexNotFoundException;
	
	public long bulkIndex();
}
