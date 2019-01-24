package kr.co.koscom.marketdata.api;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.koscom.marketdata.util.HttpClientUtil;

@Component
public class LogpressoApiCaller {

	//private static String URI_PREFIX = "http://10.10.10.107:8080/logpresso/httpexport/query.json";
	
	//private static String APIKEY = "02e72b0f-f7c5-c016-8921-4a24d2792849";
	
	//로컬피씨
    private static String URI_PREFIX = "http://127.0.0.1:8888/logpresso/httpexport/query.json";
	
	private static String APIKEY = "d47ecfd3-1b09-93a5-d02f-cccadb52fa20";
	
	@Autowired
	private HttpClientUtil httpClientUtil;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public JsonNode get10mLog(String issueCode) {
    	String query = "table duration=10m marketdata_trade | search 단축코드 == \"" + issueCode + "\"";
		return executeQuery(query);
	}
	
	public String test(String issueCode) {
	    String query = "table marketdata_trade | search 단축코드 == \"005930\"";
    	JsonNode node = executeQuery(query);
    	return node.toString();
	}
	
	public JsonNode getRecentTrade(int num){
		String query = "table marketdata_trade | limit "+num+" | fields 단축코드, 등락율, 현재가";
		JsonNode node = executeQuery(query);
		return node;
	}
	
	
	private JsonNode executeQuery(String query) {
		
		try {
			String jsonStr = httpClientUtil.execute(URI_PREFIX + "?_apikey=" + URLEncoder.encode(APIKEY, "UTF-8") 
													+ "&_q=" + URLEncoder.encode(query, "UTF-8"));

			// 응답 받은 json이 배열형태가 아닌 line break로 구분되어 있어, 배열형태로 바꾸어준다.
			String[] lines = jsonStr.split("\n");
			String finalStr = Arrays.stream(lines).collect(Collectors.joining(", "));
			finalStr = "[" + finalStr + "]";

			JsonNode node = objectMapper.readTree(finalStr);
			
			return node;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
