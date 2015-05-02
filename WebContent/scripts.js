//Array of the square champion icons.
var squares = document.getElementsByClassName("champSquare");

/**
 * Initializes the page by hiding the champion description divs, attaching listeners to the
 * square icons, fixing the square alignment (for when there's an extra free week champion),
 * and setting the currently displayed champion to the first one.
 */
function start() {
	hideDivs();
	attachListeners();
	fixChampAlignment();
	setTemplate(0);
	setSquareBorders(0);
}

/**
 * Hides the champion description divs. This is done in order to later selectively display a certain div's
 * data rather than show them all at once, or query the server every time.
 */
function hideDivs() {
	var divs = document.getElementsByClassName("champDescriptionDiv");
	for (var i = 0; i < divs.length; i++) {
		divs[i].style.display = "none";
	}
}

/**
 * Fills the "template" div with an actual champion's description contents.
 * @param index of the champion description div to fill the template with.
 */
function setTemplate(index) {
	var template = document.getElementById("template");
	template.innerHTML = document.getElementsByClassName("champDescriptionDiv")[index].innerHTML;
}

/**
 * Sets the champion square borders. The square at the given index will receive a gray border, and
 * the rest with receive no border.
 * @param index of the champion icon to add a border to.
 */
function setSquareBorders(index) {
	for (var i = 0; i < squares.length; i++) {
		if (i == index) {
			squares[i].style.border = "3px solid gray";
		} else {
			squares[i].style.border = "0px";
		}
	}
}

/**
 * Attaches listeners to every champion square such that when they are clicked, the champClicked() method
 * will be called. Also sets that square to have an "index" attribute storing their location.
 */
function attachListeners() {
	for (var i = 0; i < squares.length; i++) {
		squares[i].onclick = champClicked;
		squares[i].setAttribute("index", i);
	}
}

/**
 * Changes the left margins of champion squares if there are 11 champions this week.
 */
function fixChampAlignment() {
	if (squares.length == 11) {
		for (var i = 0; i < squares.length; i++) {
			squares[i].style.marginLeft = "20px";
		}
	}
}

/**
 * Updates the champion on display in the template div and sets the square borders for this
 * new selection.
 */
function champClicked() {
	var index = Number(this.getAttribute("index"));
	setTemplate(index);
	setSquareBorders(index);
}

onload = start;