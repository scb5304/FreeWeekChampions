<%@ page pageEncoding="UTF-8" 
	trimDirectiveWhitespaces="true"
    import="java.util.List, 
    	com.robrua.orianna.type.core.staticdata.Champion,
    	me.stevesite.model.ChampionDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="styles.css">
  <link rel="shortcut icon" type="image/png" href="images/favicon.png"/>
  <title>Free Week Champions Home</title>
</head>
<body>
<div id="wrapper">
  
  <header>
	<h1>League of Legends: Free Week Champions</h1>
  </header>
  
  <nav id="horzNav">
    <a href="index.do">Home</a>
	<a href="about.html">About</a>
  </nav>
  
  <div id="main">
	
	<%-- Get the champions and their cleaned names for use in this JSP --%>
	<c:set var="champs" scope="session" value="${requestScope.champs}"/>
	<c:set var="cleanedNames" scope="session" value="${requestScope.cleanedNames}"/>
	
	<div id="champSquaresDiv">
	
	<%-- Loop through champions --%>
	<c:forEach items="${champs}" var="champ" varStatus="i">
	
	  <%-- Get this champion's cleaned name --%>	  
	  <c:set var="cleanedName" scope="session" value="${cleanedNames.get(i.index)}"></c:set>
	  
	  <%-- Display image for this champion --%>
	  <c:set var="imgSource" value="${'images/square/'}${cleanedName}_Square_0.png"></c:set>
	  <img class="champSquare" src="${imgSource}" alt="${imgSource}"/>
	  
	</c:forEach>
	
	</div>
	
	<%-- Mark a spot for each champion's data to actually be displayed --%>
	<div id="template"></div>
	
	<!-- BEGIN CHAMPION DESCRIPTION DIVS -->
	
	<%-- Generate a champDescriptionDiv for each champion --%>
	<c:forEach items="${champs}" var="champ" varStatus="i">
	  <div class="champDescriptionDiv">
	  
	  <%-- Champion name, title --%>
	  <h2><c:out value="${champ.getName()}, ${champ.getTitle()}"></c:out></h2>
	  
	  <c:set var="cleanedName" scope="session" value="${cleanedNames.get(i.index)}"></c:set>
	  
	  <%-- Splash --%>
	  <c:set var="imgSource" value="${'images/splash/'}${cleanedName}_0.jpg"></c:set>
	  <img class="champSplash" src="${imgSource}" alt="${imgSource}"/>
	  
	  <%-- Role --%>
	  <p>Primary role: <c:out value="${champ.getRole()}"></c:out></p>
	  
	  <%-- Abilities --%>
	  <h3>Abilities</h3>
	  
	  <table id="abilityTable">
	  	<tbody>
	  	 
	  	  <tr>
	  	    <td class="ability" background="${'images/ability/passive/'}${cleanedNames.get(i.index)}_Passive.png"></td>
	  	    <td class="abilityDescription"><c:out value="${champ.getPassive()}" escapeXml="false"></c:out></td>
	  	  <tr>
	  	  
	  	  <tr>
	  	    <td class="ability" background="${'images/ability/q/'}${cleanedNames.get(i.index)}_Q.png"><p>Q</p></td>
	  	    <td class="abilityDescription"><c:out value="${champ.getSpell1()}" escapeXml="false"></c:out></td>
	  	  <tr>
	  	  
	  	  <tr>
	  	    <td class="ability" background="${'images/ability/w/'}${cleanedNames.get(i.index)}_W.png"><p>W</p></td>
	  	    <td class="abilityDescription"><c:out value="${champ.getSpell2()}" escapeXml="false"></c:out></td>
	  	  <tr>
	  	  
	  	  <tr>
	  	    <td class="ability" background="${'images/ability/e/'}${cleanedNames.get(i.index)}_E.png" ><p>E</p></td>
	  	    <td class="abilityDescription"><c:out value="${champ.getSpell3()}" escapeXml="false"></c:out></td>
	  	  <tr>
	  	  
	  	  <tr>
	  	    <td class="ability" background="${'images/ability/r/'}${cleanedNames.get(i.index)}_R.png"><p>R</p></td>
	  	    <td class="abilityDescription"><c:out value="${champ.getSpell4()}" escapeXml="false"></c:out></td>
	  	  <tr>
	  	</tbody>
	  </table>
	  
	  <%-- Lore --%>
	  <h3>Lore</h3>
	    <div id="loreDiv">
	    <p><c:out value="${champ.getLore()}" escapeXml="false"></c:out></p>
	    </div>
	  
	  </div>
	</c:forEach>
	<%--  END CHAMPION DESCRIPTION DIVS --%>
	
  </div> <%-- End main div --%>
  
  <footer>
  	Developed for IST 411 by Steven Brown<br>
	<small>&copy Copyright 2015</small>  	
  </footer>
  
  </div> <%-- End wrapper div --%>	
  
  <script src="scripts.js"></script>
</body>
</html>