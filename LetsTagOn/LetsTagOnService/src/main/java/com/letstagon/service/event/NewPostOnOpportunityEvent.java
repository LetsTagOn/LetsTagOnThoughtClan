package com.letstagon.service.event;

import org.springframework.context.ApplicationEvent;

import com.letstagon.dao.model.Opportunity;
import com.letstagon.dao.model.Post;

// TODO: Auto-generated Javadoc
/**
 * The Class NewPostOnOpportunityEvent.
 */
public class NewPostOnOpportunityEvent extends ApplicationEvent{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 878500282200731741L;
	
	/** The post. */
	private Post post;
	
	/** The opportunity. */
	private Opportunity opportunity;
	
	/**
	 * Instantiates a new new post on opportunity event.
	 *
	 * @param source the source
	 */
	public NewPostOnOpportunityEvent(Object source) {
		super(source);
		
	}
	
	/**
	 * Instantiates a new new post on opportunity event.
	 *
	 * @param source the source
	 * @param post the post
	 * @param opportunity the opportunity
	 */
	public NewPostOnOpportunityEvent(Object source, Post post, Opportunity opportunity) {
		this(source);
		this.post = post;
		this.opportunity = opportunity;
	
		
	}
	
	/* (non-Javadoc)
	 * @see java.util.EventObject#toString()
	 */
	public String toString(){
		return "message printed event raised with: ("+ opportunity +" ) and (" + post +") ";
	}
	
	/**
	 * Gets the post.
	 *
	 * @return the post
	 */
	public Post getPost() {
		return post;
	}
	
	/**
	 * Sets the post.
	 *
	 * @param post the new post
	 */
	public void setPost(Post post) {
		this.post = post;
	}
	
	/**
	 * Gets the opportunity.
	 *
	 * @return the opportunity
	 */
	public Opportunity getOpportunity() {
		return opportunity;
	}
	
	/**
	 * Sets the opportunity.
	 *
	 * @param opportunity the new opportunity
	 */
	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}
}
