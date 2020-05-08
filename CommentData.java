
public class CommentData {
	
	private String game, username, comment;
	
	/**
	 * Generate a new CommentData.
	 * @param gameName - Name of the game being commented.
	 * @param user - Username that is writing the comment.
	 * @param commentSelection - The comment being written.
	 */
	public CommentData(String gameName, String user, String commentSelection) {
		this.game = gameName;
		this.username = user;
		this.comment = commentSelection;
	}
	
	
	/**
	 * Returns the title of comment's game.
	 * @return title of the game.
	 */
	public String getGame() {
		return this.game;
	}
	
	/**
	 * Returns the username that wrote the comment.
	 * @return username of the comment.
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Returns the content of the comment.
	 * @return comment content.
	 */
	public String getComment() {
		return this.comment;
	}
	
	/**
	 * Flag this comment as deleted and remove it from the game's listed comments.
	 */
	public void deleteComment() {
		this.game = "DELETED";
		Driver.writeToFile();
	}

}
