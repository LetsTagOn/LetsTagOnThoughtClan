package com.letstagon.dao.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the Post database table.
 * 
 */
@Entity
@NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
public class Post implements Serializable {
	
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
	private List<Comment> comments;
	
	/** The post. */
	private Post post;
	
	/** The posts. */
	private List<Post> posts;
	
	/** The posted by party. */
	private Party postedByParty;
	
	/** The posted for party. */
	private Party postedForParty;
	
	/** The posted for opportunity. */
	private Opportunity postedForOpportunity;

	/**
	 * Instantiates a new post.
	 */
	public Post() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Temporal(TemporalType.TIMESTAMP)
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
	// bi-directional many-to-one association to Comment
	@OneToMany(mappedBy = "postBean")
	public List<Comment> getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 * @return the comment
	 */
	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setPostBean(this);

		return comment;
	}

	/**
	 * Removes the comment.
	 *
	 * @param comment the comment
	 * @return the comment
	 */
	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setPostBean(null);

		return comment;
	}

	/**
	 * Gets the post.
	 *
	 * @return the post
	 */
	// bi-directional many-to-one association to Post
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "originalPost")
	public Post getPost() {
		return this.post;
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
	 * Gets the posts.
	 *
	 * @return the posts
	 */
	// bi-directional many-to-one association to Post
	@OneToMany(mappedBy = "post")
	public List<Post> getPosts() {
		return this.posts;
	}

	/**
	 * Sets the posts.
	 *
	 * @param posts the new posts
	 */
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	/**
	 * Adds the post.
	 *
	 * @param post the post
	 * @return the post
	 */
	public Post addPost(Post post) {
		getPosts().add(post);
		post.setPost(this);

		return post;
	}

	/**
	 * Removes the post.
	 *
	 * @param post the post
	 * @return the post
	 */
	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setPost(null);

		return post;
	}

	/**
	 * Gets the posted by party.
	 *
	 * @return the posted by party
	 */
	// bi-directional many-to-one association to Party
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "party")

	public Party getPostedByParty() {
		return postedByParty;
	}

	/**
	 * Sets the posted by party.
	 *
	 * @param postedByParty the new posted by party
	 */
	public void setPostedByParty(Party postedByParty) {
		this.postedByParty = postedByParty;
	}

	/**
	 * Gets the posted for party.
	 *
	 * @return the posted for party
	 */
	// bi-directional many-to-one association to Party
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postedTo")
	public Party getPostedForParty() {
		return postedForParty;
	}

	/**
	 * Sets the posted for party.
	 *
	 * @param postedForParty the new posted for party
	 */
	public void setPostedForParty(Party postedForParty) {
		this.postedForParty = postedForParty;
	}

	/**
	 * Gets the posted for opportunity.
	 *
	 * @return the posted for opportunity
	 */
	// bi-directional many-to-one association to Party
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postedForOpportunity")
	public Opportunity getPostedForOpportunity() {
		return postedForOpportunity;
	}

	/**
	 * Sets the posted for opportunity.
	 *
	 * @param postedForOpportunity the new posted for opportunity
	 */
	public void setPostedForOpportunity(Opportunity postedForOpportunity) {
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