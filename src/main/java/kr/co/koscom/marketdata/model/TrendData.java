package kr.co.koscom.marketdata.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrendData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 12763323455L;
	
	private long uid;// 24854332,
	private String uid_str;// "24854332",
	private String category;// "news",
	private String section;// "all",
	private int rank;//: 1,
	private float score;// 97.9717,
	private String topic;// "로제 짜릿한 유혹",

	private Keyword[] keywordz;
	public long getUid() {
		return uid;
	}
	

	public Keyword[] getKeywordz() {
		return keywordz;
	}
	public void setKeywordz(Keyword[] keywords) {
		this.keywordz = keywords;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getUid_str() {
		return uid_str;
	}
	public void setUid_str(String uid_str) {
		this.uid_str = uid_str;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TrendData [uid=" + uid + ", uid_str=" + uid_str + ", category=" + category + ", section=" + section
				+ ", rank=" + rank + ", score=" + score + ", topic=" + topic + "]";
	}
	
	
}
