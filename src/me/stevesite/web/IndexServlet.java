package me.stevesite.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.stevesite.model.ChampionDAO;
import me.stevesite.model.ChampionDTO;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 8515376057652446977L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ChampionDTO> champs = new ChampionDAO().getAllChampions();

		request.setAttribute("champs", champs);
		request.setAttribute("cleanedNames", getCleanedNames(champs));

		RequestDispatcher dispatch = request.getRequestDispatcher("index.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * Creates a list of Champion names without spaces, apostrophes, or periods for use in file names.
	 * @param champs the list of ChampionDTOs that contains the names to be cleaned.
	 */
	private List<String> getCleanedNames(List<ChampionDTO> champs) {
		List<String> cleanedNames = new ArrayList<>();
		for (ChampionDTO c: champs) {
			String cleanedName = c.getName().replaceAll("\\s+", "").replaceAll("'", "").replaceAll("\\.", "");
			cleanedNames.add(cleanedName);
		}
		return cleanedNames;
	}
}
