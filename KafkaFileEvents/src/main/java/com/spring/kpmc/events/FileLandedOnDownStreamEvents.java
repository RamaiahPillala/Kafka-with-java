package com.spring.kpmc.events;

import org.springframework.context.ApplicationEvent;

public class FileLandedOnDownStreamEvents  extends ApplicationEvent{
	
	public FileLandedOnDownStreamEvents(Object source) {
		super(source);
	}

}
