
import javax.swing.SwingUtilities;

import gui.SearchMovieDialog;
import gui.SearchMovieDialogCallback;
import util.DBConnection;

public class Starter {

	/**
	 * @param args command line arguments (none)
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Starter().run());
	}
	
	public void run() {
        try {
            DBConnection.open();
        } catch (Exception e) {
            e.printStackTrace();
			System.exit(1);
        }
        SearchMovieDialogCallback callback = new SearchMovieDialogCallback();
		SearchMovieDialog sd = new SearchMovieDialog(callback);
		sd.setVisible(true);
	}
}
