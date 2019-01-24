package kr.co.koscom.marketdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.api.LogpressoApiCaller;
import kr.co.koscom.marketdata.api.MarketDataApiCaller;
import kr.co.koscom.marketdata.api.TrendDataApiCaller;
import kr.co.koscom.marketdata.model.SearchData;
import kr.co.koscom.marketdata.model.TrendData;

@Controller
public class TrendDataController {
	
	@Autowired
	private MarketDataApiCaller marketDataApiCaller;

	@Autowired
	private LogpressoApiCaller logpressoApiCaller;

	@Autowired
	private TrendDataApiCaller trendDataApiCaller;
	
    @RequestMapping(path = "/trenddata/company/{issueKeyword}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody String trendSearchJson(@PathVariable String issueKeyword) {
    	SearchData[] searchDataArr = trendDataApiCaller.getSearchData(issueKeyword);
    	for(SearchData s : searchDataArr){
    		System.out.println(s.toString());
    	}
    	
    	ObjectMapper mapper = new ObjectMapper();
    	//Object to JSON in String
    	try {
			String jsonInString = mapper.writeValueAsString(searchDataArr);
			System.out.println("I will return");
			System.out.println(jsonInString);
			return jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return null;
    }
    
    @RequestMapping(path = "/trenddata/trend",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody String trendJson() {
    	TrendData[] trendDataArr = trendDataApiCaller.getTrend();
    	for(TrendData s : trendDataArr){
    		System.out.println(s.toString());
    	}
    	
    	ObjectMapper mapper = new ObjectMapper();
    	//Object to JSON in String
    	try {
			String jsonInString = mapper.writeValueAsString(trendDataArr);
			System.out.println("I will return");
			System.out.println(jsonInString);
			return jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return null;
    }
    
 }
