package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;

public class Example {
	@FXML
	private Button carregar_comandes;
	@FXML
	private ListView llista_comandes;
	Connection conect = null;

	public void initialize(URL location, ResourceBundle resources) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conect = DriverManager.getConnection("jdbc:mysql://192.168.88.106/SunDos", "Sun", "Dos");
			String sentencia = "SELECT comanda_id, usuari_id, producte_id_id, quantitat,"
					+ " preu, estat FROM carts_comanda, carts_linia WHERE carts_comanda.comanda_id"
					+ " = carts_linia.comanda_id_id AND estat = 'T'; ";
			Statement peticio = conect.createStatement();
			ResultSet resultat = peticio.executeQuery(sentencia);
			ObservableList<String> comandesList = FXCollections.observableArrayList();
			while (resultat.next()) {
				String cmd_id = resultat.getString("comanda_id");
				String usr_id = resultat.getString("usuari_id");
				String pdt_id = resultat.getString("producte_id_id");
				String qnt = resultat.getString("quantitat");
				String pre = resultat.getString("preu");
				String est = resultat.getString("estat");
				comandesList.addAll(cmd_id, usr_id, pdt_id, qnt, pre, est);
			}
			llista_comandes.setItems(comandesList);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Event Listener on ListView[#llista_comandes].onMouseClicked
	@FXML
	public void canviar_estat(MouseEvent event) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conect = DriverManager.getConnection("jdbc:mysql://MYIP/BDD", "Sun", "Dos");
			String sentencia = "UPDATE carts_comanda SET estat='E' WHERE comanda_id = IDdelclic;";
			Statement peticio = conect.createStatement();
			ResultSet resultat = peticio.executeQuery(sentencia);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
