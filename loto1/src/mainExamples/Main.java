package mainExamples;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Main {

	private static Connection conn = null;

	public static void main(String[] args) throws Exception {
		String userName = "root";
		String password = "lacuna";
		String url = "jdbc:mysql://localhost/loto";

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		conn = (Connection) DriverManager
				.getConnection(url, userName, password);

		System.out.println("Database connection established");

		Statement s = (Statement) conn.createStatement();

//		s.executeQuery("SELECT * FROM random2_c ");
		
		s.executeQuery("select * from sorteios_grouped ");
		

		ResultSet rs = s.getResultSet();
		int count = 0;
		while (rs.next()) {
//			int codigo = rs.getInt("codigo");
//			int numero = rs.getInt("numero");
			
			int codigo = rs.getInt("concurso");
			int numero = rs.getInt("numero");

//			System.out.println(codigo + ":" + numero);
			
			Statement s1 = (Statement) conn.createStatement();
//			s1.executeUpdate("insert into random2_d values" +
//					"("+codigo+", "+numero+", "+getQuadrante(numero)+") ");
			
			s1.executeUpdate("insert into sorteios_grouped_quadrante values" +
					"("+codigo+", "+numero+", "+getQuadrante(numero)+") ");

		}

	}

	public static int getQuadrante(int numero) {
		int quadrante = 0;

		if ((numero >= 1 && numero <= 5) || (numero >= 11 && numero <= 15)
				|| (numero >= 21 && numero <= 25)) {
			quadrante = 1;
		}

		if ((numero >= 6 && numero <= 10) || (numero >= 16 && numero <= 20)
				|| (numero >= 26 && numero <= 30)) {
			quadrante = 2;
		}

		if ((numero >= 31 && numero <= 35) || (numero >= 41 && numero <= 45)
				|| (numero >= 51 && numero <= 55)) {
			quadrante = 3;
		}

		if ((numero >= 36 && numero <= 40) || (numero >= 46 && numero <= 50)
				|| (numero >= 56 && numero <= 60)) {
			quadrante = 4;
		}

		return quadrante;
	}

}
