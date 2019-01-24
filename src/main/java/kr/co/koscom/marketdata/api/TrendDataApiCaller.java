package kr.co.koscom.marketdata.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.model.Keyword;
import kr.co.koscom.marketdata.model.SearchData;
import kr.co.koscom.marketdata.model.TrendData;
import kr.co.koscom.marketdata.util.HttpClientUtil;

@Component
public class TrendDataApiCaller {

	private static String URI_TREND = "https://sandbox-apigw.koscom.co.kr/v2/topics/trending/news/";

	
	private static String APIKEY = "l7xx230ef2235e34448c982eb192ac98e206";
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	

	public SearchData[] getSearchData(String keyword)
	{
		try {
			
			StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v1/haystack/entities/company/_search");
		    urlBuilder.append("?");
		    
		    //urlBuilder.append(URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode("미세먼지", "UTF-8") + "&");
		    urlBuilder.append(URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8") + "&");
		    urlBuilder.append(URLEncoder.encode("apikey","UTF-8") + "=" + URLEncoder.encode("l7xx30a19d389f204686a4b2a0e150ade045", "UTF-8"));
		    URL url = new URL(urlBuilder.toString());
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    System.out.println("Response code: " + conn.getResponseCode());
		    BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        
			JsonNode node = objectMapper.readTree(sb.toString());

			if(node.findValue("found").toString().equals("true"))
			{
				List<JsonNode> nodes = node.findValue("data").findValues("buckets");
				for(JsonNode j : nodes)
				{
					return objectMapper.readValue(j.findValue("entities").toString(), SearchData[].class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public TrendData[] getTrend(){
		
		try {
			
			StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v1/haystack/topics/trending/news");
	        urlBuilder.append("?");
	        urlBuilder.append(URLEncoder.encode("apikey","UTF-8") + "=" + URLEncoder.encode("l7xx30a19d389f204686a4b2a0e150ade045", "UTF-8"));
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
			JsonNode node = objectMapper.readTree(sb.toString());
			
			if(node.findValue("found").toString().equals("true"))
			{
				JsonNode trends = node.findValue("topics").findValue("news");//.findValues("all");
				
				List<JsonNode> jKeyword = trends.findValue("all").findValues("keywords");
				
				/*for(JsonNode j : trends)
				{
					
					trendList.add(trendData);
				}*/
				TrendData[] trendData = objectMapper.readValue(trends.findValue("all").toString(), TrendData[].class);
				for(int k = 0; k < trendData.length; ++k){
					JsonNode n = jKeyword.get(k);
					Keyword[] keywords = objectMapper.readValue(n.toString(), Keyword[].class);
					trendData[k].setKeywordz(keywords);
				}
				
				return trendData;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public SearchData getSearchResult(){
		
		return null;
	}
	
}
