import java.awt.BorderLayout;
import java.net.URI;
import java.net.URISyntaxException;
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
import java.awt.Desktop;
import java.awt.Scrollbar;
import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.net.MalformedURLException;
import javax.swing.JButton;

public class InfoWindow extends JFrame {

	private JPanel contentPane;
	private String name, company, keyword1, keyword2, keyword3, storeName, wiki, storeLink, picture,
		newGame1, newGame2, newGame3;
	double storePrice;
	private int position = 0;
	private JButton Rec1, Rec2, Rec3, CommentsButton;

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
		//Setup
		Driver.retrieveGamesList();
		
		name = gameName;
		
		if(name.equals("Call of Duty: Modern Warfare")) position = 0;
		else if(name.equals("CSGO")) position = 1;
		else if(name.equals("Minecraft")) position = 2;
		else if (name.equals("Fortnite")) position = 3;
		else if (name.equals("Overwatch")) position = 4;
		else if (name.equals("GTA")) position = 5;
		else if (name.equals("RocketLeague")) position = 6;
		else if (name.equals("Destiny 2")) position = 7;
		
		GameData game = Driver.gameList.get(position);
		
		//Set variable names
		    //Company
		company = game.getCompany() ;
		    //wiki
		wiki = game.getWiki().toString();
		
		    //keywords
		keyword1 = game.getKeyword(0);
		keyword2 = game.getKeyword(1);
		keyword3 = game.getKeyword(2);
		
		    //stores
		storeName = game.getStorefront(0).getName();
		storePrice = game.getStorefront(0).getPrice();
		storeLink = game.getStorefront(0).getLink().toString();
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(gameName);
		setBounds(100, 100, 708, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel SteamStoreButton = new JLabel("<html><a href=''>" + storeName + "</a></html>");
		SteamStoreButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		SteamStoreButton.setBounds(129, 212, 219, 40);
		contentPane.add(SteamStoreButton);
		SteamStoreButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			         
			        Desktop.getDesktop().browse(new URI(storeLink));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});
		
		JLabel pcicon = new JLabel("");
		pcicon.setIcon(new ImageIcon("./pictures/pcicon.png"));
		pcicon.setBounds(86, 212, 40, 40);
		contentPane.add(pcicon);
		
		JLabel lblStores = new JLabel("Stores:");
		lblStores.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblStores.setBounds(15, 210, 396, 40);
		contentPane.add(lblStores);
		
		if(game.getStorefrontCount() == 1) {
			JLabel lblPrice = new JLabel("Price: " + storeName + " $" + storePrice);
			lblPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblPrice.setBounds(15, 310, 396, 40);
			contentPane.add(lblPrice);
		}
		
		//Multiple storefronts
		if(game.getStorefrontCount() == 2) {
			//change variables
			String storeName2 = game.getStorefront(1).getName();
			double storePrice2 = game.getStorefront(1).getPrice();
			String storeLink2 = game.getStorefront(1).getLink().toString();
			
			JLabel ps4icon = new JLabel("");
			ps4icon.setIcon(new ImageIcon("./pictures/ps4icon.png"));
			ps4icon.setBounds(86, 264, 40, 40);
			contentPane.add(ps4icon);
			
			JLabel ps4StoreButton = new JLabel("<html><a href=''>" + storeName2 + "</a></html>");
			ps4StoreButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
			ps4StoreButton.setBounds(129, 264, 219, 40);
			contentPane.add(ps4StoreButton);
			ps4StoreButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
				         
				        Desktop.getDesktop().browse(new URI(storeLink));
				         
				    } catch (IOException | URISyntaxException e1) {
				        e1.printStackTrace();
				    }
				}
			});
			
			JLabel lblPrice = new JLabel("Prices: " + storeName + " $" + storePrice + ", " + storeName2 + " $" + storePrice2);
			lblPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
			lblPrice.setBounds(15, 310, 396, 40);
			contentPane.add(lblPrice);
		}
		
		JLabel gametext = new JLabel(name);
		gametext.setForeground(Color.RED);
		gametext.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		gametext.setBounds(15, 6, 626, 49);
		contentPane.add(gametext);
		
		JLabel companytext = new JLabel("Company: " + company);
		companytext.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		companytext.setBounds(15, 60, 396, 40);
		contentPane.add(companytext);
		
		JLabel wikitext = new JLabel("wiki:" + wiki);
		wikitext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			         
			        Desktop.getDesktop().browse(new URI(wiki));
			         
			    } catch (IOException | URISyntaxException e1) {
			        e1.printStackTrace();
			    }
			}
		});
		wikitext.setText("<html><a href=''>Click here to visit wiki</a></html>");
		
		wikitext.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		wikitext.setBounds(15, 110, 396, 40);
		contentPane.add(wikitext);
		
		JLabel keywordtext = new JLabel("Keywords: " + keyword1 + ", " + keyword2 + ", " + keyword3);
		keywordtext.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		keywordtext.setBounds(15, 160, 459, 40);
		contentPane.add(keywordtext);
		
		JLabel gamepicture = new JLabel("");
		
		if(name.equals("Call of Duty: Modern Warfare")) {
			picture = "./pictures/callofdutypicture.jpg";
			Rec1 = new JButton("CSGO");
			newGame1 = "CSGO";
			Rec2 = new JButton("Overwatch");
			newGame2 = "Overwatch";
			Rec3 = new JButton("Fortnite");
			newGame3 = "Fortnite";
		}
		else if(name.equals("CSGO")) {
			picture = "./pictures/csgopicture.jpeg";
			Rec1 = new JButton("COD");
			newGame1 = "Call of Duty: Modern Warfare";
			Rec2 = new JButton("Overwatch");
			newGame2 = "Overwatch";
			Rec3 = new JButton("Fortnite");
			newGame3 = "Fortnite";
		}
		else if(name.equals("Minecraft")) {
			picture = "./pictures/minecraftpicture.jpeg";
			Rec1 = new JButton("Rocket League");
			newGame1 = "RocketLeague";
			Rec2 = new JButton("Fortnite");
			newGame2 = "Fortnite";
			Rec3 = new JButton("GTA");
			newGame3 = "GTA";
		}
		else if (name.equals("Fortnite")) {
			picture = "./pictures/fortnitepicture.jpg";
			Rec1 = new JButton("COD");
			newGame1 = "Call of Duty: Modern Warfare";
			Rec2 = new JButton("Minecraft");
			newGame2 = "Minecraft";
			Rec3 = new JButton("Overwatch");
			newGame3 = "Overwatch";
		}
		else if (name.equals("Overwatch")) {
			picture = "./pictures/overwatchpicture.jpg";
			Rec1 = new JButton("COD");
			newGame1 = "Call of Duty: Modern Warfare";
			Rec2 = new JButton("CSGO");
			newGame2 = "CSGO";
			Rec3 = new JButton("Destiny 2");
			newGame3 = "Destiny 2";
		}
		else if (name.equals("GTA")) {
			picture = "./pictures/gtapicture.png";
			Rec1 = new JButton("Rocket League");
			newGame1 = "RocketLeague";
			Rec2 = new JButton("COD");
			newGame2 = "Call of Duty: Modern Warfare";
			Rec3 = new JButton("Destiny 2");
			newGame3 = "Destiny 2";
		}
		else if (name.equals("RocketLeague")) {
			picture = "./pictures/rlpicture.jpg";
			Rec1 = new JButton("GTA");
			newGame1 = "GTA";
			Rec2 = new JButton("Fortnite");
			newGame2 = "Fortnite";
			Rec3 = new JButton("Minecraft");
			newGame3 = "Minecraft";
		}
		else if (name.equals("Destiny 2")) {
			picture = "./pictures/destinypicture.jpg";
			Rec1 = new JButton("Overwatch");
			newGame1 = "Overwatch";
			Rec2 = new JButton("GTA");
			newGame2 = "GTA";
			Rec3 = new JButton("Fortnite");
			newGame3 = "Fortnite";
		}
		
		gamepicture.setIcon(new ImageIcon(picture));
		gamepicture.setBounds(486, 67, 200, 283);
		contentPane.add(gamepicture);
		
		Rec1.setBounds(217, 369, 117, 29);
		contentPane.add(Rec1);
		
		Rec2.setBounds(346, 369, 117, 29);
		contentPane.add(Rec2);
		
		Rec3.setBounds(475, 369, 117, 29);
		contentPane.add(Rec3);
		
		//Rec buttons
		Rec1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				InfoWindow game = new InfoWindow(newGame1);
				game.setVisible(true);		
			}
		});
		Rec2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				InfoWindow game = new InfoWindow(newGame2);
				game.setVisible(true);
			}
		});
		Rec3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				InfoWindow game = new InfoWindow(newGame3);
				game.setVisible(true);
			}
		});
		
		JLabel lblRecommendations = new JLabel("Recommendations:");
		lblRecommendations.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblRecommendations.setBounds(15, 362, 190, 40);
		contentPane.add(lblRecommendations);
		
		CommentsButton = new JButton("Comments");
		CommentsButton.setBounds(604, 355, 97, 56);
		contentPane.add(CommentsButton);
		CommentsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CommentWindow commentWindow = new CommentWindow(name);
				commentWindow.setVisible(true);
			}
		});
	}
}
