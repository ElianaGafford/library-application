/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author Gaffo
 */
package jdbc.demo;

import java.sql.*;
import java.util.Scanner;

public class LibraryRunner {

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static Connection getDBConnection() throws SQLException {
        //Edit to connect to your database
        String connectionurl = "jdbc:mysql://127.0.0.1:3306/project";
        String username = "root";
        String password = "Valorant";

        Connection dbconn;

        try {
            //connection to a database
            dbconn = DriverManager.getConnection(connectionurl, username, password);
            System.out.println("Database connection was successful!");
            return dbconn;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws SQLException {
        ResultSet rs = null;
        int choice1;
        boolean again = true;

        Scanner in = new Scanner(System.in);
        Scanner str = new Scanner(System.in);

        System.out.println("Hello User!");

        while (again) {
            System.out.println("What would you like to access? ");
            System.out.println("Enter 1. Student ");
            System.out.println("Enter 2. Book ");
            System.out.println("Enter 3. Employee ");
            System.out.println("Enter 4. Order ");
            System.out.println("Enter 5. Publisher ");
            System.out.println("Enter 6. Author ");
            System.out.println("Enter 7. Accessories ");
            System.out.println("Enter 8. Exit");
            System.out.print("Enter a choice: ");

            choice1 = in.nextInt();

            switch (choice1) {
                case 1:
                    String studentID,
                     firstname,
                     lastname,
                     middleinitial;
                    while (true) {
//                        System.out.println("1. Insert Student Detail");
                        System.out.println("1. Select a Student");
                        System.out.println("2. Select all Students");
                        System.out.println("3. Update Student Details");
                        System.out.println("4. Delete Student Detail");
                        System.out.print("Enter a choice: ");
                        int choice2 = in.nextInt();

//                        if (choice2 == 1) {
//
//                            System.out.println("Inserting New Data");
//                            System.out.println("Please enter the following values");
//
//                            System.out.println("Enter Student ID : ");
//                            studentID = str.nextLine();
//                            System.out.println("Enter Student First Name : ");
//                            firstname = str.nextLine();
//                            System.out.println("Enter Student Last Name :  ");
//                            lastname = str.nextLine();
//                            System.out.println("Enter Student Middle initial: ");
//                            middleinitial = str.nextLine();
//
//                            try {
//                                //insert data into a table of a database/schema
//                                Connection conn = getDBConnection();
//                                String insertion = "insert into student(studentID, firstname, lastname, middleinitial) "
//                                        + "values ('" + studentID + "','" + firstname + "','" + lastname + "','" + middleinitial + "')";
//
//                                PreparedStatement insertstatement = conn.prepareStatement(insertion);
//                                insertstatement.executeUpdate();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        } 
                        if (choice2 == 1) {
                            System.out.println("Making A Selection");

                            System.out.println("Enter a Student ID");
                            studentID = str.nextLine();

                            try {
                                Connection conn = getDBConnection();
                                String selectq = "select * from student where studentID like '%" + studentID + "%'";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s |%-35s | %-35s |%n", "StudentID", "FirstName", "LastName", "MiddleInitial");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s |%-35s | %-35s |%n", rs.getString("studentID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("middleinitial"));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else if (choice2 == 2) {
                            System.out.println("Selecting All");

                            try {
                                //Select all from student
                                Connection conn = getDBConnection();
                                String selectq = "select * from Student";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s |%-35s | %-35s |%n", "StudentID", "FirstName", "LastName", "MiddleInitial");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s |%-35s | %-35s |%n", rs.getString("studentID"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("middleinitial"));

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        } else if (choice2 == 3) {
                            System.out.println("Updating Records");
                            System.out.println("Enter Student ID to update the student record: ");
                            studentID = str.nextLine();

                            System.out.println("Select Student details to Update: ");

                            System.out.println("1. Student First Name : ");

                            System.out.println("2. Student Last Name : ");

                            System.out.println("3. Student Middle Initial: ");

                            int selection = in.nextInt();
                            if (selection == 1) {
                                System.out.println("Enter New Student First Name : ");
                                firstname = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update Student set firstname = '" + firstname + "' where StudentID = '" + studentID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 2) {
                                System.out.println("Enter New Student Last Name : ");
                                lastname = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update Student set lastname = '" + lastname + "' where StudentID= '" + studentID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 3) {
                                System.out.println("Enter New Student Middle initial : ");
                                middleinitial = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update Student set middleinitial = '" + middleinitial + "' where StudentID= '" + studentID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Wrong Input");
                            }
                            break;

                        } else if (choice2 == 4) {
                            System.out.println(". Delete a Student by Student_ID");
                            System.out.println("Enter Student ID : ");
                            studentID = str.nextLine();
                            try {
                                Connection conn = getDBConnection();
                                String deleteq = "delete from Student where StudentID= '" + studentID + "'";
                                PreparedStatement deletest = conn.prepareStatement(deleteq);
                                deletest.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else {
                            System.out.println("Invalid Selection");
                        }
                    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                case 2:
                    String ISBN,
                     author_SSN,
                     publisherID,
                     title,
                     genre_name,
                     employee_SSN,
                     bookstudentID;
                    while (true) {
//                        System.out.println("1. Insert Books");
                        System.out.println("1. Select a Book by ISBN");
                        System.out.println("2. Select all Books");
                        System.out.println("3. Update a book");
                        System.out.println("4. Delete a book by ISBN");
                        System.out.print("Enter a choice: ");
                        int choice3 = in.nextInt();

//                        if (choice3 == 1) {
//
//                            System.out.println("Inserting New Data");
//                            System.out.println("Please enter the following values");
//
//                            System.out.println("Enter ISBN number : ");
//                            ISBN = str.nextLine();
//                            System.out.println("Enter Author SSN");
//                            author_SSN = str.nextLine();
//                            System.out.println("Enter publisherID");
//                            publisherID = str.nextLine();
//                            System.out.println("Enter Book title : ");
//                            title = str.nextLine();
//                            System.out.println("Enter Book genre name :  ");
//                            genre_name = str.nextLine();
//                            System.out.println("Enter employee SSN");
//                            employee_SSN = str.nextLine();
//                            System.out.println("Enter studentID");
//                            bookstudentID = str.nextLine();
//
//                            try {
//                                //insert data into a table of a database/schema
//                                Connection conn = getDBConnection();
//                                String insertion = "insert into books(ISBN, author_SSN, publisherID, title, genre_name, employee_SSN, studentID) "
//                                        + "values ('" + ISBN + "','" + author_SSN + "','" + publisherID + "','" + title + "','" + genre_name + "','" + employee_SSN + "','" + bookstudentID + "')";
//
//                                PreparedStatement insertstatement = conn.prepareStatement(insertion);
//                                insertstatement.executeUpdate();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        } 
                        if (choice3 == 1) {
                            System.out.println("Making A Selection");

                            System.out.println("Enter a ISBN");
                            ISBN = str.nextLine();

                            try {
                                Connection conn = getDBConnection();
                                String selectq = "select * from Books where ISBN like '%" + ISBN + "%'";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s |%-35s |%-35s |%-35s |%-35s |%n", "ISBN", "author_SSN", "publisherID", "title", "genre_name", "employee_SSN", "StudentID");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s |%-35s |%-35s |%-35s |%-35s |%n", rs.getString("ISBN"), rs.getString("author_SSN"), rs.getString("publisherID"), rs.getString("title"), rs.getString("genre_name"), rs.getString("employee_SSN"), rs.getString("studentID"));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else if (choice3 == 2) {
                            System.out.println("Selecting All");

                            try {
                                //Select all from student
                                Connection conn = getDBConnection();
                                String selectq = "select * from books";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s |%-35s |%-35s |%-35s |%-35s |%n", "ISBN", "author_SSN", "publisherID", "title", "genre_name", "employee_SSN", "StudentID");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s |%-35s |%-35s |%-35s |%-35s |%n", rs.getString("ISBN"), rs.getString("author_SSN"), rs.getString("publisherID"), rs.getString("title"), rs.getString("genre_name"), rs.getString("employee_SSN"), rs.getString("studentID"));

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        } else if (choice3 == 3) {
                            System.out.println("Updating Records");
                            System.out.println("Enter ISBN number to update a book record: ");
                            ISBN = str.nextLine();

                            System.out.println("Select book details to Update: ");

                            System.out.println("1. Author_SSN");

                            System.out.println("2. PublisherID");

                            System.out.println("3. Title : ");

                            System.out.println("4. Genre : ");

                            System.out.println("5. Employee SSN: ");

                            System.out.println("6. StudentID: ");

                            int selection = in.nextInt();

                            if (selection == 1) {
                                System.out.println("Enter New author_SSN : ");
                                author_SSN = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update book set author_SSN = '" + author_SSN + "' where ISBN= '" + ISBN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 2) {
                                System.out.println("Enter New publisherID : ");
                                publisherID = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update book set publisherID = '" + publisherID + "' where ISBN= '" + ISBN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 3) {
                                System.out.println("Enter New title : ");
                                title = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update book set title = '" + title + "' where ISBN= '" + ISBN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 4) {
                                System.out.println("Enter New genre : ");
                                genre_name = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update book set genre_name = '" + genre_name + "' where ISBN= '" + ISBN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 5) {
                                System.out.println("Enter New employee_SSN : ");
                                employee_SSN = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update book set employee_SSN = '" + employee_SSN + "' where ISBN= '" + ISBN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Wrong Input");
                            }
                            break;

                        } else if (choice3 == 4) {
                            System.out.println("Delete a book by ISBN");
                            System.out.println("Enter ISBN : ");
                            ISBN = str.nextLine();
                            try {
                                Connection conn = getDBConnection();
                                String deleteq = "delete from books where ISBN= '" + ISBN + "'";
                                PreparedStatement deletest = conn.prepareStatement(deleteq);
                                deletest.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else {
                            System.out.println("Invalid Selection");
                        }
                    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 3:
                    String SSN,
                     efirstname,
                     elastname,
                     emiddleinitial,
                     address,
                     sex,
                     birthday,
                     salary,
                     manager_SSN;

                    while (true) {
//                        System.out.println("1. Insert employee Details");
                        System.out.println("1. Select a employee");
                        System.out.println("2. Select all employees");
                        System.out.println("3. Update employee Details");
                        System.out.println("4. Delete employees");
                        System.out.print("Enter a choice: ");
                        int choice4 = in.nextInt();

//                        if (choice4 == 1) {
//
//                            System.out.println("Inserting New Data");
//                            System.out.println("Please enter the following values");
//
//                            System.out.println("Enter a SSN : ");
//                            SSN = str.nextLine();
//                            System.out.println("Enter First Name : ");
//                            efirstname = str.nextLine();
//                            System.out.println("Enter  Last Name :  ");
//                            elastname = str.nextLine();
//                            System.out.println("Enter Middle initial: ");
//                            emiddleinitial = str.nextLine();
//                            System.out.println("Enter an address");
//                            address = str.nextLine();
//                            System.out.println("Enter sex");
//                            sex = str.nextLine();
//                            System.out.println("Enter birthday");
//                            birthday = str.nextLine();
//                            System.out.println("Enter salary");
//                            salary = str.nextLine();
//                            System.out.println("Enter manager_SSN");
//                            manager_SSN = str.nextLine();
//
//                            try {
//                                //insert data into a table of a database/schema
//                                Connection conn = getDBConnection();
//                                String insertion = "insert into employee(SSN, firstname, lastname, middleinitial, address, sex, birthday, salary) "
//                                        + "values ('" + SSN + "','" + efirstname + "','" + elastname + "','" + emiddleinitial + "','" + address + "','" + sex + "','" + birthday + "','" + salary + "','" + manager_SSN + "')";
//
//                                PreparedStatement insertstatement = conn.prepareStatement(insertion);
//                                insertstatement.executeUpdate();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        } 
                         if (choice4 == 1) {
                            System.out.println("Making A Selection");

                            System.out.println("Enter a SSN");
                            SSN = str.nextLine();

                            try {
                                Connection conn = getDBConnection();
                                String selectq = "select * from employee where SSN like '%" + SSN + "%'";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s |%-35s | %-35s | %-35s | %-35s | %-35s |%n", "SSN", "FirstName", "LastName", "MiddleInitial", "Address", "Sex", "Birthday", "Salary", "Manager_SSN");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s |%-35s | %-35s | %-35s | %-35s | %-35s |%n", rs.getString("SSN"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("middleinitial"), rs.getString("address"), rs.getString("sex"), rs.getString("birthday"), rs.getString("salary"), rs.getString("manager_SSN"));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else if (choice4 == 2) {
                            System.out.println("Selecting All");

                            try {
                                //Select all from student
                                Connection conn = getDBConnection();
                                String selectq = "select * from employee";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s |%-35s | %-35s | %-35s | %-35s | %-35s |%n", "SSN", "FirstName", "LastName", "MiddleInitial", "Address", "Sex", "Birthday", "Salary", "Manager_SSN");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s |%-35s | %-35s | %-35s | %-35s | %-35s |%n", rs.getString("SSN"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("middleinitial"), rs.getString("address"), rs.getString("sex"), rs.getString("birthday"), rs.getString("salary"), rs.getString("manager_SSN"));

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        } else if (choice4 == 3) {
                            System.out.println("Updating Records");
                            System.out.println("Enter a SSN to update an employee record: ");
                            SSN = str.nextLine();

                            System.out.println("Select employee details to Update: ");

                            System.out.println("1.  First Name : ");

                            System.out.println("2.  Last Name : ");

                            System.out.println("3.  Middle Initial: ");

                            System.out.println("4.  Address : ");

                            System.out.println("5.  Sex : ");

                            System.out.println("6.  Birthday : ");

                            System.out.println("7.  Salary : ");

                            System.out.println("8. Manager SSN");

                            int selection = in.nextInt();
                            if (selection == 1) {
                                System.out.println("Enter New employee First Name : ");
                                efirstname = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set firstname = '" + efirstname + "' where SSN= '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 2) {
                                System.out.println("Enter New employee Last Name : ");
                                elastname = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set lastname = '" + elastname + "' where SSN = '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 3) {
                                System.out.println("Enter New employee Middle initial : ");
                                emiddleinitial = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set middleinitial = '" + emiddleinitial + "' where SSN= '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 4) {
                                System.out.println("Enter New employee address : ");
                                address = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set address = '" + address + "' where SSN = '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 5) {
                                System.out.println("Enter New employee sex : ");
                                sex = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set sex = '" + sex + "' where SSN = '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 6) {
                                System.out.println("Enter New employee birthday : ");
                                birthday = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set birthday = '" + birthday + "' where SSN = '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 7) {
                                System.out.println("Enter New employee salary : ");
                                salary = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set salary = '" + salary + "' where SSN = '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 8) {
                                System.out.println("Enter New employee manager_SSN : ");
                                manager_SSN = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update employee set manager_SSN = '" + manager_SSN + "' where SSN = '" + SSN + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Wrong Input");
                            }
                            break;
                        } else if (choice4 == 4) {
                            System.out.println("Delete a employee by SSN");
                            System.out.println("Enter SSN : ");
                            SSN = str.nextLine();
                            try {
                                Connection conn = getDBConnection();
                                String deleteq = "delete from employee where SSN= '" + SSN + "'";
                                PreparedStatement deletest = conn.prepareStatement(deleteq);
                                deletest.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else {
                            System.out.println("Invalid Selection");
                        }
                    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                    

                case 4:
                    String orderID,
                     employee_ssn,
                     orderstudentID,
                     bookISBN,
                     accCode,
                     ordertype,
                     quantity,
                     borrow_date,
                     return_date;
                    while (true) {
                        System.out.println("1. Insert order");
                        System.out.println("2. Select by Order ID");
                        System.out.println("3. Select all orders");
                        System.out.println("4. Update order records");
                        System.out.println("5. Delete orders");
                        System.out.print("Enter a choice: ");
                        int choice5 = in.nextInt();

                        if (choice5 == 1) {

                            System.out.println("Inserting New Data");
                            System.out.println("Please enter the following values");

                            System.out.println("Enter a Order ID");
                            orderID = str.nextLine();
                            System.out.println("Enter employee SSN");
                            employee_ssn = str.nextLine();
                            System.out.println("Enter Student ID");
                            orderstudentID = str.nextLine();
                            System.out.println("Enter Book ISBN");
                            bookISBN = str.nextLine();
                            System.out.println("Enter accessory code");
                            accCode = str.nextLine();
                            System.out.println("Enter a order type : ");
                            ordertype = str.nextLine();
                            System.out.println("Enter quantity : ");
                            quantity = str.nextLine();
                            System.out.println("Enter borrow_date");
                            borrow_date = str.nextLine();
                            System.out.println("Enter return_date");
                            return_date = str.nextLine();

                            try {
                                //insert data into a table of a database/schema
                                Connection conn = getDBConnection();
                                String insertion = "insert into orderbooks(orderID, employee_SSN, studentID, bookISBN, accCode, Order_type, quantity, borrow_date, return_date) "
                                        + "values ('" + orderID + "','" + employee_ssn + "','" + orderstudentID + "','" + bookISBN + "','" + accCode + "','" + ordertype + "','" + quantity + "','" + borrow_date + "','" + return_date + "')";

                                PreparedStatement insertstatement = conn.prepareStatement(insertion);
                                insertstatement.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } else if (choice5 == 2) {
                            System.out.println("Making A Selection");

                            System.out.println("Enter a orderID");
                            orderID = str.nextLine();

                            try {
                                Connection conn = getDBConnection();
                                String selectq = "select * from orderbooks where orderID like '%" + orderID + "%'";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("|%-35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s |%n", "OrderID ", "Employee SSN", "StudentID", "ISBN", "ACode", "OrderType", "Quantity", "Borrow Date", "Return Date");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("|%-35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s |%n", rs.getString("orderID"), rs.getString("employee_SSN"), rs.getString("studentID"), rs.getString("bookISBN"), rs.getString("accCode"), rs.getString("Order_type"), rs.getString("quantity"), rs.getString("borrow_date"), rs.getString("return_date"));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } else if (choice5 == 3) {
                            System.out.println("Selecting All");

                            try {
                                //Select all from student
                                Connection conn = getDBConnection();
                                String selectq = "select * from orderbooks";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("|%-35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s |%n", "OrderID ", "Employee SSN", "StudentID", "ISBN", "ACode", "OrderType", "Quantity", "Borrow Date", "Return Date");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("|%-35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s | %35s |%n", rs.getString("orderID"), rs.getString("employee_SSN"), rs.getString("studentID"), rs.getString("bookISBN"), rs.getString("accCode"), rs.getString("Order_type"), rs.getString("quantity"), rs.getString("borrow_date"), rs.getString("return_date"));

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        } else if (choice5 == 4) {
                            System.out.println("Updating Records");
                            System.out.println("Enter a orderId to update an Order record: ");
                            orderID = str.nextLine();

                            System.out.println("Select order details to Update: ");

                            System.out.println("1.  employee_SSN : ");

                            System.out.println("2.  studentID : ");

                            System.out.println("3.  ISBN: ");

                            System.out.println("4.  accessory code : ");

                            System.out.println("5.  order type : ");

                            System.out.println("6.  quantity : ");

                            System.out.println("7.  borrowdate : ");

                            System.out.println("8. returndate");

                            int selection = in.nextInt();
                            if (selection == 1) {
                                System.out.println("Enter New  employee ssn : ");
                                employee_SSN = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set employee_SSN = '" + employee_SSN + "' where orderID= '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 2) {
                                System.out.println("Enter New studentID : ");
                                studentID = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set studentID = '" + studentID + "' where orderID = '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 3) {
                                System.out.println("Enter New ISBN : ");
                                bookISBN = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set ISBN = '" + bookISBN + "' where orderID= '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 4) {
                                System.out.println("Enter New orderbooks Accessory code : ");
                                accCode = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set accCode = '" + accCode + "' where orderID = '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 5) {
                                System.out.println("Enter New orderbooks order type : ");
                                ordertype = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set Order_type = '" + ordertype + "' where orderID = '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 6) {
                                System.out.println("Enter New orderbooks quantity : ");
                                quantity = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set quantity = '" + quantity + "' where orderID = '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 7) {
                                System.out.println("Enter New orderbooks borrow_date : ");
                                borrow_date = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set borrow_date = '" + borrow_date + "' where orderID = '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 8) {
                                System.out.println("Enter New orderbooks return_date : ");
                                return_date = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update orderbooks set return_date = '" + return_date + "' where orderID = '" + orderID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (choice5 == 5) {
                                System.out.println("Delete a order by orderID");
                                System.out.println("Enter orderID : ");
                                orderID = str.nextLine();
                                try {
                                    Connection conn = getDBConnection();
                                    String deleteq = "delete from orderbooks where orderID= '" + orderID + "'";
                                    PreparedStatement deletest = conn.prepareStatement(deleteq);
                                    deletest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Invalid Selection");
                            }
                        }
                    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                    

                case 5:
                    String PUBpublisherID,
                     name,
                     publisheraddress,
                     location;

                    while (true) {
//                        System.out.println("1. Insert publisher Details");
                        System.out.println("1. Select a publisher");
                        System.out.println("2. Select all publishers");
                        System.out.println("3. Update publisher Details");
                        System.out.println("4. Delete publisher Details");
                        System.out.print("Enter a choice: ");
                        int choice6 = in.nextInt();

//                        if (choice6 == 1) {
//
//                            System.out.println("Inserting New Data");
//                            System.out.println("Please enter the following values");
//
//                            System.out.println("Enter a publisherID : ");
//                            PUBpublisherID = str.nextLine();
//                            System.out.println("Enter publisher name : ");
//                            name = str.nextLine();
//                            System.out.println("Enter  publisher address :  ");
//                            publisheraddress = str.nextLine();
//                            System.out.println("Enter location: ");
//                            location = str.nextLine();
//
//                            try {
//                                //insert data into a table of a database/schema
//                                Connection conn = getDBConnection();
//                                String insertion = "insert into publisher(publisherID, publishername, address, location) "
//                                        + "values ('" + PUBpublisherID + "','" + name + "','" + publisheraddress + "','" + location + "')";
//
//                                PreparedStatement insertstatement = conn.prepareStatement(insertion);
//                                insertstatement.executeUpdate();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        } 
                         if (choice6 == 1) {
                            System.out.println("Making A Selection");

                            System.out.println("Enter a publisherID");
                            PUBpublisherID = str.nextLine();

                            try {
                                Connection conn = getDBConnection();
                                String selectq = "select * from publisher where publisherID like '%" + PUBpublisherID + "%'";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s |%n", "PublisherID", "Name", "Address", "Location");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s |%n", rs.getString("publisherID"), rs.getString("publishername"), rs.getString("address"), rs.getString("location"));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else if (choice6 == 2) {
                            System.out.println("Selecting All");

                            try {
                                //Select all from student
                                Connection conn = getDBConnection();
                                String selectq = "select * from publisher";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s |%n", "PublisherID", "Name", "Address", "Location");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s |%n", rs.getString("publisherID"), rs.getString("publishername"), rs.getString("address"), rs.getString("location"));

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        } else if (choice6 == 3) {
                            System.out.println("Updating Records");
                            System.out.println("Enter a publisherID to update an publisher record: ");
                            PUBpublisherID = str.nextLine();

                            System.out.println("Select publisher details to Update: ");

                            System.out.println("1.  Name : ");

                            System.out.println("2.  Address : ");

                            System.out.println("3.  Location : ");

                            int selection = in.nextInt();
                            if (selection == 1) {
                                System.out.println("Enter New publisher Name : ");
                                name = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update publisher set publishername = '" + name + "' where publisherID= '" + PUBpublisherID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 2) {
                                System.out.println("Enter New publisher address : ");
                                publisheraddress = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update publisher set address = '" + publisheraddress + "' where publisherID = '" + PUBpublisherID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 3) {
                                System.out.println("Enter New publisher location : ");
                                location = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update publisher set location = '" + location + "' where publisherID= '" + PUBpublisherID + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Wrong Input");
                            }
                            break;

                        } else if (choice6 == 4) {
                            System.out.println("Delete a publisher by publisherID");
                            System.out.println("Enter publisherID : ");
                            PUBpublisherID = str.nextLine();
                            try {
                                Connection conn = getDBConnection();
                                String deleteq = "delete from publisher where publisherID= '" + PUBpublisherID + "'";
                                PreparedStatement deletest = conn.prepareStatement(deleteq);
                                deletest.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else {
                            System.out.println("Invalid Selection");
                        }
                    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                case 6:
                    String assn,
                     afirstname,
                     alastname,
                     amiddleinitial,
                     email,
                     apublisherID;

                    while (true) {
//                        System.out.println("1. Insert Author Details");
                        System.out.println("1. Select a Author");
                        System.out.println("2. Select all Authors");
                        System.out.println("3. Update Author Details");
                        System.out.println("4. Delete Author Details");
                        System.out.print("Enter a choice: ");
                        int choice7 = in.nextInt();

//                        if (choice7 == 1) {
//
//                            System.out.println("Inserting New Data");
//                            System.out.println("Please enter the following values");
//
//                            System.out.println("Enter a SSN : ");
//                            assn = str.nextLine();
//                            System.out.println("Enter First Name : ");
//                            afirstname = str.nextLine();
//                            System.out.println("Enter  Last Name :  ");
//                            alastname = str.nextLine();
//                            System.out.println("Enter Middle initial: ");
//                            amiddleinitial = str.nextLine();
//                            System.out.println("Enter an email");
//                            email = str.nextLine();
//                            System.out.println("Enter a publisherID");
//                            apublisherID = str.nextLine();
//
//                            try {
//                                //insert data into a table of a database/schema
//                                Connection conn = getDBConnection();
//                                String insertion = "insert into author(SSN, firstname, lastname, middleinitial, email) "
//                                        + "values ('" + assn + "','" + afirstname + "','" + alastname + "','" + amiddleinitial + "','" + email + "','" + apublisherID + "')";
//
//                                PreparedStatement insertstatement = conn.prepareStatement(insertion);
//                                insertstatement.executeUpdate();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        } 
                         if (choice7 == 1) {
                            System.out.println("Making A Selection");

                            System.out.println("Enter a SSN");
                            assn = str.nextLine();

                            try {
                                Connection conn = getDBConnection();
                                String selectq = "select * from author where SSN like '%" + assn + "%'";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", "SSN", "FirstName", "LastName", "MiddleInitial", "Email", "PublisherID");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", rs.getString("SSN"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("middleinitial"), rs.getString("email"), rs.getString("publisherID"));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else if (choice7 == 2) {
                            System.out.println("Selecting All");

                            try {
                                //Select all from student
                                Connection conn = getDBConnection();
                                String selectq = "select * from author";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", "SSN", "FirstName", "LastName", "MiddleInitial", "Email", "PublisherID");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", rs.getString("SSN"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("middleinitial"), rs.getString("email"), rs.getString("publisherID"));

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        } else if (choice7 == 3) {
                            System.out.println("Updating Records");
                            System.out.println("Enter a SSN to update an author record: ");
                            assn = str.nextLine();

                            System.out.println("Select author details to Update: ");

                            System.out.println("1.  First Name : ");

                            System.out.println("2.  Last Name : ");

                            System.out.println("3.  Middle Initial: ");

                            System.out.println("4.  Email : ");

                            System.out.println("5. PublisherID: ");

                            int selection = in.nextInt();
                            if (selection == 1) {
                                System.out.println("Enter New author First Name : ");
                                afirstname = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update author set firstname = '" + afirstname + "' where SSN= '" + assn + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 2) {
                                System.out.println("Enter New author Last Name : ");
                                alastname = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update author set lastname = '" + alastname + "' where SSN = '" + assn + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 3) {
                                System.out.println("Enter New author Middle initial : ");
                                amiddleinitial = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update author set middleinitial = '" + amiddleinitial + "' where SSN= '" + assn + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 4) {
                                System.out.println("Enter New author email : ");
                                email = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update author set email = '" + email + "' where SSN = '" + assn + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 5) {
                                System.out.println("Enter New author publisherID : ");
                                apublisherID = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update author set publisherID = '" + apublisherID + "' where SSN = '" + assn + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Wrong Input");
                            }
                            break;

                        } else if (choice7 == 4) {
                            System.out.println("Delete a author by SSN");
                            System.out.println("Enter SSN : ");
                            assn = str.nextLine();
                            try {
                                Connection conn = getDBConnection();
                                String deleteq = "delete from author where SSN= '" + assn + "'";
                                PreparedStatement deletest = conn.prepareStatement(deleteq);
                                deletest.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } else {
                            System.out.println("Invalid Selection");
                        }
                    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                case 7:
                    String Acode,
                     accstudentID,
                     Aclass,
                     aborrow_date,
                     areturn_date;
                    while (true) {
//                        System.out.println("1. Insert Accessory Details");
                        System.out.println("1. Select an accessory");
                        System.out.println("2. Select all accessories");
                        System.out.println("3. Update accessory Details");
                        System.out.println("4. Delete accessory Details");
                        System.out.print("Enter a choice: ");
                        int choice8 = in.nextInt();

//                        if (choice8 == 1) {
//
//                            System.out.println("Inserting New Data");
//                            System.out.println("Please enter the following values");
//
//                            System.out.println("Enter Accesory code : ");
//                            Acode = str.nextLine();
//                            System.out.println("Enter StudentID: ");
//                            accstudentID = str.nextLine();
//                            System.out.println("Enter class : ");
//                            Aclass = str.nextLine();
//                            System.out.println("Enter borrow date :  ");
//                            aborrow_date = str.nextLine();
//                            System.out.println("Enter return date: ");
//                            areturn_date = str.nextLine();
//
//                            try {
//                                //insert data into a table of a database/schema
//                                Connection conn = getDBConnection();
//                                String insertion = "insert into accessories(Acode, studentID ,class, borrow_date, return_date) "
//                                        + "values ('" + Acode + "','" + accstudentID + "','" + Aclass + "','" + aborrow_date + "','" + areturn_date + "')";
//
//                                PreparedStatement insertstatement = conn.prepareStatement(insertion);
//                                insertstatement.executeUpdate();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            break;
//                        } 
                        if (choice8 == 1) {
                            System.out.println("Making A Selection");

                            System.out.println("Enter a accessory code");
                            Acode = str.nextLine();

                            try {
                                Connection conn = getDBConnection();
                                String selectq = "select * from accessories where Acode like '%" + Acode + "%'";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", "Acode", "StudentID", "Class", "Borrow_date", "Return_date");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", rs.getString("Acode"), rs.getString("studentID"), rs.getString("class"), rs.getString("borrow_date"), rs.getString("return_date"));

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        } else if (choice8 == 2) {
                            System.out.println("Selecting All");

                            try {
                                //Select all from student
                                Connection conn = getDBConnection();
                                String selectq = "select * from accessories";
                                PreparedStatement selectst = conn.prepareStatement(selectq);
                                rs = selectst.executeQuery();

                                System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", "Acode", "StudentID", "Class", "Borrow_date", "Return_date");
                                System.out.printf("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------%n");

                                while (rs.next()) {
                                    System.out.printf("| %-35s | %-35s | %-35s | %-35s | %-35s | %-35s |%n", rs.getString("Acode"), rs.getString("studentID"), rs.getString("class"), rs.getString("borrow_date"), rs.getString("return_date"));

                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            break;
                        } else if (choice8 == 3) {
                            System.out.println("Updating Records");
                            System.out.println("Enter Code to update the accessory record: ");
                            Acode = str.nextLine();

                            System.out.println("Select accessory details to Update: ");

                            System.out.println("1. StudentID");

                            System.out.println("2. accessory class : ");

                            System.out.println("3. accessory borrow date : ");

                            System.out.println("4. accessory return date: ");

                            int selection = in.nextInt();

                            if (selection == 1) {
                                System.out.println("Enter New StudentID : ");
                                accstudentID = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update accessories set studentID = '" + accstudentID + "' where Acode= '" + Acode + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                            } else if (selection == 2) {
                                System.out.println("Enter New accessory class : ");
                                Aclass = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update accessories set class = '" + Aclass + "' where Acode= '" + Acode + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 3) {
                                System.out.println("Enter New accessory borrow date : ");
                                borrow_date = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update accessories set borrow_date = '" + borrow_date + "' where Acode= '" + Acode + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else if (selection == 4) {
                                System.out.println("Enter New accessory return_date : ");
                                return_date = str.nextLine();

                                try {
                                    Connection conn = getDBConnection();
                                    String updateq = "update accessories set return_date = '" + return_date + "' where Acode= '" + Acode + "'";
                                    PreparedStatement updatest = conn.prepareStatement(updateq);
                                    updatest.executeUpdate();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                System.out.println("Wrong Input");
                            }
                            break;

                        } else if (choice8 == 4) {
                            System.out.println("Delete a accessories by Accessory code");
                            System.out.println("Enter Accessory code : ");
                            Acode = str.nextLine();
                            try {
                                Connection conn = getDBConnection();
                                String deleteq = "delete from accessories where Acode= '" + Acode + "'";
                                PreparedStatement deletest = conn.prepareStatement(deleteq);
                                deletest.executeUpdate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        } else {
                            System.out.println("Invalid Selection");
                        }
                    }
                    break;

                case 8:
                    again = false;
                    break;
            }
        }
    }
}
