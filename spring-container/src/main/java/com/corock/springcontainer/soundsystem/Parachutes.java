package com.corock.springcontainer.soundsystem;

import javax.inject.Named;

//@Component( "Parachutes" )
@Named( "Parachutes" )
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
