import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

import java.awt.Color;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.SelectionListener;
import java.util.function.Consumer;

public class Window {

	protected Shell shlVirtue;
	private Text searchBox;
	private boolean edit = false;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Window window = new Window();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlVirtue.open();
		shlVirtue.layout();
		while (!shlVirtue.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Driver.retrieveGamesList();
		
		shlVirtue = new Shell();
		shlVirtue.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlVirtue.setSize(1450, 825);
		shlVirtue.setText("VirtuBase Games");
		shlVirtue.setLayout(null);
		
		Button RequestGame = new Button(shlVirtue, SWT.NONE);
		RequestGame.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RequestGame request = new RequestGame();
				request.open();
			}
		});
		
		RequestGame.setText("Request");
		RequestGame.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		RequestGame.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.BOLD));
		RequestGame.setBounds(50, 23, 94, 47);
		RequestGame.setVisible(true);
		
		
		Button DeletedComments = new Button(shlVirtue, SWT.NONE);
		DeletedComments.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.BOLD));
		DeletedComments.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		DeletedComments.setBounds(50, 23, 94, 47);
		DeletedComments.setText("Deleted");
		DeletedComments.setVisible(false);
		DeletedComments.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CommentWindow deletedComments = new CommentWindow("DELETED");
				deletedComments.setVisible(true);
			}
		});
		
		Button DoneButton = new Button(shlVirtue, SWT.NONE);
		DoneButton.setBounds(1353, 47, 60, 27);
		DoneButton.setText("Done");
		DoneButton.setVisible(false);
		
		Button EditButton = new Button(shlVirtue, SWT.NONE);
		EditButton.setBounds(1360, 47, 53, 27);
		EditButton.setText("Edit");
		EditButton.setVisible(false);
		
		Label UserDisplay = new Label(shlVirtue, SWT.NONE);
		UserDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		UserDisplay.setAlignment(SWT.RIGHT);
		if(Login.getUser().equals("Guest")) {
			UserDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		}
		if(Login.getUser().equals("admin")) {
			UserDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
		else if (Login.getUser().equals("moderator")) {
			UserDisplay.setForeground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		}
		UserDisplay.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 25, SWT.BOLD));
		UserDisplay.setBounds(1009, 10, 404, 34);
		UserDisplay.setText(Login.getUser());
		UserDisplay.setVisible(true);
		
		Label GameDatabaseTitle = new Label(shlVirtue, SWT.NONE);
		GameDatabaseTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		GameDatabaseTitle.setAlignment(SWT.CENTER);
		GameDatabaseTitle.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 50, SWT.NORMAL));
		GameDatabaseTitle.setBounds(0, 10, 1450, 64);
		GameDatabaseTitle.setText("Game Database");
		
		Browser CODpicture = new Browser(shlVirtue, SWT.NONE);
		CODpicture.setUrl("https://savegame.pro/wp-content/uploads/2020/01/call-of-duty-modern-warfare-cover.jpg");
		CODpicture.setBounds(50, 140, 300, 274);
		CODpicture.setVisible(Driver.gameList.get(0).getVisible());
		
		Browser CSGOpicture = new Browser(shlVirtue, SWT.NONE);
		CSGOpicture.setUrl("https://www.allkeyshop.com/blog/wp-content/uploads/buy-csgo-1-premium-random-skin-cd-key-pc-download-img1.jpg");
		CSGOpicture.setBounds(400, 140, 300, 274);
		CSGOpicture.setVisible(Driver.gameList.get(1).getVisible());
		
		Button Game1 = new Button(shlVirtue, SWT.NONE);
		Game1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("Call of Duty: Modern Warfare");
				game.setVisible(true);
			}
		});
		Game1.setBounds(148, 425, 105, 27);
		Game1.setText("Learn more");
		Game1.setVisible(Driver.gameList.get(0).getVisible());
		
		searchBox = new Text(shlVirtue, SWT.BORDER);
		searchBox.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 19, SWT.NORMAL));
		searchBox.setBounds(445, 80, 284, 34);
		
		Button CheckShooter = new Button(shlVirtue, SWT.CHECK);
		CheckShooter.setSelection(true);
		CheckShooter.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		CheckShooter.setBounds(750, 80, 77, 34);
		CheckShooter.setText("Shooter");
		
		Button CheckRacing = new Button(shlVirtue, SWT.CHECK);
		CheckRacing.setSelection(true);
		CheckRacing.setBounds(833, 80, 65, 34);
		CheckRacing.setText("Racing");
		
		Label Searchlabel = new Label(shlVirtue, SWT.NONE);
		Searchlabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		Searchlabel.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 20, SWT.NORMAL));
		Searchlabel.setBounds(374, 87, 65, 27);
		Searchlabel.setText("Search");
		
		Browser Fortnitepicture = new Browser(shlVirtue, SWT.NONE);
		Fortnitepicture.setUrl("https://i.scdn.co/image/ab67706c0000da84263a9a46b6093120c513c7aa");
		Fortnitepicture.setBounds(1100, 140, 300, 274);
		Fortnitepicture.setVisible(Driver.gameList.get(3).getVisible());
		
		Browser Minecraftpicture = new Browser(shlVirtue, SWT.NONE);
		Minecraftpicture.setUrl("https://toptechinspector.com/wp-content/uploads/2019/08/minecraft-ava-300x300.jpg");
		Minecraftpicture.setBounds(750, 140, 300, 274);
		Minecraftpicture.setVisible(Driver.gameList.get(2).getVisible());
		
		Browser Overwatchpicture = new Browser(shlVirtue, SWT.NONE);
		Overwatchpicture.setUrl("https://eu.battle.net/support/static/images/games/tile_overwatch.227b2450.jpg");
		Overwatchpicture.setBounds(50, 460, 300, 274);
		Overwatchpicture.setVisible(Driver.gameList.get(4).getVisible());
		
		Browser GTApicture = new Browser(shlVirtue, SWT.NONE);
		GTApicture.setUrl("https://lunasmods.com/wp-content/uploads/2019/02/grand-theft-auto-v-1-300x300.jpg");
		GTApicture.setBounds(400, 460, 300, 274);
		GTApicture.setVisible(Driver.gameList.get(5).getVisible());
		
		Browser Rlpicture = new Browser(shlVirtue, SWT.NONE);
		Rlpicture.setUrl("https://static-cdn.jtvnw.net/jtv_user_pictures/d2f4517c-b867-40d1-bf36-48de9146e966-profile_image-300x300.png");
		Rlpicture.setBounds(750, 460, 300, 274);
		Rlpicture.setVisible(Driver.gameList.get(6).getVisible());
		
		Browser Destinypicture = new Browser(shlVirtue, SWT.NONE);
		Destinypicture.setUrl("https://uploads.mixer.com/cover/b39904b13f1012ed8671.jpg?rev=3");
		Destinypicture.setBounds(1100, 460, 300, 274);
		Destinypicture.setVisible(Driver.gameList.get(7).getVisible());
		
		Button Game2 = new Button(shlVirtue, SWT.NONE);
		Game2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("CSGO");
				game.setVisible(true);
			}
		});
		Game2.setText("Learn more");
		Game2.setBounds(498, 425, 105, 27);
		Game2.setVisible(Driver.gameList.get(1).getVisible());
		
		Button Game3 = new Button(shlVirtue, SWT.NONE);
		Game3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("Minecraft");
				game.setVisible(true);
			}
		});
		Game3.setText("Learn more");
		Game3.setBounds(848, 425, 105, 27);
		Game3.setVisible(Driver.gameList.get(2).getVisible());
		
		Button Game4 = new Button(shlVirtue, SWT.NONE);
		Game4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("Fortnite");
				game.setVisible(true);
			}
		});
		Game4.setText("Learn more");
		Game4.setBounds(1198, 425, 105, 27);
		Game4.setVisible(Driver.gameList.get(3).getVisible());
		
		Button Game8 = new Button(shlVirtue, SWT.NONE);
		Game8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("Destiny 2");
				game.setVisible(true);
			}
		});
		Game8.setText("Learn more");
		Game8.setBounds(1198, 745, 105, 27);
		Game8.setVisible(Driver.gameList.get(7).getVisible());
		
		Button Game7 = new Button(shlVirtue, SWT.NONE);
		Game7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("RocketLeague");
				game.setVisible(true);
			}
		});
		Game7.setText("Learn more");
		Game7.setBounds(848, 745, 105, 27);
		Game7.setVisible(Driver.gameList.get(6).getVisible());
		
		Button Game6 = new Button(shlVirtue, SWT.NONE);
		Game6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("GTA");
				game.setVisible(true);
			}
		});
		Game6.setText("Learn more");
		Game6.setBounds(498, 745, 105, 27);
		Game6.setVisible(Driver.gameList.get(5).getVisible());
		
		Button Game5 = new Button(shlVirtue, SWT.NONE);
		Game5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InfoWindow game = new InfoWindow("Overwatch");
				game.setVisible(true);
			}
		});
		Game5.setText("Learn more");
		Game5.setBounds(148, 745, 105, 27);
		Game5.setVisible(Driver.gameList.get(4).getVisible());
		
		Button CheckOW = new Button(shlVirtue, SWT.CHECK);
		CheckOW.setText("Open-World");
		CheckOW.setSelection(true);
		CheckOW.setBounds(904, 80, 99, 34);
		
		Button btnSearch = new Button(shlVirtue, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean f = false;
				boolean t = true;
				
				if(getSearch().equals("")) {
					if(CheckShooter.getSelection() == false && CheckRacing.getSelection() == false && CheckOW.getSelection() == false) {
						CODpicture.setVisible(f);
						Game1.setVisible(f);
						CSGOpicture.setVisible(f);
						Game2.setVisible(f);
						Minecraftpicture.setVisible(f);
						Game3.setVisible(f);
						Fortnitepicture.setVisible(f);
						Game4.setVisible(f);
						Overwatchpicture.setVisible(f);
						Game5.setVisible(f);
						GTApicture.setVisible(f);
						Game6.setVisible(f);
						Rlpicture.setVisible(f);
						Game7.setVisible(f);
						Destinypicture.setVisible(f);
						Game8.setVisible(f);
					}
					if(CheckShooter.getSelection() == false && CheckRacing.getSelection() == false  && CheckOW.getSelection() == true) {
						CODpicture.setVisible(f);
						Game1.setVisible(f);
						CSGOpicture.setVisible(f);
						Game2.setVisible(f);
						if(Driver.gameList.get(2).getVisible()) Minecraftpicture.setVisible(t);
						if(Driver.gameList.get(2).getVisible())Game3.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Fortnitepicture.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Game4.setVisible(t);
						Overwatchpicture.setVisible(f);
						Game5.setVisible(f);
						if(Driver.gameList.get(5).getVisible())GTApicture.setVisible(t);
						if(Driver.gameList.get(5).getVisible())Game6.setVisible(t);
						Rlpicture.setVisible(f);
						Game7.setVisible(f);
						if(Driver.gameList.get(7).getVisible())Destinypicture.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Game8.setVisible(t);
					}
					if(CheckShooter.getSelection() == false && CheckRacing.getSelection() == true  && CheckOW.getSelection() == true) {
						CODpicture.setVisible(f);
						Game1.setVisible(f);
						CSGOpicture.setVisible(f);
						Game2.setVisible(f);
						if(Driver.gameList.get(2).getVisible())Minecraftpicture.setVisible(t);
						if(Driver.gameList.get(2).getVisible())Game3.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Fortnitepicture.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Game4.setVisible(t);
						Overwatchpicture.setVisible(f);
						Game5.setVisible(f);
						if(Driver.gameList.get(5).getVisible())GTApicture.setVisible(t);
						if(Driver.gameList.get(5).getVisible())Game6.setVisible(t);
						if(Driver.gameList.get(6).getVisible())Rlpicture.setVisible(t);
						if(Driver.gameList.get(6).getVisible())Game7.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Destinypicture.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Game8.setVisible(t);
					}
					if(CheckShooter.getSelection() == false && CheckRacing.getSelection() == true  && CheckOW.getSelection() == false) {
						CODpicture.setVisible(f);
						Game1.setVisible(f);
						CSGOpicture.setVisible(f);
						Game2.setVisible(f);
						Minecraftpicture.setVisible(f);
						Game3.setVisible(f);
						Fortnitepicture.setVisible(f);
						Game4.setVisible(f);
						Overwatchpicture.setVisible(f);
						Game5.setVisible(f);
						if(Driver.gameList.get(5).getVisible())GTApicture.setVisible(t);
						if(Driver.gameList.get(5).getVisible())Game6.setVisible(t);
						if(Driver.gameList.get(6).getVisible())Rlpicture.setVisible(t);
						if(Driver.gameList.get(6).getVisible())Game7.setVisible(t);
						Destinypicture.setVisible(f);
						Game8.setVisible(f);
					}
					if(CheckShooter.getSelection() == true && CheckRacing.getSelection() == false  && CheckOW.getSelection() == true) {
						if(Driver.gameList.get(0).getVisible())CODpicture.setVisible(t);
						if(Driver.gameList.get(0).getVisible())Game1.setVisible(t);
						if(Driver.gameList.get(1).getVisible())CSGOpicture.setVisible(t);
						if(Driver.gameList.get(1).getVisible())Game2.setVisible(t);
						if(Driver.gameList.get(2).getVisible())Minecraftpicture.setVisible(t);
						if(Driver.gameList.get(2).getVisible())Game3.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Fortnitepicture.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Game4.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Overwatchpicture.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Game5.setVisible(t);
						if(Driver.gameList.get(5).getVisible())GTApicture.setVisible(t);
						if(Driver.gameList.get(5).getVisible())Game6.setVisible(t);
						Rlpicture.setVisible(f);
						Game7.setVisible(f);
						if(Driver.gameList.get(7).getVisible())Destinypicture.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Game8.setVisible(t);
					}
					if(CheckShooter.getSelection() == true && CheckRacing.getSelection() == true  && CheckOW.getSelection() == false) {
						if(Driver.gameList.get(0).getVisible())CODpicture.setVisible(t);
						if(Driver.gameList.get(0).getVisible())Game1.setVisible(t);
						if(Driver.gameList.get(1).getVisible())CSGOpicture.setVisible(t);
						if(Driver.gameList.get(1).getVisible())Game2.setVisible(t);
						Minecraftpicture.setVisible(f);
						Game3.setVisible(f);
						if(Driver.gameList.get(3).getVisible())Fortnitepicture.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Game4.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Overwatchpicture.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Game5.setVisible(t);
						if(Driver.gameList.get(5).getVisible())GTApicture.setVisible(t);
						if(Driver.gameList.get(5).getVisible())Game6.setVisible(t);
						if(Driver.gameList.get(6).getVisible())Rlpicture.setVisible(t);
						if(Driver.gameList.get(6).getVisible())Game7.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Destinypicture.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Game8.setVisible(t);
					}
					if(CheckShooter.getSelection() == true && CheckRacing.getSelection() == true  && CheckOW.getSelection() == true) {
						if(Driver.gameList.get(0).getVisible())CODpicture.setVisible(t);
						if(Driver.gameList.get(0).getVisible())Game1.setVisible(t);
						if(Driver.gameList.get(1).getVisible())CSGOpicture.setVisible(t);
						if(Driver.gameList.get(1).getVisible())Game2.setVisible(t);
						if(Driver.gameList.get(2).getVisible())Minecraftpicture.setVisible(t);
						if(Driver.gameList.get(2).getVisible())Game3.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Fortnitepicture.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Game4.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Overwatchpicture.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Game5.setVisible(t);
						if(Driver.gameList.get(5).getVisible())GTApicture.setVisible(t);
						if(Driver.gameList.get(5).getVisible())Game6.setVisible(t);
						if(Driver.gameList.get(6).getVisible())Rlpicture.setVisible(f);
						if(Driver.gameList.get(6).getVisible())Game7.setVisible(f);
						if(Driver.gameList.get(7).getVisible())Destinypicture.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Game8.setVisible(t);
					}
					if(CheckShooter.getSelection() == true && CheckRacing.getSelection() == false  && CheckOW.getSelection() == false) {
						if(Driver.gameList.get(0).getVisible())CODpicture.setVisible(t);
						if(Driver.gameList.get(0).getVisible())Game1.setVisible(t);
						if(Driver.gameList.get(1).getVisible())CSGOpicture.setVisible(t);
						if(Driver.gameList.get(1).getVisible())Game2.setVisible(t);
						Minecraftpicture.setVisible(f);
						Game3.setVisible(f);
						if(Driver.gameList.get(3).getVisible())Fortnitepicture.setVisible(t);
						if(Driver.gameList.get(3).getVisible())Game4.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Overwatchpicture.setVisible(t);
						if(Driver.gameList.get(4).getVisible())Game5.setVisible(t);
						if(Driver.gameList.get(5).getVisible())GTApicture.setVisible(t);
						if(Driver.gameList.get(5).getVisible())Game6.setVisible(t);
						Rlpicture.setVisible(f);
						Game7.setVisible(f);
						if(Driver.gameList.get(7).getVisible())Destinypicture.setVisible(t);
						if(Driver.gameList.get(7).getVisible())Game8.setVisible(t);
					}
				}
				//If searched
				else {
					
					CODpicture.setVisible(f);
					Game1.setVisible(f);
					CSGOpicture.setVisible(f);
					Game2.setVisible(f);
					Minecraftpicture.setVisible(f);
					Game3.setVisible(f);
					Fortnitepicture.setVisible(f);
					Game4.setVisible(f);
					Overwatchpicture.setVisible(f);
					Game5.setVisible(f);
					GTApicture.setVisible(f);
					Game6.setVisible(f);
					Rlpicture.setVisible(f);
					Game7.setVisible(f);
					Destinypicture.setVisible(f);
					Game8.setVisible(f);
					
					if(search("call of duty: modern warfare cod")) {
						if(Driver.gameList.get(0).getVisible()) {
							CODpicture.setVisible(t);
							Game1.setVisible(t);
						}
					}
					
					if(search("csgo counter strike global offensive")) {
						if(Driver.gameList.get(1).getVisible()) {
							CSGOpicture.setVisible(t);
							Game2.setVisible(t);
						}
					}
					
					if(search("minecraft")) {
						if(Driver.gameList.get(2).getVisible()) {
							Minecraftpicture.setVisible(t);
							Game3.setVisible(t);
						}
					}
					
					if(search("fortnite battle royale")) {
						if(Driver.gameList.get(3).getVisible()) {
							Fortnitepicture.setVisible(t);
							Game4.setVisible(t);
						}
					}
					
					if(search("overwatch")) {
						if(Driver.gameList.get(4).getVisible()) {
							Overwatchpicture.setVisible(t);
							Game5.setVisible(t);
						}
					}
					
					if(search("gta grand theft auto")) {
						if(Driver.gameList.get(5).getVisible()) {
							GTApicture.setVisible(t);
							Game6.setVisible(t);
						}
					}
					
					if(search("rl rocketleague rocket league")) {
						if(Driver.gameList.get(6).getVisible()) {
							Rlpicture.setVisible(t);
							Game7.setVisible(t);
						}
					}
					
					if(search("destiny 2")) {
						if(Driver.gameList.get(7).getVisible()) {
							Destinypicture.setVisible(t);
							Game8.setVisible(t);
						}
					}
				}
			}
		});
		btnSearch.setBounds(1009, 85, 94, 27);
		btnSearch.setText("Search");
		
		Label Game1Eye = new Label(shlVirtue, SWT.NONE);
		Game1Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game1.getVisible() == true) {
					Game1.setVisible(false);
					CODpicture.setVisible(false);
					Driver.gameList.get(0).setVisible(0);
				} else {
					Game1.setVisible(true);
					CODpicture.setVisible(true);
					Driver.gameList.get(0).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game1Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game1Eye.setBounds(356, 134, 27, 27);			
		Label Game2Eye = new Label(shlVirtue, SWT.NONE);
		Game2Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game2.getVisible() == true) {
					Game2.setVisible(false);
					CSGOpicture.setVisible(false);
					Driver.gameList.get(1).setVisible(0);
				} else {
					Game2.setVisible(true);
					CSGOpicture.setVisible(true);
					Driver.gameList.get(1).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game2Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game2Eye.setBounds(706, 134, 27, 27);
		Label Game3Eye = new Label(shlVirtue, SWT.NONE);
		Game3Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game3.getVisible() == true) {
					Game3.setVisible(false);
					Minecraftpicture.setVisible(false);
					Driver.gameList.get(2).setVisible(0);
				} else {
					Game3.setVisible(true);
					Minecraftpicture.setVisible(true);
					Driver.gameList.get(2).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game3Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game3Eye.setBounds(1056, 134, 27, 27);
		
		Label Game4Eye = new Label(shlVirtue, SWT.NONE);
		Game4Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game4.getVisible() == true) {
					Game4.setVisible(false);
					Fortnitepicture.setVisible(false);
					Driver.gameList.get(3).setVisible(0);
				} else {
					Game4.setVisible(true);
					Fortnitepicture.setVisible(true);
					Driver.gameList.get(3).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game4Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game4Eye.setBounds(1406, 134, 27, 27);
		
		Label Game7Eye = new Label(shlVirtue, SWT.NONE);
		Game7Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game7.getVisible() == true) {
					Game7.setVisible(false);
					Rlpicture.setVisible(false);
					Driver.gameList.get(6).setVisible(0);
				} else {
					Game7.setVisible(true);
					Rlpicture.setVisible(true);
					Driver.gameList.get(6).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game7Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game7Eye.setBounds(1056, 454, 27, 27);
		
		Label Game8Eye = new Label(shlVirtue, SWT.NONE);
		Game8Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game8.getVisible() == true) {
					Game8.setVisible(false);
					Destinypicture.setVisible(false);
					Driver.gameList.get(7).setVisible(0);
				} else {
					Game8.setVisible(true);
					Destinypicture.setVisible(true);
					Driver.gameList.get(7).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game8Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game8Eye.setBounds(1406, 454, 27, 27);
		
		Label Game6Eye = new Label(shlVirtue, SWT.NONE);
		Game6Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game6.getVisible() == true) {
					Game6.setVisible(false);
					GTApicture.setVisible(false);
					Driver.gameList.get(5).setVisible(0);
				} else {
					Game6.setVisible(true);
					GTApicture.setVisible(true);
					Driver.gameList.get(5).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game6Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game6Eye.setBounds(706, 454, 27, 27);
		
		Label Game5Eye = new Label(shlVirtue, SWT.NONE);
		Game5Eye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if(Game5.getVisible() == true) {
					Game5.setVisible(false);
					Overwatchpicture.setVisible(false);
					Driver.gameList.get(4).setVisible(0);
				} else {
					Game5.setVisible(true);
					Overwatchpicture.setVisible(true);
					Driver.gameList.get(4).setVisible(1);
				}
				Driver.writeToDatabase();
			}
		});
		Game5Eye.setImage(SWTResourceManager.getImage("./pictures/eyeicon.png"));
		Game5Eye.setBounds(356, 454, 27, 27);
		
		Game1Eye.setVisible(false);
		Game2Eye.setVisible(false);
		Game3Eye.setVisible(false);
		Game4Eye.setVisible(false);
		Game5Eye.setVisible(false);
		Game6Eye.setVisible(false);
		Game7Eye.setVisible(false);
		Game8Eye.setVisible(false);
		
		if(Login.getUser().equals("moderator")) {
			DeletedComments.setVisible(true);
			RequestGame.setVisible(false);
		}
		
		if(Login.getUser().equals("admin")) {
			EditButton.setVisible(true);
			DeletedComments.setVisible(true);
			RequestGame.setVisible(false);
			EditButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					EditButton.setVisible(false);
					DoneButton.setVisible(true);
					
					Game1Eye.setVisible(true);
					Game2Eye.setVisible(true);
					Game3Eye.setVisible(true);
					Game4Eye.setVisible(true);
					Game5Eye.setVisible(true);
					Game6Eye.setVisible(true);
					Game7Eye.setVisible(true);
					Game8Eye.setVisible(true);
				}
				
			});
			DoneButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					DoneButton.setVisible(false);
					EditButton.setVisible(true);
					
					Game1Eye.setVisible(false);
					Game2Eye.setVisible(false);
					Game3Eye.setVisible(false);
					Game4Eye.setVisible(false);
					Game5Eye.setVisible(false);
					Game6Eye.setVisible(false);
					Game7Eye.setVisible(false);
					Game8Eye.setVisible(false);
				}
			});
		}
		
	}
	
	public String getSearch() {
		return searchBox.getText().toLowerCase();
	}

	public boolean search(String titleWords) {
		return titleWords.contains(getSearch());
	}
}
