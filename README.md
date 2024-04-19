# BookStore
*Hi we are from HCMUTE, this is our Web Programming course final project.*<br />
Here is some information about the project :<br />
- Link : [http://bookstore.ap-southeast-2.elasticbeanstalk.com/?fbclid=IwAR0oP-xQB7Xw0cNdgim-3pp8SzgueuB01m5uiR_UkRhv7re9PEC7nOKJ0jI](URL)
- Name: Online Bookstore.<br />
- Description: A website for both sellers and buyers. Customers can purchase books, view books, add to favorites, add to cart, and checkout. Admin can view revenue, profit, manage quantities, selling prices, add authors, books, users, etc.<br />
- Programming language: Java.<br />
- Technology: Servlet.<br />
- Database: PostgreSQL.<br />
- Theme : [https://themewagon.com/themes/free-html5-e-commerce-template-bootstrap4-amado/?fbclid=IwAR2bK7gJ55_B_r2SPXVkwEG2iQCqTtU2N6WbA-G_bxv6HfRpNcQYPZxf7CU](URL)


SET UP PROJECT TO LOCAL HOST
1.	Requirement
-	Install Postgres with JDBC driver.
Link to download: Community DL Page (enterprisedb.com)
-	JAVA 17 or JAVA 21
-	TomCat 10.1.20.
Link to download: Apache Tomcat® - Apache Tomcat 10 Software Downloads
-	File bookshop.sql (I have attached in project)
 ![image](https://github.com/toantammute/BookStore/assets/145630820/fcb09c4f-0a8f-4b03-b97f-6918bf6034a4)

2.	Connect to Database (Postgres)
-	On the File Explorer – Your disk install PostgreSQL - Program File – PostgreSQL, choose the version that have file bin. Copy the path.
-	In Pgadmin4, choose File – References – Paths- Binary paths. I have a file bin in version 15.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/64256c02-c22b-4e08-bd91-651842a158ad)
-	In PostgreSQL Binary Path, in the version that have file bin, paste the file path to Binary Path. Then click Save.
-	Create a new database name “bookshop”
-	Right click on the bookshop database chose restore.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/9a26c914-3346-45eb-aee3-1673d48f28e2)
-	Choose the bookshop.sql to the filename. Click restore.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/7b8c5ee6-bd48-4b54-9d98-e2b9459d08c2)
-	In Intellij choose Database to connect PostgreSQL to project. Choose Data Source - PostgreSQL.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/daee336e-29e2-41b5-b00b-126d4cdf2ea2)
-	If there was a warning error not found driver, click Download.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/be98930d-1ad9-4bb9-a801-0125f4436d1f)
-	Fill in the user, password, database, copy the URL. Test connection, if connect successfully, click OK.
![image](https://github.com/toantammute/BookStore/assets/145630820/f3011f90-37ed-40cc-bf57-7e2b8caaf232)
-	In file …\BookStore\src\main\resources\META-INF\persistence.xml edit jdbc.url value to the URL you already have copied. Edit jdbc user and jdbc password. Jdbc user and jdbc password are username and password of your PostgreSQL.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/d4d0e5a7-cc63-4995-814f-3a71c0feaf7b)

3.	Set up TomCat Local.
-	On the Run/Debug configuration. Choose edit configuration.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/2cf25826-f5bd-44d4-876d-0a18dc32b627)
-	On the left column add new server, choose Tomcat Server - local.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/61a933ed-ae0d-4106-bef7-e28eda884246)
-	In the application server choose Configure. Then choose the file Tomcat that you have downloaded. Click OK.
 ![image](https://github.com/toantammute/BookStore/assets/145630820/b3fc19be-e3a8-4525-b39f-718a8983c1b1)
-	Run the project. This is the home page.
  ![image](https://github.com/toantammute/BookStore/assets/145630820/e1b1ec14-71b4-4d74-85d4-2346bc4fa99d)
-	To login to admin: email: admin@gmail.com, password: 1. If you want to log in by a member, register then log in.
