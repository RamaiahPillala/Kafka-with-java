package com.spring.kpmc.events;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.Event;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileLandedListenerService {
//	Logger log;
	
	static Logger log = org.slf4j.LoggerFactory.getLogger(FileLandedListenerService.class);
	
	@EventListener
	void onLandedDownstreams(FileLandedOnDownStreamEvents event) {
	log.warn(" Just recieved the Evnet ::::" + event.getClass().getName() +  " , " + "timestamp  :" +event.getTimestamp());
		
		System.out.println(event.getClass().getName());
		
	}

}
