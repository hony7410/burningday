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

import kr.co.koscom.marketdata.model.HistoricalData;
import kr.co.koscom.marketdata.model.Price;
import kr.co.koscom.marketdata.model.SearchData;
import kr.co.koscom.marketdata.util.HttpClientUtil;

@Component
public class MarketDataApiCaller {


	private static String URI_PREFIX = "https://sandbox-apigw.koscom.co.kr/v2/market/stocks/kospi/";
	private static String URI_POSTFIX = "/price";
	
	private static String APIKEY = "l7xx230ef2235e34448c982eb192ac98e206";
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public Price getPrice(String issueCode) {
		
		try {
			String jsonStr = httpClientUtil.execute(URI_PREFIX + URLEncoder.encode(issueCode, "UTF-8") + URI_POSTFIX + "?apikey=" + URLEncoder.encode(APIKEY, "UTF-8"));
			JsonNode node = objectMapper.readTree(jsonStr);
			
			Price price = objectMapper.readValue(node.findValue("result").toString(), Price.class);
			
			return price;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getCompanyNameById(String id){
		try {
	        StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v2/market/stocks/{marketcode}/{issuecode}/master".replace("{marketcode}", URLEncoder.encode("kospi", "UTF-8")).replace("{issuecode}", id.replaceAll("\"", "")));
	        urlBuilder.append("?");
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
	        
	        JsonNode node = objectMapper.readTree(sb.toString());
	        
	        return node.findValue("result").findValue("isuKorAbbrv").toString();
	        

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @param marketcode 시장 구분 (kospi | kosdaq)
	 * @param issueCode 종목코드 예) 005930
	 * @param startDate 조회시작일자 (YYYYMMDD)
	 * @param endDate 조회종료일자 (YYYYMMDD)
	 * @return
	 */
	public HistoricalData[] getHistoricalData(String marketcode, String issueCode, String startDate, String endDate)
	{
		try {
			
			StringBuilder urlBuilder = new StringBuilder("https://sandbox-apigw.koscom.co.kr/v2/market/stocks/{marketcode}/{issuecode}/history".replace("{marketcode}", URLEncoder.encode(marketcode, "UTF-8")).replace("{issuecode}", URLEncoder.encode(issueCode, "UTF-8")));
	        urlBuilder.append("?");
	        urlBuilder.append(URLEncoder.encode("trnsmCycleTpCd","UTF-8") + "=" + URLEncoder.encode("D", "UTF-8") + "&");
	        urlBuilder.append(URLEncoder.encode("inqStrtDd","UTF-8") + "=" + URLEncoder.encode(startDate, "UTF-8") + "&");
	        urlBuilder.append(URLEncoder.encode("inqEndDd","UTF-8") + "=" + URLEncoder.encode(endDate, "UTF-8") + "&");
	        urlBuilder.append(URLEncoder.encode("reqCnt","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8") + "&");
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
	        
			JsonNode node = objectMapper.readTree(sb.toString());


			return objectMapper.readValue(node.findValue("result").findValue("hisLists").toString(), HistoricalData[].class);
			
		
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
