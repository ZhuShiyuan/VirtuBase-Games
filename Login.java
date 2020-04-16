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
		shlLoginVirtubase = new Shell();
		shlLoginVirtubase.setSize(500, 370);
		shlLoginVirtubase.setText("Login - Virtubase Games");
	
		
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
				if(username.getText().equals("admin") && password.getText().equals("password")) {
					Window main = new Window();
					main.open();
					shlLoginVirtubase.close();
				}
				else {
					invaliderror.setVisible(true);
				}
			}
		});
		btnLogin.setBounds(203, 207, 94, 27);
		btnLogin.setText("Login");
		
		Label title = new Label(shlLoginVirtubase, SWT.NONE);
		title.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 35, SWT.BOLD));
		title.setBounds(133, 25, 233, 57);
		title.setText("ADMIN LOGIN");
		
		back = new Button(shlLoginVirtubase, SWT.NONE);
		back.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Window main = new Window();
				main.open();
				shlLoginVirtubase.close();
			}
		});
		back.setText("Launch as User");
		back.setBounds(160, 260, 179, 64);
		

		//Selection screen
		username.setVisible(false);
		password.setVisible(false);
		usernameicon.setVisible(false);
		passwordicon.setVisible(false);
		btnLogin.setVisible(false);
		title.setVisible(false);
		back.setVisible(false);
		
		Button userbutton = new Button(shlLoginVirtubase, SWT.NONE);
		userbutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Window main = new Window();
				main.open();
				shlLoginVirtubase.close();
			}
		});
		userbutton.setImage(SWTResourceManager.getImage("./pictures/userlogin.png"));
		userbutton.setBounds(27, 70, 180, 180);
				
		Button adminbutton = new Button(shlLoginVirtubase, SWT.NONE);
		adminbutton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userbutton.setVisible(false);
				adminbutton.setVisible(false);
				username.setVisible(true);
				password.setVisible(true);
				usernameicon.setVisible(true);
				passwordicon.setVisible(true);
				btnLogin.setVisible(true);
				title.setVisible(true);
				back.setVisible(true);
						
						
			}
		});
		adminbutton.setImage(SWTResourceManager.getImage("./pictures/adminlogin.png"));
		adminbutton.setBounds(267, 70, 180, 180);
				
				
		//Background
		Browser background = new Browser(shlLoginVirtubase, SWT.NONE);
		background.setUrl("https://i.pinimg.com/originals/24/22/32/24223276aba1bd1f91190a745023e469.gif");
		background.setBounds(0, -54, 500, 500);
	}
}
