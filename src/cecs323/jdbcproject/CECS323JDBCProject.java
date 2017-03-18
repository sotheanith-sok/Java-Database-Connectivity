
package cecs323.jdbcproject;

import java.sql.*;
import java.util.*;

//Import Package
import cecs323.jdbcproject.interconnect.*;
import cecs323.jdbcproject.pojos.*;
import impl.*;

/**
 * <h1>CECS323JDBCProject</h1> This is program is designed to be operate in
 * conjunction with a database of books, publisher, and writing group.
 *
 * @author Sotheanith Sok
 * @version 1.0
 * @since 03-16-2017
 */
public class CECS323JDBCProject {

	public static void main(String[] args) {
		// TODO code application logic here
		Scanner in = new Scanner(System.in);
		// Input data if required
		String DBNAME = "";
		String USER = "";
		String PASS = "";

		// Database URL
		String DB_URL = "jdbc:derby://localhost:1527/" + DBNAME + ";user=" + USER + ";password=" + PASS;
		Connection conn = null;
		// Statement stmt=null;
		boolean done = false;
		try {
			// Register JDBC driver
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();

			// Open Connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL);

			// Create Datebase object
			DatabaseOperations d = OpsImplFactory.getOperationsImpl(conn);
			do {
				int choice = menu(in);
				switch (choice) {
				case 1:
					System.out.println("-Listing All Writing Groups-");
					listAllWritingGroups(d);
					break;
				case 2:
					System.out.println("-Listing all the data for a group-");
					listDataForAWritingGroup(d, in);
					break;
				case 3:
					System.out.println("-Listing all publishers-");
					listAllPublisher(d);
					break;
				case 4:
					System.out.println("-Listing all the data for publisher-");
					printDataForAPublisher(d, in);
					break;
				case 5:
					System.out.println("-Listing all book titles-");
					listAllBookTitle(d);
					break;
				case 6:
					System.out.println("-Listing all the data for a book-");
					listDataForABook(d, in);
					break;
				case 7:
					System.out.println("-Inserting a new book-");
					insertABook(d, in);
					break;
				case 8:
					System.out.println("-Inserting a new publisher-");
					insertAPublisher(d, in);
					break;
				case 9:
					System.out.println("-Removing a book-");
					removeABook(d, in);
					break;
				case 10:
					System.out.println("-Exiting-");
					done = true;
					break;
				default:
					System.out.println("-Invalid Input-");
					break;
				}
			} while (!done);
			// Close resource when there isn't any error.
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Error then need to close resources.
			try {
				conn.close();
				in.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	/**
	 * Print menu and get user input
	 * 
	 * @param in Scanner for keyboard
	 * @return valid user choice
	 */
	public static int menu(Scanner in) {
		boolean done = false;
		int choice = 0;
		System.out.println("--Menu--");
		System.out.println("1. List all writing groups ");
		System.out.println("2. List all the data for a group");
		System.out.println("3. List all publishers");
		System.out.println("4. List all the data for a publisher");
		System.out.println("5. List all book titles");
		System.out.println("6. List all the data for a book");
		System.out.println("7. Insert a new book");
		System.out.println("8. Insert a new publisher");
		System.out.println("9. Remove a book");
		System.out.println("10. Exit");
		while (!done) {
			try {
				choice = in.nextInt();
				if (!(choice >= 1 && choice <= 10)) {
					throw new NumberFormatException();
				}
				done = true;
			} catch (InputMismatchException ime) {
				in.next();
				System.out.print("Invalid Input. Re-enter: ");
			} catch (NumberFormatException nfe) {
				System.out.print("Invalid Input. Re-enter: ");
			}
		}
		return choice;
	}

	/**
	 * List all data related to all writing groups
	 * 
	 * @param w WritingGroupOperations object.
	 * @throws SQLException
	 */
	public static void listAllWritingGroups(DatabaseOperations w) {
		try {
			List<WritingGroup> list = w.listWritingGroups();
			System.out.printf("%-10s%-10s%-10s%-10s", "GroupName", "HeadWriter", "YearFormed", "Subject");
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("%-10s%-10s%-10s%-10s", list.get(i).groupName, list.get(i).headWriter,
						list.get(i).subject, list.get(i).yearFormed);
			}
		} catch (SQLException s) {
			System.out.println("Error: Code 1");
		}

	}

	/**
	 * List all data for a specific WritingGroup (4)
	 * 
	 * @param w WritingGroupOperations object
	 * @param in Scanner for keyboard
	 * @throws SQLException
	 */
	public static void listDataForAWritingGroup(DatabaseOperations w, Scanner in) {
		try {
			int i = printDataAndGetInput(w, in, 1);
			List<WritingGroup> list = w.listWritingGroups();
			System.out.printf("%-10s%-10s%-10s%-10s", "GroupName", "HeadWriter", "YearFormed", "Subject");
			System.out.printf("%-10s%-10s%-10s%-10s", list.get(i).groupName, list.get(i).headWriter,
					list.get(i).subject, list.get(i).yearFormed);
		} catch (SQLException s) {
			System.out.println("Error: Code 2");
		}

	}

	/**
	 * List information related to all publishers (4)
	 * 
	 * @param p PublisherOperations object
	 * @throws SQLException
	 */
	public static void listAllPublisher(DatabaseOperations p) {
		try {
			List<Publisher> list = p.listPublishers();
			System.out.printf("%-10s%-10s%-10s%-10s", "PublisherName", "PublisherAddress", "PublisherPhone",
					"PublisherEmail");
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("%-10s%-10s%-10s%-10s", list.get(i).publisherName, list.get(i).publisherAddress,
						list.get(i).publisherPhone, list.get(i).publisherEmail);
			}
		} catch (SQLException s) {
			System.out.println("Error: Code 3");
		}
	}

	/**
	 * List all the book title
	 * 
	 * @param b
	 * @throws SQLException
	 */
	public static void listAllBookTitle(DatabaseOperations b) {
		try {
			List<String> list = b.listBookTitles();
			System.out.printf("%-10s%", "BookTitle");
			for (int i = 0; i < list.size(); i++) {
				System.out.printf("%-10s", list.get(i));
			}
		} catch (SQLException s) {
			System.out.println("Error: Code 4");
		}

	}

	/**
	 * List all data for a specific book including related writing group and
	 * publisher.
	 * 
	 * @param b BookOperations object
	 * @param in Scanner for keyboard
	 * @throws SQLException
	 */
	public static void listDataForABook(DatabaseOperations b, Scanner in) {
		try {
			System.out.print("Enter GroupName:");
			String GroupName = in.nextLine();
			System.out.print("Enter BookTitle: ");
			String BookTitle = in.nextLine();
			Book book = b.getBook(new BookKeyData(BookTitle, GroupName));
			BookDetail bookDetail = b.getBookDetails(new BookKeyData(BookTitle, GroupName));
			System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s/n", "BookTitle", "YearPublished",
					"NumberPages", "GroupName", "HeadWriter", "YearFormed", "Subject", "PublisherName",
					"PublisherAddress", "PublisherPhone", "PublisherEmail");
			System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s/n", book.bookTitle,
					book.yearPublished, book.numberPages, bookDetail.writingGroup.groupName,
					bookDetail.writingGroup.headWriter, bookDetail.writingGroup.yearFormed,
					bookDetail.writingGroup.subject, bookDetail.publisher.publisherName,
					bookDetail.publisher.publisherAddress, bookDetail.publisher.publisherPhone,
					bookDetail.publisher.publisherEmail);
		} catch (SQLException s) {
			System.out.println("Error: Code 5");
		}

	}

	/**
	 * Insert a new book into database
	 * 
	 * @param d DatabaseOpeartions object
	 * @param in Scanner for keyboard
	 * @throws SQLIntegrityConstraintViolationException
	 * @throws SQLException
	 */
	public static void insertABook(DatabaseOperations d, Scanner in) {
		try {
			System.out.print("Enter BookTitle");
			String bookTitle = in.nextLine();
			System.out.print("Enter YearPublished");
			String yearPublished = in.nextLine();
			System.out.print("Enter NumberPages");
			int numberPages = in.nextInt();
			System.out.print("Enter GroupName");
			String groupName = in.nextLine();
			System.out.print("Enter PublisherName");
			String publisherName = in.nextLine();
			d.insertBook(new Book(bookTitle, groupName, publisherName, yearPublished, numberPages));
		} catch (SQLException s) {
			System.out.println("Error: Code 6");
		}

	}

	/**
	 * Insert and replace old publisher with a new one
	 * 
	 * @param d DatabaseOperations objects
	 * @param in Scanner for keyboard
	 * @throws SQLIntegrityConstraintViolationException
	 * @throws SQLException
	 */
	public static void insertAPublisher(DatabaseOperations d, Scanner in) throws SQLException {
		try {
			System.out.println("Enter OldPublisherName:");
			String oldPub = in.nextLine();
			System.out.println("-Get New Publisher Info-");
			System.out.println("Enter PublisherName");
			String publisherName = in.nextLine();
			System.out.println("Enter PublisherAddress");
			String publisherAddress = in.nextLine();
			System.out.println("Enter PublisherPhone");
			String publisherPhone = in.nextLine();
			System.out.println("Enter PublisherEmail");
			String publisherEmail = in.nextLine();
			d.insertPublisher(new Publisher(publisherName, publisherAddress, publisherPhone, publisherEmail));
			d.replacePublisher(oldPub, publisherName);
			d.deletePublisher(oldPub);
		} catch (SQLException s) {
			System.out.println("Error: Code 7");
		}
	}

	/**
	 * Remove book based on title and group name
	 * 
	 * @param d DatabaseOperations object
	 * @param in Scanner for keyboard
	 * @throws SQLException
	 */
	public static void removeABook(DatabaseOperations d, Scanner in) {
		try {
			System.out.print("Enter BookTitle");
			String bookTitle = in.nextLine();
			System.out.print("Enter GroupName");
			String groupName = in.nextLine();
			d.deleteBook(new BookKeyData(bookTitle, groupName));
		} catch (SQLException s) {
			System.out.println("Error: Code 8");
		}

	}

	/**
	 * Print option for user to choose
	 * 
	 * @param o Object of types: BookOperations, PublisherOperations, or
	 *            WritingGroupOperations.
	 * @param in Scanner from keyboard
	 * @return user choice
	 * @throws SQLException
	 */
	public static int printDataAndGetInput(Object o, Scanner in, int k) {
		int choice = -1;
		try {
			List<String> list;
			boolean done = false;
			// Get the list
			if (k == 1) {
				list = ((DatabaseOperations) o).listWritingGroupNames();
			} else {
				list = ((DatabaseOperations) o).listPublisherNames();
			}

			// Print the list
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + " . " + list.get(i));
			}

			while (!done) {
				try {
					choice = in.nextInt();
					if (!(choice >= 1 && choice <= list.size())) {
						throw new NumberFormatException();
					}
					done = true;
				} catch (InputMismatchException ime) {
					in.next();
					System.out.print("Invalid Input. Re-enter: ");
				} catch (NumberFormatException nfe) {
					System.out.print("Invalid Input. Re-enter: ");
				}
			}
		} catch (SQLException s) {
			System.out.println("Error: Code 2");
		}

		return choice - 1;
	}

	/**
	 * Print all data for a publisher
	 * 
	 * @param d DatabaseOperations object
	 */
	public static void printDataForAPublisher(DatabaseOperations d, Scanner in) {

	}
}
