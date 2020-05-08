import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class CommentWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField enterComment;
	private static String newUser = "";
	private static String newComment = "";
	private static String newGame = "";
	private DefaultTableModel model = new DefaultTableModel();
	private static boolean deleting = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CommentWindow frame = new CommentWindow("empty");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CommentWindow(String game) {	
		newGame = game;
		
		setTitle(game + " | Comments");
		setResizable(false);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 708, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 52, 696, 363);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		
		setup();
		scrollPane.setViewportView(table);
		
		JLabel modadminerror = new JLabel("Must be a moderator or admin to delete comments!");
		modadminerror.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		modadminerror.setHorizontalAlignment(SwingConstants.CENTER);
		modadminerror.setForeground(Color.RED);
		modadminerror.setBounds(6, 425, 696, 16);
		contentPane.add(modadminerror);
		modadminerror.setVisible(false);
		
		JLabel Warning = new JLabel("Invalid new comment. Comment must be at least 5 characters long.");
		Warning.setHorizontalAlignment(SwingConstants.CENTER);
		Warning.setForeground(Color.RED);
		Warning.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		Warning.setBounds(6, 425, 696, 16);
		contentPane.add(Warning);
		Warning.setVisible(false);
		
		JLabel deleted = new JLabel("Comment successfully deleted.");
		deleted.setHorizontalAlignment(SwingConstants.CENTER);
		deleted.setForeground(Color.RED);
		deleted.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		deleted.setBounds(6, 425, 696, 16);
		contentPane.add(deleted);
		deleted.setVisible(false);
		
		JButton DeleteButton = new JButton("Delete");
		DeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Warning.setVisible(false);	
				deleting = true;
				
				if(Login.getUser().equals("moderator") || Login.getUser().equals("admin")) {
					int wantedCount = table.getSelectedRow()+1;
					int count = 0;
					
					for(int i = 0; i < Driver.commentList.size(); i++) {
						if(Driver.commentList.get(i).getGame().equals(newGame)) {
							count++;
							if(count == wantedCount) {
								clearText();
								Driver.commentList.get(i).deleteGame();
								deleted.setVisible(true);
							}
						}
					}
					setup();
				} else {
					modadminerror.setVisible(true);
					Warning.setVisible(false);
					deleted.setVisible(false);
				}
				
			}
		});
		DeleteButton.setBounds(233, 526, 117, 29);
		contentPane.add(DeleteButton);
		
		JLabel NewCommentText = new JLabel("New Comment");
		NewCommentText.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		NewCommentText.setBounds(251, 441, 198, 41);
		contentPane.add(NewCommentText);
		
		JLabel CommentText = new JLabel("Comment:");
		CommentText.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		CommentText.setBounds(6, 485, 78, 29);
		contentPane.add(CommentText);
		
		enterComment = new JTextField();
		enterComment.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		enterComment.setColumns(10);
		enterComment.setBounds(85, 487, 617, 30);
		contentPane.add(enterComment);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				deleting = false;
				modadminerror.setVisible(false);
				deleted.setVisible(false);
				
				newUser = Login.getUser();
				newComment = enterComment.getText();
				
				if(newComment.length() >= 5) {
					Driver.writeToFile();
					Warning.setVisible(false);
					
					clearText();
					
					//clear rows
					setup();
				}
				else {
					Warning.setVisible(true);
				}
			}
		});
		enterButton.setBounds(351, 526, 117, 29);
		contentPane.add(enterButton);
			
		JLabel TitleText = new JLabel("Comments for " + game);
		TitleText.setHorizontalAlignment(SwingConstants.CENTER);
		TitleText.setForeground(Color.RED);
		TitleText.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		TitleText.setBounds(6, 6, 696, 41);
		contentPane.add(TitleText);
		
	}
	
	
	
	private void clearText() {
		enterComment.setText("");
	}
	
	public static String output() {
		if(newComment.length()>1) {
			String output = newGame + "," + newUser + "," + newComment;
			return output;
		}
		return "";
	}
	
	private void setup() {
		Driver.retrieveGamesList();
		String [] column = {"Username", "Comment"};
		String[][] data = new String[Driver.commentList.size()][2];
		int count = 0;
		for(int i = 0; i < Driver.commentList.size(); i++) {
			if(Driver.commentList.get(i).getGame().equals(newGame)) {
				data[count][0] = Driver.commentList.get(i).getUsername();
				data[count][1] = Driver.commentList.get(i).getComment();
				count++;
			}
		}
		
		model.setDataVector(data, column);
	}
	
	public static boolean getDeleting() {
		return deleting;
	}
}
