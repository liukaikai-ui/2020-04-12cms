package com.kiki.cms.domain;

import java.io.Serializable;
import java.util.Date;

public class FriendlyLink implements Serializable{

	private Integer id;
	private String text;
	private String url;
	private Date datetime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "FriendlyLink [id=" + id + ", text=" + text + ", url=" + url + ", datetime=" + datetime + "]";
	}
	public FriendlyLink(Integer id, String text, String url, Date datetime) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.datetime = datetime;
	}
	public FriendlyLink() {
		super();
	}
	
	
}
