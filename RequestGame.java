import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.SWT;

public class RequestGame {

	protected Shell shlVirtubaseGames;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RequestGame window = new RequestGame();
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
		shlVirtubaseGames.open();
		shlVirtubaseGames.layout();
		while (!shlVirtubaseGames.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlVirtubaseGames = new Shell();
		shlVirtubaseGames.setSize(622, 476);
		shlVirtubaseGames.setText("Virtubase Games - Request");
		shlVirtubaseGames.setLayout(null);
		
		Browser browser = new Browser(shlVirtubaseGames, SWT.NONE);
		browser.setUrl("https://forms.gle/H3NKxbYRryjAtBcs6");
		browser.setBounds(0, 0, 622, 454);

	}
}
