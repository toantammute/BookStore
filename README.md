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
- Connect with database in Postgres
  + Connect the database of your IDE to Postgres
  + In Persisance from src/main/resources/META-INF/persistence.xml edit your jdbc url, jdbc.user and jdbc.password of your postgres
    ![image](https://github.com/toantammute/BookStore/assets/145630820/69e78301-6de3-4afc-81d7-5f2bf5b06e34)
  + After connect database successfully, edit the jakarta.persistence.schema-generation.database.action value to update.
  + In postgres add a user with role is admin to add book or other database.
 - Configure to TomCat 10.1.20
   + Add the Tomcat server local.
   + In application server choose Tomcat 10.1.20
   + In deployment tab, add artifact: /BookShop_war, /BookShop_war_exploded
     ![image](https://github.com/toantammute/BookStore/assets/145630820/4d9faf3e-3f52-4256-9993-6f5d22d20557)
   + Click OK to apply.
     
  !!! RECOMMEND: USE JDK 21.


