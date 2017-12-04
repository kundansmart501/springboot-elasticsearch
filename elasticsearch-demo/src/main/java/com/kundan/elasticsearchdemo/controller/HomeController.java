/**
 * 
 */
package com.kundan.elasticsearchdemo.controller;

import org.apache.lucene.index.IndexNotFoundException;
import org.elasticsearch.indices.IndexAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kundan.elasticsearchdemo.service.ElasticSearchServiceImpl;

/**
 * @author kundan
 *
 */
@Controller
public class HomeController {

	@Autowired
	ElasticSearchServiceImpl elasticService;

	@RequestMapping("/home")
	public String getHomePage(){
		return "home";
	}
	
	@RequestMapping(value="/settings/{indexName}")
	public String getIndexSettings(Model model, @PathVariable String indexName) throws IndexNotFoundException {
		System.out.println("Inside get Index Settings");
		model.addAttribute("settings", elasticService.getIndexSettings(indexName));
		System.out.println(elasticService.getIndexSettings(indexName));
		return "home";
	}

	@RequestMapping("/createIndex/{indexName}")
	public String createIndex(Model model, @PathVariable String indexName) throws IndexAlreadyExistsException {
		boolean flag = elasticService.createIndex(indexName);
		if (flag) {
			model.addAttribute("flag", flag);
		}
		return "index";
	}
}
