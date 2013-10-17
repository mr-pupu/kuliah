package ziez.infocul;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ziez.infocul.view.MainFrame;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class InfoCul  {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException 
				| InstantiationException | IllegalAccessException e) {
		
		} 
		
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
		
			@Override
			public void run() {
				new MainFrame();
				
			}
		});
		
		NativeInterface.runEventPump();

	}

}
