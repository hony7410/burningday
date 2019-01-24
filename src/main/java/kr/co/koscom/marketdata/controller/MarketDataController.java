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
import kr.co.koscom.marketdata.model.Price;

@Controller
public class MarketDataController {
	
	@Autowired
	private MarketDataApiCaller marketDataApiCaller;

	@Autowired
	private LogpressoApiCaller logpressoApiCaller;

    @RequestMapping(path = "/marketdata/price/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody Price priceJson(@PathVariable String issueCode) {
        return marketDataApiCaller.getPrice(issueCode);
    }
    
    
    @RequestMapping(path = "/marketdata/graph/price/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String priceJson(@PathVariable String issueCode, Model model) {
        
    	model.addAttribute("issueCode", issueCode);
    	
    	// return view
    	return "graph";
    }
    
    @RequestMapping(path = "/marketdata/log/10m/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody JsonNode log10mJson(@PathVariable String issueCode) {
        return logpressoApiCaller.get10mLog(issueCode);
    }
    
    @RequestMapping(path = "/marketdata/graph/priceAll/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String priceAllJson(@PathVariable String issueCode, Model model) {
        
    	model.addAttribute("issueCode", issueCode);
    	
    	// return view
    	return "graphAll";
    }
    
  
 }
