package kr.co.koscom.marketdata.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.api.LogpressoApiCaller;
import kr.co.koscom.marketdata.api.MarketDataApiCaller;
import kr.co.koscom.marketdata.model.HistoricalData;
import kr.co.koscom.marketdata.model.Price;
import pl.stock.algorithm.core.RSI;

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
    @RequestMapping(path = "/marketdata/analyze/{marketCode}/{issueCode}/{from}/{to}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String getRSIChart(@PathVariable String marketCode, @PathVariable String issueCode, @PathVariable String from, @PathVariable String to) {
    	
    	to = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    	
    	HistoricalData[] his = marketDataApiCaller.getHistoricalData(marketCode.toLowerCase(), issueCode, from, to);
    	
    	RSI rsi = new RSI(14);
    	
    	double[] prizes = new double[his.length];
    	for(int k = his.length-1; k >=0; --k)
    	{
    		prizes[k] = his[k].getTrdPrc();
    	}
    	
    	double[] rsiResult = rsi.count(prizes);
    	
    	String s = "[";
    	int hisIndex = his.length-1;
    	int rsiIndex = 0;
    	for(int k = 0; k < his.length; ++k)
    	{
    		s += "{";
    		if(k >= his.length - rsiResult.length)
    		{
    			s += "\"rsi\" : " + rsiResult[rsiIndex++] + ",";
    		}
    		else
    		{
    			s += "\"rsi\" : -1,";
    		}
    		
    		s += "\"date\" : \"" + his[hisIndex-k].getBzDd() + "\",";
    		s += "\"price\" : " + his[hisIndex-k].getTrdPrc();
    		s += "},";
    	}
    	s+= "]";
    	
    	
    	return s;
    }
    @RequestMapping(path = "/marketdata/log/10m/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody JsonNode log10mJson(@PathVariable String issueCode) {
        return logpressoApiCaller.get10mLog(issueCode);
    }
    
    @RequestMapping(path = "/marketdata/graph/priceAll/{marketCode}/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public String priceAllJson(@PathVariable String marketCode, @PathVariable String issueCode, Model model) {
        
    	model.addAttribute("issueCode", issueCode);
    	model.addAttribute("marketCode", marketCode);
    	model.addAttribute("name", marketDataApiCaller.getCompanyNameById(marketCode,issueCode));
    	// return view
    	return "graphAll";
    }
      
    @RequestMapping(path = "/marketdata/historicalPrice/{marketCode}/{issueCode}",
    		method = { RequestMethod.GET, RequestMethod.POST } )
    public @ResponseBody String historicalPriceJson(@PathVariable String marketCode, @PathVariable String issueCode) {
    	
    	String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
    	
    	HistoricalData[] historicalDataArr = marketDataApiCaller.getHistoricalData(marketCode.toLowerCase(), issueCode, "20190101", timeStamp);
    	for(HistoricalData s : historicalDataArr){
    		System.out.println(s.toString());
    	}
    	
    	ObjectMapper mapper = new ObjectMapper();
    	//Object to JSON in String
    	try {
			String jsonInString = mapper.writeValueAsString(historicalDataArr);
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
