import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Menu;

public class Window {

	protected Shell shell;
	private Text txtTextText;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(626, 415);
		shell.setText("SWT Application");
		
		Button btnTestCheckButton = new Button(shell, SWT.CHECK);
		btnTestCheckButton.setBounds(105, 158, 131, 18);
		btnTestCheckButton.setText("Test Check Button");
		
		txtTextText = new Text(shell, SWT.BORDER);
		txtTextText.setText("Text Text");
		txtTextText.setBounds(344, 158, 64, 19);
		
		DateTime dateTime = new DateTime(shell, SWT.BORDER);
		dateTime.setBounds(146, 252, 90, 27);
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		Menu menu_1 = new Menu(shell);
		shell.setMenu(menu_1);

	}
}
