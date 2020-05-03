import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Login {

	protected Shell shlLoginVirtubase;
	private Text username;
	private Text password;
	private Label invaliderror;
	private Button back;
	private Label usernameicon;
	private Label passwordicon;
	private static String user, pass;
	private static boolean loginStatus = false;
	private Button btnRegister;
	private Label RegisterTitle;
	private Button EnterReg;
	private static String newUsername;
	private static String newPassword;
	private Label UsernameExists;
	private Label MissingPass;
	private Label UserCharacters;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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
		shlLoginVirtubase.open();
		shlLoginVirtubase.layout();
		while (!shlLoginVirtubase.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Driver.retrieveLogin();
		
		shlLoginVirtubase = new Shell();
		shlLoginVirtubase.setSize(500, 370);
		shlLoginVirtubase.setText("Login - Virtubase Games");
		
		UserCharacters = new Label(shlLoginVirtubase, SWT.NONE);
		UserCharacters.setVisible(false);
		UserCharacters.setText("Error: Username must be atleast 3 characters.");
		UserCharacters.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		UserCharacters.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 15, SWT.ITALIC));
		UserCharacters.setAlignment(SWT.CENTER);
		UserCharacters.setBounds(0, 72, 500, 25);
		
		MissingPass = new Label(shlLoginVirtubase, SWT.NONE);
		MissingPass.setVisible(false);
		MissingPass.setText("Error: Missing password.");
		MissingPass.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		MissingPass.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 15, SWT.ITALIC));
		MissingPass.setAlignment(SWT.CENTER);
		MissingPass.setBounds(133, 72, 233, 25);
		
		UsernameExists = new Label(shlLoginVirtubase, SWT.NONE);
		UsernameExists.setVisible(false);
		UsernameExists.setText("Error: Username already exists.");
		UsernameExists.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		UsernameExists.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 15, SWT.ITALIC));
		UsernameExists.setAlignment(SWT.CENTER);
		UsernameExists.setBounds(133, 72, 233, 25);
		
		RegisterTitle = new Label(shlLoginVirtubase, SWT.NONE);
		RegisterTitle.setVisible(false);
		RegisterTitle.setText("REGISTER");
		RegisterTitle.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 35, SWT.BOLD));
		RegisterTitle.setAlignment(SWT.CENTER);
		RegisterTitle.setBounds(133, 25, 233, 57);
		
		EnterReg = new Button(shlLoginVirtubase, SWT.NONE);
		EnterReg.setVisible(false);
		EnterReg.setText("Enter");
		EnterReg.setBounds(203, 207, 94, 27);
	
		
		//Login screen
		
		username = new Text(shlLoginVirtubase, SWT.BORDER);
		username.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 20, SWT.NORMAL));
		username.setBounds(136, 105, 227, 29);
		
		password = new Text(shlLoginVirtubase, SWT.BORDER | SWT.PASSWORD);
		password.setBounds(136, 159, 227, 29);
		
		usernameicon = new Label(shlLoginVirtubase, SWT.NONE);
		usernameicon.setImage(SWTResourceManager.getImage("./pictures/usernameicon.png"));
		usernameicon.setBounds(91, 102, 35, 35);
		
		passwordicon = new Label(shlLoginVirtubase, SWT.NONE);
		passwordicon.setImage(SWTResourceManager.getImage("./pictures/passwordicon.png"));
		passwordicon.setBounds(91, 156, 35, 35);
		
		invaliderror = new Label(shlLoginVirtubase, SWT.NONE);
		invaliderror.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		invaliderror.setAlignment(SWT.CENTER);
		invaliderror.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 15, SWT.ITALIC));
		invaliderror.setBounds(133, 72, 233, 25);
		invaliderror.setText("Invalid Username or Password");
		invaliderror.setVisible(false);
		
		Button btnLogin = new Button(shlLoginVirtubase, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Driver.retrieveLogin();
				
				user = username.getText();
				pass = password.getText();
				for(int i = 0; i < Driver.loginList.size(); i++) {
					if(user.equals(Driver.loginList.get(i).getUsername()) && pass.equals(Driver.loginList.get(i).getPassword())) {
						loginStatus = true;
						Window main = new Window();
						main.open();
						shlLoginVirtubase.close();
					}
					else {
						invaliderror.setVisible(true);
					}
				}
			}
		});
		btnLogin.setBounds(146, 207, 94, 27);
		btnLogin.setText("Login");
		
		Label UserLoginTitle = new Label(shlLoginVirtubase, SWT.NONE);
		UserLoginTitle.setAlignment(SWT.CENTER);
		UserLoginTitle.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 35, SWT.BOLD));
		UserLoginTitle.setBounds(133, 25, 233, 57);
		UserLoginTitle.setText("USER LOGIN");
		
		btnRegister = new Button(shlLoginVirtubase, SWT.NONE);
		btnRegister.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				username.setVisible(true);
				password.setVisible(true);
				usernameicon.setVisible(true);
				passwordicon.setVisible(true);
				btnLogin.setVisible(false);
				btnRegister.setVisible(false);
				UserLoginTitle.setVisible(false);
				back.setVisible(false);		
				EnterReg.setVisible(true);
				RegisterTitle.setVisible(true);	
				
				EnterReg.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						boolean passes = true;
						
						Driver.retrieveLogin();
						newUsername = username.getText();
						newPassword = password.getText();
						
						//checks if user already exist
						for(int i = 0; i < Driver.loginList.size(); i++) {
							if(newUsername.equals(Driver.loginList.get(i).getUsername())) {
								UsernameExists.setVisible(true);
								UserCharacters.setVisible(false);
								MissingPass.setVisible(false);
								passes = false;
							}
						}
						
						//checks length of username
						if(newUsername.length() < 3) {
							UserCharacters.setVisible(true);
							UsernameExists.setVisible(false);
							MissingPass.setVisible(false);
							passes = false;
						}
						
						//checks if password is entered
						if(newPassword.length() < 1) {
							MissingPass.setVisible(true);
							UsernameExists.setVisible(false);
							UserCharacters.setVisible(false);
						}
						
						//update file
						if(passes) {
							Driver.writeToLogin();
							
							btnLogin.setVisible(true);
							btnRegister.setVisible(true);
							UserLoginTitle.setVisible(true);
							back.setVisible(true);
							EnterReg.setVisible(false);
							RegisterTitle.setVisible(false);
							UsernameExists.setVisible(false);
							UserCharacters.setVisible(false);
							MissingPass.setVisible(false);
							
						}	
					}
				});
			}
		});
		btnRegister.setVisible(false);
		btnRegister.setText("Register");
		btnRegister.setBounds(260, 207, 94, 27);
		
		back = new Button(shlLoginVirtubase, SWT.NONE);
		back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				user = "Guest";
				Window main = new Window();
				main.open();
				shlLoginVirtubase.close();
			}
		});
		back.setText("Launch as Guest");
		back.setBounds(160, 260, 179, 64);
		

		//Selection screen
		username.setVisible(false);
		password.setVisible(false);
		usernameicon.setVisible(false);
		passwordicon.setVisible(false);
		btnLogin.setVisible(false);
		btnRegister.setVisible(false);
		UserLoginTitle.setVisible(false);
		back.setVisible(false);
		EnterReg.setVisible(false);
		
		Button guestbutton = new Button(shlLoginVirtubase, SWT.NONE);
		guestbutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				user = "Guest";
				Window main = new Window();
				main.open();
				shlLoginVirtubase.close();
			}
		});
		guestbutton.setImage(SWTResourceManager.getImage("./pictures/guestlogin.png"));
		guestbutton.setBounds(27, 70, 180, 180);
				
		Button userbutton = new Button(shlLoginVirtubase, SWT.NONE);
		userbutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				guestbutton.setVisible(false);
				userbutton.setVisible(false);
				username.setVisible(true);
				password.setVisible(true);
				usernameicon.setVisible(true);
				passwordicon.setVisible(true);
				btnLogin.setVisible(true);
				btnRegister.setVisible(true);
				UserLoginTitle.setVisible(true);
				back.setVisible(true);	
			}
		});
		userbutton.setImage(SWTResourceManager.getImage("./pictures/userlogin.png"));
		userbutton.setBounds(267, 70, 180, 180);
				
				
		//Background
		Browser background = new Browser(shlLoginVirtubase, SWT.NONE);
		background.setUrl("https://i.pinimg.com/originals/24/22/32/24223276aba1bd1f91190a745023e469.gif");
		background.setBounds(0, -54, 500, 500);
		
	}
	
	public static boolean getLoginStatus() {
		return loginStatus;
	}
	
	public static String getUser() {
		return user;
	}

	public static String newReg() {
		String reg = newUsername + "," + newPassword;
		return reg;
	}
}
