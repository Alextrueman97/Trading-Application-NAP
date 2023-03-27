package com.napgroup.models;

public enum Region {
	
	LSE("London Stock Exchange"), SSE("Shanghai Stock Exchange"), NYSE("New York Stock Exchange");
	
	private String name;
	
	private Region(String name) {
		this.name = name;
	}
	
	public String getName(String name) {
		return this.name;
	}

}
