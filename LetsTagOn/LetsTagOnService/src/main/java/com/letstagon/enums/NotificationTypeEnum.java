package com.letstagon.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum NotificationTypeEnum.
 */
public enum NotificationTypeEnum {
	
	/** The connection accept. */
	CONNECTION_ACCEPT("ConnectionAcceptEvent",
			"Your Connection has been accepted"), 
 /** The connection request. */
 CONNECTION_REQUEST(
			"ConnectionRequestEvent", "Connection has been requested"), 
 /** The invite to opportunity. */
 INVITE_TO_OPPORTUNITY(
			"OpportunityInviteEvent", "You have been invited for opportunity"), 
 /** The message priint. */
 MESSAGE_PRIINT(
			"MessagePrintEvent", "Message"), 
 /** The new post on opportunity. */
 NEW_POST_ON_OPPORTUNITY(
			"NewPostOnOppEvent", "New post on"), 
 /** The opportunity application status change. */
 OPPORTUNITY_APPLICATION_STATUS_CHANGE(
			"OppAppStatusChangeEvent", "Your Application status has been updated"), 
 /** The opportunity attendence change. */
 OPPORTUNITY_ATTENDENCE_CHANGE(
			"OppAttChangeEvent", "Attendence Marked"), 
 /** The opportunity feedback change. */
 OPPORTUNITY_FEEDBACK_CHANGE(
			"OppFeedBackEvent", "FeedBack"), 
 /** The opportunity application sent. */
 OPPORTUNITY_APPLICATION_SENT(
			"OppAppSentEvent", "Application Sent"), 
 /** The opportunity application applied. */
 OPPORTUNITY_APPLICATION_APPLY(
			"OppAppApplyEvent", "Application Received"), 
 /** The password reset. */
 PASSWORD_RESET(
			"PasswordResetEvent", "Password has been reset");
	
	/**
	 * Instantiates a new notification type enum.
	 *
	 * @param eventType the event type
	 * @param message the message
	 */
	private NotificationTypeEnum(String eventType, String message) {
		this.eventType = eventType;
		this.message = message;
	}

	/** The event type. */
	private final String eventType;
	
	/** The message. */
	private final String message;

	/**
	 * Gets the event type.
	 *
	 * @return the event type
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
