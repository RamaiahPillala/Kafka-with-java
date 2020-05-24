package com.spring.kpmc.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileLandedService implements ApplicationListener<FileLandedOnDownStreamEvents> {

	static Logger log = LoggerFactory.getLogger(FileLandedService.class);

	@Override
	public void onApplicationEvent(FileLandedOnDownStreamEvents event) {
		log.info("File Landed Service :::::::" + event.getClass().getName());

	}

}
