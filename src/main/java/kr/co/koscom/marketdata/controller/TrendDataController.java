package kr.co.koscom.marketdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import kr.co.koscom.marketdata.api.LogpressoApiCaller;
import kr.co.koscom.marketdata.api.MarketDataApiCaller;
import kr.co.koscom.marketdata.api.TrendDataApiCaller;

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
    public @ResponseBody void priceJson(@PathVariable String issueKeyword) {
        trendDataApiCaller.getSearchData(issueKeyword);
        return;
    }
    
 }
