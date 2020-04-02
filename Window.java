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

public class Window {

	protected Shell shlVirtue;
	private Text txtGameDatabase;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

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
		shlVirtue = new Shell();
		shlVirtue.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shlVirtue.setSize(1300, 800);
		shlVirtue.setText("VirtuBase Games");
		shlVirtue.setLayout(null);
		
		txtGameDatabase = new Text(shlVirtue, SWT.READ_ONLY | SWT.CENTER);
		txtGameDatabase.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		txtGameDatabase.setBounds(484, 22, 354, 74);
		txtGameDatabase.setEditable(false);
		txtGameDatabase.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		txtGameDatabase.setFont(SWTResourceManager.getFont("Georgia", 50, SWT.NORMAL));
		txtGameDatabase.setText("Game Database");
		
		Browser browser = new Browser(shlVirtue, SWT.NONE);
		browser.setUrl("https://upload.wikimedia.org/wikipedia/en/e/e9/CallofDutyModernWarfare%282019%29.jpg");
		browser.setBounds(51, 170, 272, 274);
		formToolkit.adapt(browser);
		formToolkit.paintBordersFor(browser);
		
		Browser browser_1 = new Browser(shlVirtue, SWT.NONE);
		browser_1.setUrl("https://haste.net/wp-content/uploads/2017/11/Haste-csgo-cover.jpg");
		browser_1.setBounds(387, 170, 272, 274);
		formToolkit.adapt(browser_1);
		formToolkit.paintBordersFor(browser_1);
		
		Browser browser_2 = new Browser(shlVirtue, SWT.NONE);
		browser_2.setUrl("https://upload.wikimedia.org/wikipedia/en/e/e9/CallofDutyModernWarfare%282019%29.jpg");
		browser_2.setBounds(734, 170, 272, 274);
		formToolkit.adapt(browser_2);
		formToolkit.paintBordersFor(browser_2);
		
		Button btnLearnMore = new Button(shlVirtue, SWT.NONE);
		btnLearnMore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				CallOfDuty game = new CallOfDuty();
				game.setVisible(true);
			}
		});
		btnLearnMore.setBounds(132, 460, 105, 27);
		formToolkit.adapt(btnLearnMore, true, true);
		btnLearnMore.setText("Learn more");
		
	}
}
