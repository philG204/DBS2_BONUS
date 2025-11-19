
import javax.swing.SwingUtilities;

import gui.SearchMovieDialog;
import gui.SearchMovieDialogCallback;
import javafx.application.Application;
import util.DBConnection;

import java.sql.SQLException;

public class Starter {

	/**
	 * @param args command line arguments (none)
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            try {
                new Starter().run();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
	}
	
	public void run() throws SQLException {
        try {
            DBConnection.open();
        } catch (Exception e) {
            e.printStackTrace();
            DBConnection.closeConnection();
        }
        SearchMovieDialogCallback callback = new SearchMovieDialogCallback();
		SearchMovieDialog sd = new SearchMovieDialog(callback);
		sd.setVisible(true);
	}
}
