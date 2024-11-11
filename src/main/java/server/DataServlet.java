package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6107593528220538922L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!resp.containsHeader("Access-Control-Allow-Origin")) {
			resp.addHeader("Access-Control-Allow-Origin", "*");
			System.out.println("Access-Control-Allow-Origin header added");
		} else {
			System.out.println("Access-Control-Allow-Origin header already present");
		}
		
		resp.setContentType("application/json");
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.getWriter().write("[{\"board_game_id\":1,\"name\":\"Arkham Horror (Third Edition)\",\"release_year\":2018,\"number_of_players\":\"1-6\",\"age\":\"14+\",\"playing_time\":\"120-180 minutes\",\"franchise\":{\"name\":\"Arkham Horror\"},\"publisher\":{\"name\":\"Fantasy Flight Games\"},\"designers\":[{\"name\":\"Nikki\",\"surname\":\"Valens\"}, {\"name\":\"Richard\",\"surname\":\"Launius\"}, {\"name\":\"Kevin\",\"surname\":\"Wilson\"}],\"artists\":[{\"name\":\"Justin\",\"surname\":\"Adams\"}, {\"name\":\"W.T.\",\"surname\":\"Arnold\"}, {\"name\":\"Anders\",\"surname\":\"Finér\"}]}, {\"board_game_id\":5,\"name\":\"Ticket to Ride: Europe\",\"release_year\":2005,\"number_of_players\":\"2-5\",\"age\":\"8+\",\"playing_time\":\"30-60 minutes\",\"franchise\":{\"name\":\"Ticket to Ride\"},\"publisher\":{\"name\":\"Days of Wonder\"},\"designers\":[{\"name\":\"Alan R.\",\"surname\":\"Moon\"}],\"artists\":[{\"name\":\"Cyrille\",\"surname\":\"Daujean\"}, {\"name\":\"Julien\",\"surname\":\"Delval\"}]}, {\"board_game_id\":9,\"name\":\"Ticket to Ride\",\"release_year\":2004,\"number_of_players\":\"2-5\",\"age\":\"8+\",\"playing_time\":\"30-60 minutes\",\"franchise\":{\"name\":\"Ticket to Ride\"},\"publisher\":{\"name\":\"Days of Wonder\"},\"designers\":[{\"name\":\"Alan R.\",\"surname\":\"Moon\"}],\"artists\":[{\"name\":\"Cyrille\",\"surname\":\"Daujean\"}, {\"name\":\"Julien\",\"surname\":\"Delval\"}]}, {\"board_game_id\":10,\"name\":\"Magic: The Gathering - Kaladesh\",\"release_year\":2016,\"number_of_players\":\"2+\",\"age\":\"13+\",\"playing_time\":\"60 minutes\",\"franchise\":{\"name\":\"Magic: The Gathering\"},\"publisher\":{\"name\":\"Wizards of the Coast\"},\"designers\":[{\"name\":\"Richard\",\"surname\":\"Garfield\"}, {\"name\":\"Ben\",\"surname\":\"Hayes\"}],\"artists\":null}, {\"board_game_id\":11,\"name\":\"Magic: The Gathering - Amonkhet\",\"release_year\":2017,\"number_of_players\":\"2+\",\"age\":\"13+\",\"playing_time\":\"60 minutes\",\"franchise\":{\"name\":\"Magic: The Gathering\"},\"publisher\":{\"name\":\"Wizards of the Coast\"},\"designers\":[{\"name\":\"Richard\",\"surname\":\"Garfield\"}, {\"name\":\"Ben\",\"surname\":\"Hayes\"}],\"artists\":null}, {\"board_game_id\":13,\"name\":\"The Magnificent\",\"release_year\":2019,\"number_of_players\":\"1-4\",\"age\":\"14+\",\"playing_time\":\"60-90 minutes\",\"franchise\":{\"name\":\"The Magnificent\"},\"publisher\":{\"name\":\"Aporta Games\"},\"designers\":[{\"name\":\"Kristian Amundsen\",\"surname\":\"Østby\"}, {\"name\":\"Eilif\",\"surname\":\"Svensson\"}, {\"name\":\"Ludovic\",\"surname\":\"Maublanc\"}, {\"name\":\"Michael I.\",\"surname\":\"Levin\"}],\"artists\":[{\"name\":\"Martin\",\"surname\":\"Mottet\"}]}, {\"board_game_id\":14,\"name\":\"Cyclades\",\"release_year\":2009,\"number_of_players\":\"2-5\",\"age\":\"13+\",\"playing_time\":\"60-90 minutes\",\"franchise\":{\"name\":\"Cyclades\"},\"publisher\":{\"name\":\"Matagot\"},\"designers\":[{\"name\":\"Bruno\",\"surname\":\"Cathala\"}],\"artists\":[{\"name\":\"Miguel\",\"surname\":\"Coimbra\"}]}, {\"board_game_id\":15,\"name\":\"Risk\",\"release_year\":1959,\"number_of_players\":\"2-6\",\"age\":\"10+\",\"playing_time\":\"120 minutes\",\"franchise\":{\"name\":\"Risk\"},\"publisher\":{\"name\":\"Parker Brothers\"},\"designers\":[{\"name\":\"Albert\",\"surname\":\"Lamorisse\"}],\"artists\":null}, {\"board_game_id\":17,\"name\":\"Terraforming Mars\",\"release_year\":2016,\"number_of_players\":\"1-5\",\"age\":\"12+\",\"playing_time\":\"120 minutes\",\"franchise\":{\"name\":\"Terraforming Mars\"},\"publisher\":{\"name\":\"FryxGames\"},\"designers\":[{\"name\":\"Jacob\",\"surname\":\"Fryxelius\"}],\"artists\":[{\"name\":\"Isaac\",\"surname\":\"Fryxelius\"}, {\"name\":\"Daniel\",\"surname\":\"Fryxelius\"}]}, {\"board_game_id\":18,\"name\":\"Terraforming Mars: Colonies\",\"release_year\":2018,\"number_of_players\":\"1-5\",\"age\":\"12+\",\"playing_time\":\"120 minutes\",\"franchise\":{\"name\":\"Terraforming Mars\"},\"publisher\":{\"name\":\"FryxGames\"},\"designers\":[{\"name\":\"Jacob\",\"surname\":\"Fryxelius\"}, {\"name\":\"Jonathan\",\"surname\":\"Fryxelius\"}],\"artists\":[{\"name\":\"Isaac\",\"surname\":\"Fryxelius\"}, {\"name\":\"William\",\"surname\":\"Bricker\"}]}]\r\n"
				+ "");
		resp.getWriter().flush();
	}
//
//	@Override
//	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//	}
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Properties properties = new Properties();
//		
//		try {
//			properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
//		} catch(IOException ex) {
//			System.err.println("Could not load properties");
//			ex.printStackTrace();
//        }
//		
//		String dbName = properties.getProperty("dbName");
//		String username = properties.getProperty("username");
//		String password = properties.getProperty("password");
//		String connectionURL = "jdbc:postgresql://localhost:5432/" + dbName 
//				+ "?user=" + username + "&password=" + password;
//		
//		String json = null;
//		
//		try (Connection con = DriverManager.getConnection(connectionURL)) {
//			try (PreparedStatement pst = con.prepareStatement("SELECT json_agg(row_to_json(bg_full)) AS all_board_games_json\r\n"
//					+ "FROM (\r\n"
//					+ "    SELECT \r\n"
//					+ "        bg.board_game_id,\r\n"
//					+ "        bg.name,\r\n"
//					+ "        bg.release_year,\r\n"
//					+ "        bg.number_of_players,\r\n"
//					+ "        bg.age,\r\n"
//					+ "        bg.playing_time,\r\n"
//					+ "        \r\n"
//					+ "        -- Franchise details as a JSON object\r\n"
//					+ "        (SELECT row_to_json(franchise_data)\r\n"
//					+ "         FROM (SELECT f.name FROM Franchises f WHERE f.franchise_id = bg.franchise_id) AS franchise_data) AS franchise,\r\n"
//					+ "         \r\n"
//					+ "        -- Publisher details as a JSON object\r\n"
//					+ "        (SELECT row_to_json(publisher_data)\r\n"
//					+ "         FROM (SELECT p.name FROM Publishers p WHERE p.publisher_id = bg.publisher_id) AS publisher_data) AS publisher,\r\n"
//					+ "         \r\n"
//					+ "        -- Designers as a JSON array\r\n"
//					+ "        (SELECT json_agg(row_to_json(designer_data))\r\n"
//					+ "         FROM (\r\n"
//					+ "             SELECT d.name, d.surname\r\n"
//					+ "             FROM Designers d\r\n"
//					+ "             JOIN BoardGameDesigners bgd ON d.designer_id = bgd.designer_id\r\n"
//					+ "             WHERE bgd.board_game_id = bg.board_game_id\r\n"
//					+ "         ) AS designer_data) AS designers,\r\n"
//					+ "         \r\n"
//					+ "        -- Artists as a JSON array\r\n"
//					+ "        (SELECT json_agg(row_to_json(artist_data))\r\n"
//					+ "         FROM (\r\n"
//					+ "             SELECT a.name, a.surname\r\n"
//					+ "             FROM Artists a\r\n"
//					+ "             JOIN BoardGameArtists bga ON a.artist_id = bga.artist_id\r\n"
//					+ "             WHERE bga.board_game_id = bg.board_game_id\r\n"
//					+ "         ) AS artist_data) AS artists\r\n"
//					+ "\r\n"
//					+ "    FROM BoardGames bg\r\n"
//					+ ") AS bg_full")) {
//				ResultSet result = pst.executeQuery();
//	
//				if(result.next()) {
//					json = result.getString(1);
//				}
//				
//				if(result.next())
//					System.err.println("Too many rows in return JSON");
//				
//			} catch(SQLException e) {
//				System.err.println("Could not complete query");
//			}
//		} catch(SQLException e) {
//			System.err.println("Could not connect to database");
//			e.printStackTrace();
//			System.exit(0);
//		}
//		
//		if(json == null) {
//			System.err.println("JSON is empty, something went wrong");
//			System.exit(0);
//		}
//		
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//		resp.setStatus(HttpServletResponse.SC_OK);
//		resp.setContentType("application/json");
//		resp.getWriter().write(json);
//		resp.getWriter().flush();
//	}
}
