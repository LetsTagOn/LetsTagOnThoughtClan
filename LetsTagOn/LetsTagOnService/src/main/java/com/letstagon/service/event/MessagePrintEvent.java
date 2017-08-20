package com.letstagon.service.event;

import org.springframework.context.ApplicationEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class MessagePrintEvent.
 */
public class MessagePrintEvent extends ApplicationEvent{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9027163410121119043L;
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new message print event.
	 *
	 * @param source the source
	 */
	public MessagePrintEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new message print event.
	 *
	 * @param source the source
	 * @param message the message
	 */
	public MessagePrintEvent(Object source, String message) {
		this(source);
		this.message = message;
		
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString(){
		return "message printed event raised with: ("+ message +" )";
	}
}
