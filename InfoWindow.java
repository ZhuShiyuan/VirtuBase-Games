import java.awt.BorderLayout;
import org.eclipse.swt.*;
import org.eclipse.swt.browser.Browser;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Canvas;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.ScrollPane;

public class InfoWindow extends JFrame {

	private JPanel contentPane;
	private String name, company, wiki, keywords, stores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					InfoWindow frame = new InfoWindow("empty");
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
	public InfoWindow(String gameName) {
		//Set variable names
		name = gameName;
		    //Company
		if(name == "Call of Duty: Modern Warfare") ;
		else if(name == "CSGO") ;
		else if(name == "Minecraft") ;
		else if (name == "Fortnite") ;
		else if (name == "Overwatch") ;
		else if (name == "GTA") ;
		else if (name == "RocketLeague");
		else if (name == "Destiny") ;
		    //wiki
		if(name == "Call of Duty: Modern Warfare") ;
		else if(name == "CSGO") ;
		else if(name == "Minecraft") ;
		else if (name == "Fortnite") ;
		else if (name == "Overwatch") ;
		else if (name == "GTA") ;
		else if (name == "RocketLeague");
		else if (name == "Destiny") ;
		    //keywords
		if(name == "Call of Duty: Modern Warfare") ;
		else if(name == "CSGO") ;
		else if(name == "Minecraft") ;
		else if (name == "Fortnite") ;
		else if (name == "Overwatch") ;
		else if (name == "GTA") ;
		else if (name == "RocketLeague");
		else if (name == "Destiny") ;
		    //stores
		if(name == "Call of Duty: Modern Warfare") ;
		else if(name == "CSGO") ;
		else if(name == "Minecraft") ;
		else if (name == "Fortnite") ;
		else if (name == "Overwatch") ;
		else if (name == "GTA") ;
		else if (name == "RocketLeague");
		else if (name == "Destiny") ;
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(gameName);
		setBounds(100, 100, 638, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfoWindow = new JLabel(name);
		lblInfoWindow.setForeground(Color.RED);
		lblInfoWindow.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblInfoWindow.setBounds(15, 6, 626, 49);
		contentPane.add(lblInfoWindow);
		
		JLabel lblCompany = new JLabel("Company: " + company);
		lblCompany.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblCompany.setBounds(15, 60, 396, 40);
		contentPane.add(lblCompany);
		
		JLabel lblWiki = new JLabel("wiki:" + wiki);
		lblWiki.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblWiki.setBounds(15, 110, 396, 40);
		contentPane.add(lblWiki);
		
		JLabel lblKeywords = new JLabel("Keywords:" + keywords);
		lblKeywords.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblKeywords.setBounds(15, 160, 396, 40);
		contentPane.add(lblKeywords);
		
		JLabel lblStores = new JLabel("Stores: " + stores);
		lblStores.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStores.setBounds(15, 210, 396, 40);
		contentPane.add(lblStores);
		
		JLabel lblNewLabel = new JLabel("");
		String picture;
		
		if(name == "Call of Duty: Modern Warfare") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/callofdutypicture.jpg";
		else if(name == "CSGO") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/csgopicture.jpeg";
		else if(name == "Minecraft") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/minecraftpicture.jpeg";
		else if (name == "Fortnite") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/fortnitepicture.jpg";
		else if (name == "Overwatch") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/overwatchpicture.jpg";
		else if (name == "GTA") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/gtapicture.png";
		else if (name == "RocketLeague") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/rlpicture.jpg";
		else if (name == "Destiny") picture = "/Users/josh/CSE201Workspace/CSE201/pictures/destinypicture.jpg";
		else picture = "NOT FOUND";
		
		lblNewLabel.setIcon(new ImageIcon(picture));
		lblNewLabel.setBounds(411, 67, 200, 283);
		contentPane.add(lblNewLabel);
	}
}
