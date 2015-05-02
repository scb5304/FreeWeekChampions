package me.stevesite.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
* ChampionDAO acts as a Database Access Object through the database connection provided by DataSourceMySQL; it can retrieve all Champions,
* delete all Champions, or add a new Champion. ChampionDTO objects are used to transfer Champion data.
* @see ChampionDTO
* @see DataSourceMySQL
*/
public class ChampionDAO {

	private DataSourceMySQL dataSource;
	private Connection connection;
	
	/**
	* Constructs a ChampionDAO with a new DataSourceMySQL.
	*/
	public ChampionDAO(){
		dataSource = new DataSourceMySQL();
	}
	
	/**
	* Retrieves a List of all Champions in the champion table.
	* @return the list of Champions in ChampionDTO format.
	*/
	public List<ChampionDTO> getAllChampions(){
		connection = dataSource.createConnection();
		Statement statement = null;
		ResultSet rs = null;
		List<ChampionDTO> championList = new ArrayList<ChampionDTO>();

		try {
			String query = "SELECT * FROM champion;";

			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {
				ChampionDTO champion = new ChampionDTO();
				champion.setName(rs.getString("name"));
				champion.setTitle(rs.getString("title"));
				champion.setRole(rs.getString("role"));
				champion.setPassive(rs.getString("passive"));
				champion.setSpell1(rs.getString("spell1"));
				champion.setSpell2(rs.getString("spell2"));
				champion.setSpell3(rs.getString("spell3"));
				champion.setSpell4(rs.getString("spell4"));
				champion.setLore(rs.getString("lore"));

				championList.add(champion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
		}

		return championList;
	}

	/**
	* Adds a new Champion to the champion table.
	* @param champion the ChampionDTO object that stores this Champion's data.
	*/
	public void addChampion(ChampionDTO champion){
		connection = dataSource.createConnection();
		Statement statement = null;

		String sql = "INSERT INTO champion VALUES (" + 
				"\"" + champion.getName() + "\", " + 
				"\"" + champion.getTitle() + "\", " + 
				"\"" + champion.getRole() + "\", " + 
				"\"" + champion.getPassive() + "\", " + 
				"\"" + champion.getSpell1() + "\", " + 
				"\"" + champion.getSpell2() + "\", " + 
				"\"" + champion.getSpell3() + "\", " + 
				"\"" + champion.getSpell4() + "\", " + 
				"\"" + champion.getLore() + "\"" + ");";

		try {
			statement = connection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out.println("Record inserted into Champion table for champion " + champion.getName() + ".");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	* Deletes all Champions in the champion table.
	*/
	public void deleteChampions() {
		connection = dataSource.createConnection();
		Statement statement = null;
		
		String sql = "DELETE FROM champion;";
		System.out.println(sql);
		try {
			statement = connection.prepareStatement(sql);
			statement.executeUpdate(sql);

			System.out.println("Deleted records in Champion table.");
		} catch (SQLException e) { e.printStackTrace(); }
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e){}
		}
		
	}

}
