package com.letstagon.facade.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Lob;

// TODO: Auto-generated Javadoc
/**
 * The DTO class for the PostDTO core entity.
 * 
 */

public class PostDTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id. */
	private long id;
	
	/** The content. */
	private String content;
	
	/** The title. */
	private String title;
	
	/** The posted on. */
	private Date postedOn;
	
	/** The status. */
	private String status;
	
	/** The thumbnail url. */
	private String thumbnailUrl;
	
	/** The type. */
	private String type;
	
	/** The url. */
	private String url;
	
	/** The comments. */
	private List<CommentDTO> comments;
	
	/** The post. */
	private PostDTO post;
	
	/** The posts. */
	private List<PostDTO> posts;
	
	/** The posted by party. */
	private PartyDTO postedByParty;
	
	/** The posted for party. */
	private PartyDTO postedForParty;
	
	/** The posted for opportunity. */
	private OpportunityDTO postedForOpportunity;

	/**
	 * Instantiates a new post DTO.
	 */
	public PostDTO() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	@Lob
	public String getContent() {
		return this.content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the posted on.
	 *
	 * @return the posted on
	 */
	public Date getPostedOn() {
		return this.postedOn;
	}

	/**
	 * Sets the posted on.
	 *
	 * @param postedOn the new posted on
	 */
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the thumbnail url.
	 *
	 * @return the thumbnail url
	 */
	public String getThumbnailUrl() {
		return this.thumbnailUrl;
	}

	/**
	 * Sets the thumbnail url.
	 *
	 * @param thumbnailUrl the new thumbnail url
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public List<CommentDTO> getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 * @return the comment DTO
	 */
	public CommentDTO addComment(CommentDTO comment) {
		getComments().add(comment);
		comment.setPostBean(this);

		return comment;
	}

	/**
	 * Removes the comment.
	 *
	 * @param comment the comment
	 * @return the comment DTO
	 */
	public CommentDTO removeComment(CommentDTO comment) {
		getComments().remove(comment);
		comment.setPostBean(null);

		return comment;
	}

	/**
	 * Gets the post.
	 *
	 * @return the post
	 */
	public PostDTO getPost() {
		return this.post;
	}

	/**
	 * Sets the post.
	 *
	 * @param post the new post
	 */
	public void setPost(PostDTO post) {
		this.post = post;
	}

	/**
	 * Gets the posts.
	 *
	 * @return the posts
	 */
	public List<PostDTO> getPosts() {
		return this.posts;
	}

	/**
	 * Sets the posts.
	 *
	 * @param posts the new posts
	 */
	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	/**
	 * Adds the post.
	 *
	 * @param post the post
	 * @return the post DTO
	 */
	public PostDTO addPost(PostDTO post) {
		getPosts().add(post);
		post.setPost(this);

		return post;
	}

	/**
	 * Removes the post.
	 *
	 * @param post the post
	 * @return the post DTO
	 */
	public PostDTO removePost(PostDTO post) {
		getPosts().remove(post);
		post.setPost(null);

		return post;
	}

	/**
	 * Gets the posted by party.
	 *
	 * @return the posted by party
	 */
	public PartyDTO getPostedByParty() {
		return postedByParty;
	}

	/**
	 * Sets the posted by party.
	 *
	 * @param postedByParty the new posted by party
	 */
	public void setPostedByParty(PartyDTO postedByParty) {
		this.postedByParty = postedByParty;
	}

	/**
	 * Gets the posted for party.
	 *
	 * @return the posted for party
	 */
	public PartyDTO getPostedForParty() {
		return postedForParty;
	}

	/**
	 * Sets the posted for party.
	 *
	 * @param postedForParty the new posted for party
	 */
	public void setPostedForParty(PartyDTO postedForParty) {
		this.postedForParty = postedForParty;
	}

	/**
	 * Gets the posted for opportunity.
	 *
	 * @return the posted for opportunity
	 */
	public OpportunityDTO getPostedForOpportunity() {
		return postedForOpportunity;
	}

	/**
	 * Sets the posted for opportunity.
	 *
	 * @param postedForOpportunity the new posted for opportunity
	 */
	public void setPostedForOpportunity(OpportunityDTO postedForOpportunity) {
		this.postedForOpportunity = postedForOpportunity;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
