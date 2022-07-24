package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import vagas.model.Login;

public class ConexaoBD {
	private String Driver = "com.mysql.cj.jdbc.Driver";

	private String url = "jdbc:mysql://127.0.0.1:3306/contatos?useTimezone=true&serverTimezone=UTC";

	private String user = "root";

	private String password = " ";

	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(Driver);

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vagasdeemprego", "root", "");
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

	 //método para fazer a conexão com banco de dados
	public void conectaBd() {

		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	 //método para adicionar os dados no banco de dados
 	public void adicionarLogin(Login lg) {

		String sql = "INSERT INTO login(email,senha)values(?,?)";
		try {
			Connection con = conectar();
			PreparedStatement patm = con.prepareStatement(sql);
			patm.setString(1, lg.getEmail());
			patm.setString(2, lg.getSenha());
		

			patm.executeUpdate();
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

