package kr.co.koscom.marketdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalData {
	private String BzDd; // date
	private int trdPrc; // end price
	private int opnprc; // start price
	private int hgprc; // highest price
	private int lwprc; // lowest price

	public String getBzDd() {
		return BzDd;
	}
	public void setBzDd(String bzDd) {
		BzDd = bzDd;
	}
	public int getTrdPrc() {
		return trdPrc;
	}
	public void setTrdPrc(int trdPrc) {
		this.trdPrc = trdPrc;
	}
	public int getOpnprc() {
		return opnprc;
	}
	public void setOpnprc(int opnprc) {
		this.opnprc = opnprc;
	}
	public int getHgprc() {
		return hgprc;
	}
	public void setHgprc(int hgprc) {
		this.hgprc = hgprc;
	}
	public int getLwprc() {
		return lwprc;
	}
	public void setLwprc(int lwprc) {
		this.lwprc = lwprc;
	}
	
}
