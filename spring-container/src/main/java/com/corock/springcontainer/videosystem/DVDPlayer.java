package com.corock.springcontainer.videosystem;

public class DVDPlayer {

	private DigitalVideoDisc dvd;
	
	public DVDPlayer() {
	}
	
	public DVDPlayer( DigitalVideoDisc dvd ) {
		this.dvd = dvd;
	}
	
	public void setDigitalVideoDisc( DigitalVideoDisc dvd ) {
		this.dvd = dvd;
	}
	
	public void play() {
		dvd.play();
	}
	
}
