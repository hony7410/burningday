package kr.co.koscom.marketdata.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	
	
	public String getNaverTrend(String name,String endDate){
		String clientId = "9BDAFERjOvCA7mmdnnGV";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "c6OOLYWGv5";//애플리케이션 클라이언트 시크릿값";
        
        try {
            String apiURL = "https://openapi.naver.com/v1/datalab/search";
            //String body = "{\"startDate\":\"2017-01-01\",\"endDate\":\"2017-04-30\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\"한글\",\"keywords\":[\"한글\",\"korean\"]},{\"groupName\":\"영어\",\"keywords\":[\"영어\",\"english\"]}],\"device\":\"pc\",\"ages\":[\"1\",\"2\"],\"gender\":\"f\"}";
            //String body = "{\"startDate\":\"2018-01-01\",\"endDate\":\""+endDate+"\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\"코웨이\",\"keywords\":[\"코웨이\"]}],\"device\":\"pc\",\"ages\":[\"4\",\"5\",\"6\",\"7\"],\"gender\":[\"f\",\"m\"]}";
            //String body = "{\"startDate\":\"2019-01-01\",\"endDate\":\"2019-01-24\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\"한글\",\"keywords\":[\"한글\",\"korean\"]},{\"groupName\":\"영어\",\"keywords\":[\"영어\",\"english\"]}],\"device\":\"pc\",\"ages\":[\"1\",\"2\"],\"gender\":\"f\"}";
            String body = "{\"startDate\":\"2019-01-01\",\"endDate\":\""+endDate+"\",\"timeUnit\":\"date\",\"keywordGroups\":[{\"groupName\":\""+ name +"\",\"keywords\":[\""+ name +"\"]}],\"device\":\"pc\",\"ages\":[\"4\",\"5\",\"6\",\"7\"],\"gender\":\"m\"}";
            
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            /*DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();
            */
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
            writer.write(body);
            writer.flush();
            writer.close();
            wr.close();
                      
            
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    
        /*
        try {
            String apiURL = "https://openapi.naver.com/v1/datalab/search";
            /*- 4: 25∼29세
            - 5: 30∼34세
            - 6: 35∼39세
            - 7: 40∼44세*/
            //String body = "{\"startDate\":\"2018-01-01\",\"endDate\":\""+endDate+"\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\"코웨이\",\"keywords\":[\"코웨이\"]}],\"device\":[\"pc\",\"mo\"],\"ages\":[\"4\",\"5\",\"6\",\"7\"],\"gender\":[\"f\",\"m\"]}";
        /*
            String body = "{\"startDate\":\"2018-01-01\",\"endDate\":\"2019-01-24\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\"한글\",\"keywords\":[\"한글\",\"korean\"]}],\"device\":\"pc\",\"ages\":[\"3\",\"4\"],\"gender\":\"f\"}";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            return response.toString();

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }*/
	}
	
}
