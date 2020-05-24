package com.spring.kpmc.events;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping("/land")
public class LandOnDownStreamRestController {

	private final ApplicationEventPublisher applicationEventPublisher;

	static Logger log = LoggerFactory.getLogger(LandOnDownStreamRestController.class);

	@Autowired
	public LandOnDownStreamRestController(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@GetMapping("/fileLand")
	String landOnDownStream() {

		log.info("Land on DownStreams  :   Restcontroller");
		FileLandedOnDownStreamEvents event = new FileLandedOnDownStreamEvents(this);
		applicationEventPublisher.publishEvent(event); 
		Date date = new Date();
		String s = "ramaiah"; System.out.println(s.concat(date.toString()));
		return (" We just landed file on Downstreams  :::::::" + LocalDateTime.now() + " event timestamp " + event.getTimestamp());

	}

}
