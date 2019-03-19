package com.corock.springcontainer.soundsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CDPlayer {

	/** Wiring 1 */
	// @Autowired
	private CompactDisc cd;

	/** Wiring 2 */
	// @Autowired
	public CDPlayer( CompactDisc cd ) {
		this.cd = cd;
	}
	
	public CDPlayer() {
	}

	/** Wiring 3 */
	// @Autowired
	public void setCompactDisc( CompactDisc cd ) {
		this.cd = cd;
	}

	/** Wiring 4 */
	@Autowired
	public void insertCompactDisc( CompactDisc cd ) {
		this.cd = cd;
	}
	
	public void play() {
		cd.play();
	}
	
}
