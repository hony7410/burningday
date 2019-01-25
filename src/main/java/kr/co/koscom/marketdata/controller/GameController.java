package kr.co.koscom.marketdata.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import kr.co.koscom.marketdata.api.LogpressoApiCaller;
import kr.co.koscom.marketdata.api.MarketDataApiCaller;

@Controller
public class GameController {
	
	@Autowired
	private LogpressoApiCaller logpressoCaller;
	
	@Autowired
	private MarketDataApiCaller marketCaller;

    @RequestMapping(path = "/gamedata/fortunecookie/",
    method = { RequestMethod.GET, RequestMethod.POST } )
	public @ResponseBody String fortuneCookie()
	{
    	int count = 10000;
		JsonNode node = logpressoCaller.getRecentTrade(count);
		
		Random randy = new Random();
		int rnd = randy.nextInt(count);

		// @TODO add name to it
		
		
		String result = node.get(rnd).toString();
		
		String name = marketCaller.getCompanyNameById(node.get(rnd).findValue("단축코드").toString());
		result = result.replace("}", " ,\"회사이름\" : "+name+"}");
		
		
		return result;
		
	}
    
    @RequestMapping(path = "/gamedata/randombox/{type}",
    method = { RequestMethod.GET, RequestMethod.POST } )
	public @ResponseBody String randomBox(@PathVariable String type)
	{


		JsonNode node = logpressoCaller.getCompanyByType(type);
		
		Random randy = new Random();
		
		int rnd = randy.nextInt(node.size());

		// @TODO add name to it
		
		String str = node.get(rnd).findValue("종목코드").toString();
		int c = 6-str.length();
		String zero = "";
		for(int k = 0; k < c; k++)
		{
			zero += "0";
		}
		String result = node.get(rnd).toString().replace(str, zero+str);
		

		
		return result;
		
	}

}
