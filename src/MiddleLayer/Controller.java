/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleLayer;

import UILayer.Main;
import Models.Book;
import Models.BookViewModel;
import Models.Category;
import Models.Stock;
import Models.StockViewModel;
import Models.User;
import Models.UserViewModel;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.logging.Param;

/**
 *
 * @author Deltawing
 */
public class Controller {

    //Hardcoding cretentials is to prevent exposing sensitive information.
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/iBook";

    static final String USER = "root";
    static final String PASS = "123";

    public static String[] Login(String username, String password) {

        String fullname = "";
        String userType = "";
        String arg[] = new String[2];
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pst;
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String query = "SELECT Firstname, Lastname, UserType "
                    + "FROM User WHERE User.Username = ? AND password = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            if (rs.first()) {

                fullname = rs.getString("Firstname") + " " + rs.getString("Lastname");
                userType = String.valueOf(rs.getInt("UserType"));

            } else {
                fullname = "";
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        arg[0] = fullname;
        arg[1] = userType;
        return arg;
    }

    public static Book getBooks(Long id) {

        Book b = new Book();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String query = "SELECT Book.Id, Book.ShortTitle, Book.Title, Book.ISBN, Book.CategoryId, Category.Description, "
                    + "Book.Author, Book.Pages, Book.Publisher, Book.PublishedDate "
                    + "FROM Book INNER JOIN Category ON Book.CategoryId = Category.Id"
                    + " WHERE Book.Id = " + id;

            rs = st.executeQuery(query);

            if (rs.first()) {

                b.setId(rs.getLong("Id"));
                b.setShortTitle(rs.getString("ShortTitle"));
                b.setTitle(rs.getString("Title"));
                b.setISBN(rs.getString("ISBN"));
                b.setCategoryId(rs.getLong("CategoryId"));
                b.setAuthor(rs.getString("Author"));
                b.setPages(rs.getLong("Pages"));
                b.setPublisher(rs.getString("Publisher"));
                b.setPublishedDate(rs.getString("PublishedDate"));

            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }

    public static ArrayList<BookViewModel> getBooks(String filter, int filterType) {

        ArrayList<BookViewModel> bookList = new ArrayList();
        BookViewModel b;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String filterQuery = "";

            switch (filterType) {
                case 1:
                    filterQuery = " WHERE Category.Description LIKE '%" + filter + "%'";
                    break;
                case 2:
                    filterQuery = " WHERE Book.Author LIKE '%" + filter + "%'";
                    break;
                default:
                    filterQuery = " WHERE"
                            + " Book.ShortTitle LIKE '%" + filter + "%'"
                            + " OR Book.Title LIKE '%" + filter + "%'"
                            + " OR Book.Author LIKE '%" + filter + "%'"
                            + " OR ISBN LIKE '%" + filter + "%'"
                            + " OR Category.Description LIKE '%" + filter + "%' ";
                    break;
            }

            String query = "SELECT Book.Id, Book.ShortTitle, Book.Title, Book.ISBN, Category.Description, "
                    + "Book.Author, Book.Pages, Book.Publisher, Book.PublishedDate,  "
                    + "IFNULL(Stock.Quantity, 0) AS Quantity "
                    + "FROM Book INNER JOIN Category ON Book.CategoryId = Category.Id "
                    + "LEFT JOIN (SELECT X.BookId, (IFNULL(X.Qty, 0) - IFNULL(Y.Qty, 0)) AS Quantity "
                    + "FROM (SELECT BookId, SUM(Quantity) AS Qty FROM Stock WHERE UpdateType = 1 GROUP BY BookId ) AS X "
                    + "LEFT JOIN (SELECT BookId, SUM(Quantity) AS Qty FROM Stock WHERE UpdateType = 0 GROUP BY BookId) AS Y "
                    + "ON X.BookId = Y.BooKId) AS Stock ON Book.Id = Stock.BookId "
                    + filterQuery
                    + "GROUP BY Book.Id, Book.Title, Book.ISBN, Category.Description, Book.Author, Book.Pages, Book.PublishedDate "
                    + "ORDER BY Book.Title ";

            rs = st.executeQuery(query);

            while (rs.next()) {

                b = new BookViewModel();
                b.setId(rs.getLong("Id"));
                b.setShortTitle(rs.getString("ShortTitle"));
                b.setTitle(rs.getString("Title"));
                b.setISBN(rs.getString("ISBN"));
                b.setCategory(rs.getString("Description"));
                b.setAuthor(rs.getString("Author"));
                b.setPages(rs.getLong("Pages"));
                b.setPublisher(rs.getString("Publisher"));
                b.setPublishedDate(rs.getString("PublishedDate"));
                b.setQuantity(rs.getLong("Quantity"));

                bookList.add(b);
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookList;
    }

    public static Category getCategories(Long id) {

        Category c = new Category();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String query = "SELECT Category.Id, Category.Description "
                    + "FROM Category WHERE Category.Id = " + id;

            rs = st.executeQuery(query);

            if (rs.first()) {

                c.setId(rs.getLong("Id"));
                c.setDescription(rs.getString("Description"));

            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    public static ArrayList<Category> getCategories() {

        ArrayList<Category> categoryList = new ArrayList();
        Category c;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String query = "SELECT * FROM category ORDER BY Description";

            rs = st.executeQuery(query);

            while (rs.next()) {

                c = new Category();
                c.setId(rs.getLong("Id"));
                c.setDescription(rs.getString("Description"));

                categoryList.add(c);
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categoryList;
    }

    public static User getUsers(Long id) {

        User u = new User();

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String query = "SELECT User.Id, User.Username, User.Password, User.Firstname, User.Lastname, User.UserType "
                    + "FROM User WHERE User.Id = " + id;

            rs = st.executeQuery(query);

            if (rs.first()) {

                u.setId(rs.getLong("Id"));
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setFirstname(rs.getString("Firstname"));
                u.setLastname(rs.getString("Lastname"));
                u.setUserTypeIndex(rs.getInt("UserType"));
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return u;
    }

    public static ArrayList<UserViewModel> getUsers() {

        ArrayList<UserViewModel> userList = new ArrayList();
        UserViewModel u;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String query = "SELECT * FROM User ORDER BY Firstname";

            rs = st.executeQuery(query);

            while (rs.next()) {

                u = new UserViewModel();
                u.setId(rs.getLong("Id"));
                u.setFirstname(rs.getString("Firstname"));
                u.setLastname(rs.getString("Lastname"));
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));

                if (rs.getInt("UserType") == 1) {
                    u.setUserType("Administrator");
                } else if (rs.getInt("UserType") == 2) {
                    u.setUserType("Stock Controller");
                }

                userList.add(u);
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userList;
    }

    //Parameter type = true : Importing Stock // type = flase : Exporting Stock
    public static ArrayList<StockViewModel> getStockHistoryList(boolean type) {

        ArrayList<StockViewModel> stockHistory = new ArrayList();
        StockViewModel u;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            String query = "SELECT B.Id, B.Title, B.ISBN, SUM(S.Quantity) AS Quantity, S.UpdatedDate "
                    + "FROM Stock AS S INNER JOIN Book AS B ON S.BookId = B.Id "
                    + "WHERE UpdateType = " + type + " "
                    + "GROUP BY B.id, B.Title, S.UpdatedDate "
                    + "ORDER BY S.UpdatedDate";

            rs = st.executeQuery(query);

            while (rs.next()) {

                u = new StockViewModel();
                u.setItemDescription(rs.getString("Title"));
                u.setISBN(rs.getString("ISBN"));
                u.setQuantity(rs.getLong("Quantity"));
                u.setUpdateDate(rs.getDate("UpdatedDate"));

                stockHistory.add(u);
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return stockHistory;
    }

    public static boolean saveBook(Book b) {

        String query;

        if (b.getId() == null) {

            query = "INSERT INTO book(ShortTitle, Title, ISBN, CategoryId, Author, Pages, Publisher, PublishedDate)"
                    + " VALUES ("
                    + "'" + b.getShortTitle().replace("'", "''") + "',"
                    + "'" + b.getTitle().replace("'", "''") + "',"
                    + "'" + b.getISBN().replace("'", "''") + "',"
                    + b.getCategoryId() + ","
                    + "'" + b.getAuthor().replace("'", "''") + "',"
                    + b.getPages() + ","
                    + "'" + b.getPublisher().replace("'", "''") + "',"
                    + "'" + b.getPublishedDate().replace("'", "''") + "')";

        } else {

            query = "UPDATE book SET "
                    + "ShortTitle = '" + b.getShortTitle().replace("'", "''") + "',"
                    + "Title = '" + b.getTitle().replace("'", "''") + "',"
                    + "ISBN = '" + b.getISBN().replace("'", "''") + "',"
                    + "CategoryId = " + b.getCategoryId() + ","
                    + "Author = '" + b.getAuthor().replace("'", "''") + "',"
                    + "Pages = " + b.getPages() + ","
                    + "Publisher = '" + b.getPublisher().replace("'", "''") + "',"
                    + "PublishedDate = '" + b.getPublishedDate().replace("'", "''") + "'"
                    + "WHERE id = " + b.getId();

        }

        return Exec(query);

    }

    public static boolean saveCategory(Category c) {

        String query;

        if (c.getId() == null) {

            query = "INSERT INTO Category(Description)"
                    + " VALUES ("
                    + "'" + c.getDescription().replace("'", "''") + "'"
                    + ")";

        } else {

            query = "UPDATE Category SET "
                    + " Description = '" + c.getDescription().replace("'", "''") + "'"
                    + "WHERE id = " + c.getId();
        }

        return Exec(query);

    }

    public static boolean saveUser(User u) {

        String query;
        if (u.getId() == null) {

            query = "INSERT INTO User(Firstname, Lastname, Username, Password, UserType)"
                    + " VALUES ("
                    + "'" + u.getFirstname().replace("'", "''") + "',"
                    + "'" + u.getLastname().replace("'", "''") + "',"
                    + "'" + u.getUsername().replace("'", "''") + "',"
                    + "'" + u.getPassword().replace("'", "''") + "',"
                    + u.getUserTypeIndex()
                    + ")";
        } else {

            query = "UPDATE User SET "
                    + "Firstname = '" + u.getFirstname().replace("'", "''") + "',"
                    + "Lastname = '" + u.getLastname().replace("'", "''") + "',"
                    + "Username = '" + u.getUsername().replace("'", "''") + "',"
                    + "Password = '" + u.getPassword().replace("'", "''") + "',"
                    + "UserType = '" + u.getUserTypeIndex() + "'"
                    + "WHERE id = " + u.getId();

        }
        return Exec(query);

    }

    public static void updateStock(ArrayList<Stock> s, boolean type) {

        String query;

        for (int i = 0; i < s.size(); i++) {
            query = "INSERT INTO Stock(BookId, Quantity, UpdateType, UpdatedDate) VALUES("
                    + s.get(i).getBookId() + ","
                    + s.get(i).getQuantity() + ","
                    + type + ","
                    + "CurDate())";
            Exec(query);
        }
    }

    public static boolean deleteBook(Long id) {

        String query;

        query = "DELETE FROM Book WHERE Id = " + id;

        return Exec(query);

    }

    public static boolean deleteCategory(Long id) {

        String query;

        query = "DELETE FROM Category WHERE Id = " + id;

        return Exec(query);

    }

    public static boolean deleteUser(Long id) {

        String query;

        query = "DELETE FROM User WHERE Id = " + id;

        return Exec(query);

    }

    public static boolean ValidateISBN(String ISBN) {

        String query = "SELECT * FROM Book WHERE ISBN = '" + ISBN + "'";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            rs = st.executeQuery(query);

            while (rs.next()) {

                return true;
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public static boolean ValidateUsername(String username) {

        String query = "SELECT * FROM User WHERE username = '" + username + "'";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            rs = st.executeQuery(query);

            while (rs.next()) {

                return true;
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public static boolean recordExistOfBookStock(Long id) {

        String query = "SELECT * FROM Stock WHERE BookId = '" + id + "'";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            rs = st.executeQuery(query);

            while (rs.next()) {

                return true;
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public static boolean recordExistOfCategory(Long id) {

        String query = "SELECT * FROM Book WHERE CategoryId = '" + id + "'";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;
            ResultSet rs = null;

            st = conn.createStatement();

            rs = st.executeQuery(query);

            while (rs.next()) {

                return true;
            }

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }
    
    //SQL RAW execution method
    private static boolean Exec(String query) {
        Object ob = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement st;

            st = conn.createStatement();

            ob = st.executeUpdate(query);

            st.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return ob == null ? false : true;
        }

    }

}
