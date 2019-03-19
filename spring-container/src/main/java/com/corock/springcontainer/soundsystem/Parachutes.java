package com.corock.springcontainer.soundsystem;

import org.springframework.stereotype.Component;

@Component
public class Parachutes implements CompactDisc {

	private String title = "Yellow";
	private String artist = "Coldplay";

	@Override
	public void play() {
		System.out.println( "Playing " + title + " by " + artist );
	}

	@Override
	public String toString() {
		return "Parachutes [title=" + title + ", artist=" + artist + "]";
	}

}
