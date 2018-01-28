<b>Update: I made this project my senior year of college at Penn State. I believe the assignment was to... make something. So I tried to make something relevant to what we learned in class, and something I enjoy, which at the time, was Servlets/JSP and League of Legends (respectively). I managed to dig up the actual document submitted for the project as well, which I added to the root folder of this repo. Original decription follows:</b>

This web application displays the Champions in League of Legends that are currently free to play. Champions normally need to be purchased using real money or through an earned in-game currency. However, Riot also offers a rotation of Champions that are entirely free that week.

Using Riot Games' API, queries are sent from my server every hour to receive all the data regarding the current free week rotation. If this rotation is deemed new, it is stored in a MySQL database. When the user visits the site, they arrive at the IndexServlet, which uses a Database Access Object to retrieve current Champion data from the database and then forwards the request to a JSP. This JSP renders the page using HTML, JSTL, and CSS. Javascript is used to switch between Champion data client-side.

Website may be available at http://stevesite.me
