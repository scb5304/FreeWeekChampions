package me.stevesite.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.champion.ChampionStatus;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.staticdata.Champion;

/**
* Runnable object that runs indefinitely, checking for new champion data every hour and updating the database if there is.<br><br>
* <strong>Note:</strong> interrupting the Courier ends the loop, attempting to stop the thread gracefully.
* @see ConfigReader
*/
public class Courier implements Runnable {

	private boolean running;
	private List<ChampionDTO> riotDTOs;

	/**
	 * Initializes the Riot API. Uses NA as a Region and Mirror, and reads the API key using ConfigReader.
	 */
	private void initAPI() {
		RiotAPI.setRegion(Region.NA);
		RiotAPI.setMirror(Region.NA);
		RiotAPI.setAPIKey(new ConfigReader().getAPIkey());
	}
	
	/**
	 * Creates the Courier and calls initAPI(). Sets running to true.
	 */
	public Courier() {
		riotDTOs = new ArrayList<>();
		running = true;
		initAPI();
	}

	/**
	 * Fetches champions that are identified as being freeToPlay once per hour, and updates the database if there has
	 * been a change.
	 * */
	@Override
	public void run() {
		
		while (running) {
			riotDTOs.clear();
			try {
				Map<Champion, ChampionStatus> freeChampsMap = RiotAPI.getChampionStatuses(true);
				Set<Champion> keyset = freeChampsMap.keySet();

				for (Champion c : keyset) {
					riotDTOs.add(createChampionDTO(c));
				}

				if (isNewChampionRotation()) {
					updateDatabase();
				}

				Thread.sleep(60 * 60 * 1000);

			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

	/**
	 * Takes desired information from Orianna's Champion object and stores it in a ChampionDTO object.
	 * @param c an actual Champion object from the Orianna API wrapper.
	 * @return a new ChampionDTO containing several Champion attributes.
	 */
	private ChampionDTO createChampionDTO(Champion c) {

		ChampionDTO champDTO = new ChampionDTO();

		champDTO.setName(c.getName());
		champDTO.setTitle(c.getTitle());
		champDTO.setRole(c.getTags().get(0));
		champDTO.setPassive(c.getPassive().getName() + ": " + c.getPassive().getDescription());
		champDTO.setSpell1(c.getSpells().get(0).getName() + ": " + c.getSpells().get(0).getDescription());
		champDTO.setSpell2(c.getSpells().get(1).getName() + ": " + c.getSpells().get(1).getDescription());
		champDTO.setSpell3(c.getSpells().get(2).getName() + ": " + c.getSpells().get(2).getDescription());
		champDTO.setSpell4(c.getSpells().get(3).getName() + ": " + c.getSpells().get(3).getDescription());
		champDTO.setLore(c.getLore());

		return champDTO;
	}

	/**
	* Deletes champions currently in the champion table and adds new ones.
	* Prints the date and time that database updating occured to console.
	*/
	private void updateDatabase() {

		ChampionDAO dao = new ChampionDAO();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println("[" + strDate + "]: UPDATING DATABASE.");

		dao.deleteChampions();

		for (ChampionDTO dto : riotDTOs) {
			dao.addChampion(dto);
		}

	}

	/**
	 * Compares the currently stored Champion data with that fetched from Riot's API and determines whether or not
	 * the free champion rotation has been updated.
	 * */
	private boolean isNewChampionRotation() {

		List<ChampionDTO> myDTOs = new ChampionDAO().getAllChampions();
		
		List<String> myNames = new ArrayList<>();
		List<String> riotNames = new ArrayList<>();
		
		//Different amount of champions means a guaranteed new free week
		if (myDTOs.size() != riotDTOs.size()) {
			return true;
		}

		for (int i = 0; i < myDTOs.size(); i++) {
			myNames.add(myDTOs.get(i).getName());
			riotNames.add(riotDTOs.get(i).getName());
		}
		
		//By sorting the lists of names alphabetically, it can be determined whether they are equal
		Collections.sort(myNames);
		Collections.sort(riotNames);

		for (int i = 0; i < myDTOs.size(); i++) {
			//If any names aren't equal, return true
			if (!myNames.get(i).equals(riotNames.get(i))) {
				return true;
			}
		}

		return false;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean isRunning) {
		this.running = isRunning;
	}
}
