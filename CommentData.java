
public class CommentData {
	
	private String game, username, comment;
	
	public CommentData(String gameName, String user, String commentSelection) {
		this.game = gameName;
		this.username = user;
		this.comment = commentSelection;
	}
	
	public String getGame() {
		return this.game;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void deleteGame() {
		this.game = "DELETED";
		Driver.writeToFile();
	}

}
