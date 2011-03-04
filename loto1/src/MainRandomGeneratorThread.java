import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MainRandomGeneratorThread implements Runnable {

	private static Logger log = Logger
			.getLogger(MainRandomGeneratorThread.class);

	private int round;

	private Connection con;

	public MainRandomGeneratorThread() {
		round = getNextRound();
		log.info("start processamento do round: " + round);

	}

	public void run() {

		int bola = 1;
		while (bola <= 6) {
			log.info("iniciando coleta da bola: " + bola);
			String[] randNumbers = null;
			try {
				randNumbers = connectRandom();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			saveBola(randNumbers, bola);

			// sleep 10sec like sorteios
			try {
				Thread.currentThread();
				Thread.sleep(9250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			bola++;
		}
	}

	private int getNextRound() {
		int _round = 0;
		try {
			con = getConnection();
			Statement s = (Statement) con.createStatement();
			s.executeQuery("select max(round)+1 as max from random_org_monitor");
			ResultSet rs = s.getResultSet();

			while (rs.next()) {
				_round = rs.getInt("max");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return _round;
	}

	private void saveBola(String[] randNumbers, int bola) {

		try {
			Statement s = (Statement) con.createStatement();

			for(int i=0;i<3;i++) {
				s.executeUpdate("INSERT INTO random_org_monitor values " +
						" (" + round + ", " + bola + ", " + randNumbers[i] +
						", " + (i+1) + ", now() )");
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private String[] connectRandom() throws MalformedURLException, IOException {

		String[] randNumbers = new String[3];
		URL url;
		URLConnection urlConn;
		DataInputStream dis;

		url = new URL(
				"http://www.random.org/integers/?format=plain&num=3&min=1&max=60&col=1&base=10");

		// Note: a more portable URL:
		// url = new URL(getCodeBase().toString() + "/ToDoList/ToDoList.txt");

		urlConn = url.openConnection();
		urlConn.setDoInput(true);
		urlConn.setUseCaches(false);

		dis = new DataInputStream(urlConn.getInputStream());
		String s;
		int i = 0;
		while ((s = dis.readLine()) != null) {
			randNumbers[i] = s;
			i++;
		}
		dis.close();

		return randNumbers;
	}

	private Connection getConnection() throws Exception {
		String userName = "root";
		String password = "lacuna";
		String url = "jdbc:mysql://localhost/loto";

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		Connection conn = (Connection) DriverManager.getConnection(url,
				userName, password);

		return conn;

	}
}
