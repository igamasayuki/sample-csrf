package com.sample;

/**
 * 掲示板の記事を表すクラス.
 * 
 * @author igamasayuki
 *
 */
public class Article {
	/** 名前 */
	private String name;
	/** 本文 */
	private String body;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
