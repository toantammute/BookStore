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


HOW TO RUN THIS PROJECT IN LOCAL HOST
- Requirement:
 + Install Postgres with JDBC driver.
 + Tomcat version 10.1.20
 + JDK 21
- Create database on PgAdmin
  + Right click on database name to restore the database
    ![image](https://github.com/toantammute/BookStore/assets/145630820/18162d9b-53dd-4ef6-acdd-76ab8dbce5e8)
  + Choose the target file
    ![image](https://github.com/toantammute/BookStore/assets/145630820/486d8e6e-9f53-4ef2-8d08-b24be3fb1652)


- Connect with database in Postgres
  + Connect the database of your IDE to Postgres
    ![image](https://github.com/toantammute/BookStore/assets/145630820/0ba99748-741c-45fe-a3be-d221cb79c33b)
    If your IDE miss the driver there was an alert, click download to download the driver
    ![image](https://github.com/toantammute/BookStore/assets/145630820/35b8420e-7bd1-4c76-a103-84b084cc9538)
    Then fill ussername, password and choose database of Postgresql.
- Connect to TomCat
  ![image](https://github.com/toantammute/BookStore/assets/145630820/c4ae9ed9-d4c5-4b04-9453-1af665e1e167)
  Click to Add new on the left column, then choose TomCat Local
  ![image](https://github.com/toantammute/BookStore/assets/145630820/33108fb7-2e0d-40f8-b1c8-728162fb7f36)
  At the Application Server, click configure to choose the TomCat directory.
  ![image](https://github.com/toantammute/BookStore/assets/145630820/4e144a4b-dd2e-48fc-a487-4cb5b2ee2793)
  On Deployment tab choose Insert -> Artifact, then choose both 2 file
  ![image](https://github.com/toantammute/BookStore/assets/145630820/ff40e62c-f650-4411-9578-ac5048df5c7d)
  ![image](https://github.com/toantammute/BookStore/assets/145630820/8f8db7ed-94e8-4fce-97ff-0b2c5c1adc07)







  + In Persisance from src/main/resources/META-INF/persistence.xml edit your jdbc url, jdbc.user and jdbc.password of your postgres
    ![image](https://github.com/toantammute/BookStore/assets/145630820/cc328260-1b67-4728-82bf-97d34b658a23)

  + After connect database successfully, edit the jakarta.persistence.schema-generation.database.action value to update.
  + In postgres add a user with role is admin to add book or other database.
 - Configure to TomCat 10.1.20
   + Add the Tomcat server local.
   + In application server choose Tomcat 10.1.20
   + In deployment tab, add artifact: /BookShop_war, /BookShop_war_exploded
     ![image](https://github.com/toantammute/BookStore/assets/145630820/4d9faf3e-3f52-4256-9993-6f5d22d20557)
   + Click OK to apply.
     
  !!! RECOMMEND: USE JDK 21.


