/**
 * 
 */
package com.kundan.elasticsearchdemo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.index.IndexNotFoundException;
import org.elasticsearch.index.Index;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kundan.elasticsearchdemo.model.Movie;

/**
 * @author kundan
 *
 */
@Service
public class ElasticSearchServiceImpl implements ElasticsearchService {

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@SuppressWarnings("unchecked")
	public Map<String, String> getIndexSettings(String indexName) throws IndexNotFoundException {
		Map<String, String> result = new HashMap<String, String>();
		if (elasticsearchTemplate.indexExists(indexName)) {
			result = elasticsearchTemplate.getSetting(indexName);
		} else {
			throw new IndexNotFoundException(indexName + " does not exists");
		}
		return result;
	}

	@Override
	public boolean createIndex(String indexName) throws IndexAlreadyExistsException {
		if (!elasticsearchTemplate.indexExists(indexName)) {
			elasticsearchTemplate.createIndex(indexName);
			return true;
		} else {
			throw new IndexAlreadyExistsException(new Index(indexName), " Already Exists");

		}
	}
	
	@Override
	public long bulkIndex() {
		
		int counter = 0;
        try {
            if (!elasticsearchTemplate.indexExists("movieindex")) {
                elasticsearchTemplate.createIndex("movieindex");
            }
            Gson gson = new Gson();
            List<IndexQuery> queries = new ArrayList<IndexQuery>();
            List<Movie> movies = createTestData();
            for (Movie movie : movies) {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(movie.getId().toString());
                indexQuery.setSource(gson.toJson(movie));
                indexQuery.setIndexName("movieindex");
                indexQuery.setType("movietype");
                queries.add(indexQuery);
                if (counter % 9 == 0) {
                    elasticsearchTemplate.bulkIndex(queries);
                    queries.clear();
                    System.out.println("bulkIndex counter : " + counter);
                }
                counter++;
            }
            if (queries.size() > 0) {
                elasticsearchTemplate.bulkIndex(queries);
            }
            elasticsearchTemplate.refresh("movieindex");
            System.out.println("bulkIndex completed.");
        } catch (Exception e) {
            System.out.println("IndexerService.bulkIndex e;" + e.getMessage());
            throw e;
        }
        return -1;
	}
	
	private List<Movie> createTestData() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(Long.valueOf(123), "Seat", "Leon","Leaon","2013"));
        movies.add(new Movie(Long.valueOf(124), "Seat", "Rio","Rio","2013"));
        movies.add(new Movie(Long.valueOf(125), "Kia", "Ceed","Ceed","2014"));
        movies.add(new Movie(Long.valueOf(126), "Mazda", "3","","2014"));
        movies.add(new Movie(Long.valueOf(127), "Volkswagen", "Polo","Corsa","2014"));
        movies.add(new Movie(Long.valueOf(128), "Volkswagen", "Golf","Corsa","2014"));
        movies.add(new Movie(Long.valueOf(129), "Volkswagen", "Tiguan","Corsa","2014"));
        movies.add(new Movie(Long.valueOf(130), "Opel", "Astra","Corsa","2014"));
        movies.add(new Movie(Long.valueOf(131), "Opel", "Corsa","Corsa","2014"));
        return movies;
    }
	
}
