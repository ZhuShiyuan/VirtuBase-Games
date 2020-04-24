

import java.awt.BorderLayout;
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

public class CommentWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField enterUsername;
	private JTextField enterComment;

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
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		table.setModel(new DefaultTableModel (
				new Object[][] {
					
				},
				new String[] {
						"Username","Comment"
				}
		));
		scrollPane.setViewportView(table);

		
		Driver.retrieveGamesList();
		JButton RefreshButton = new JButton("Refresh");
		RefreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//clear rows
				DefaultTableModel model = new DefaultTableModel();
				table = new JTable(model);
				
				table.setModel(new DefaultTableModel (
						new Object[][] {
							
						},
						new String[] {
								"Username","Comment"
						}
				));
				scrollPane.setViewportView(table);
				
				//add updated rows
				for(int i = 0; i < Driver.commentList.size()/2; i++) {
					CommentData comments = Driver.commentList.get(i);
					if(comments.getGame().equals(game)) {
						Object[] newComment = new Object[table.getModel().getColumnCount()];
						newComment[0] = comments.getUsername();
						newComment[1] = comments.getComment();
						((DefaultTableModel) table.getModel()).addRow(newComment);
					}
				}
			}
		});
		RefreshButton.setBounds(206, 526, 117, 29);
		contentPane.add(RefreshButton);
		
		JLabel NewCommentText = new JLabel("New Comment");
		NewCommentText.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		NewCommentText.setBounds(251, 441, 198, 41);
		contentPane.add(NewCommentText);
		
		JLabel UsernameText = new JLabel("Username:");
		UsernameText.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		UsernameText.setBounds(6, 485, 78, 29);
		contentPane.add(UsernameText);
		
		enterUsername = new JTextField();
		enterUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		enterUsername.setBounds(88, 484, 153, 30);
		contentPane.add(enterUsername);
		enterUsername.setColumns(10);
		
		JLabel CommentText = new JLabel("Comment:");
		CommentText.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		CommentText.setBounds(245, 485, 78, 29);
		contentPane.add(CommentText);
		
		enterComment = new JTextField();
		enterComment.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		enterComment.setColumns(10);
		enterComment.setBounds(324, 487, 378, 30);
		contentPane.add(enterComment);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//clear rows
				DefaultTableModel model = new DefaultTableModel();
				table = new JTable(model);
				
				table.setModel(new DefaultTableModel (
						new Object[][] {
							
						},
						new String[] {
								"Username","Comment"
						}
				));
				scrollPane.setViewportView(table);
				
				//add updated rows
				for(int i = 0; i < Driver.commentList.size()/2; i++) {
					CommentData comments = Driver.commentList.get(i);
					if(comments.getGame().equals(game)) {
						Object[] newComment = new Object[table.getModel().getColumnCount()];
						newComment[0] = comments.getUsername();
						newComment[1] = comments.getComment();
						((DefaultTableModel) table.getModel()).addRow(newComment);
					}
				}
			}
		});
		enterButton.setBounds(324, 526, 117, 29);
		contentPane.add(enterButton);
	}
}
