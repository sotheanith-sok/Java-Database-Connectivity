package impl;

import cecs323.jdbcproject.interconnect.DatabaseOperations;
import cecs323.jdbcproject.pojos.Book;
import cecs323.jdbcproject.pojos.BookDetail;
import cecs323.jdbcproject.pojos.BookKeyData;
import cecs323.jdbcproject.pojos.Publisher;
import cecs323.jdbcproject.pojos.WritingGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * The OpsImplFactory class is a factory for classes that implement the
 * *Operations interfaces defined in {@link cecs323.jdbcproject.interconnect}.
 *
 * @author Nicholas Utz
 */
public class OpsImplFactory {

	public static DatabaseOperations getOperationsImpl(Connection con) throws SQLException {
		return new OpsImpl(con);
	}
}

class OpsImpl implements DatabaseOperations {

	private final Connection con;

	private static final String SQL_GET_TITLES = "SELECT booktitle FROM books";
	private static final String SQL_GET_BOOKS = "SELECT * FROM books";
	private static final String SQL_GET_BOOK = "SELECT * FROM books WHERE booktitle=? AND groupname=?";
	private static final String SQL_INSERT_BOOK = "INSERT INTO books (groupname, booktitle, "
			+ "publishername, yearpublished, numberpages) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE_PUBLISHERS = "UPDATE books SET publishername=? WHERE publishername=?";
	private static final String SQL_DELETE_BOOK = "DELETE FROM books WHERE booktitle=? AND groupname=?";
	private static final String SQL_GET_PUBLISHER_NAMES = "SELECT publishername FROM publishers";
	private static final String SQL_GET_PUBLISHERS = "SELECT * FROM publishers";
	private static final String SQL_GET_PUBLISHER = "SELECT * FROM publishers WHERE publishername=?";
	private static final String SQL_INSERT_PUBLISHER = "INSERT INTO publishers (publishername, "
			+ "publisheraddress, publisherphone, publisheremail) VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_PUBLISHER = "DELETE FROM publishers WHERE publishername=?";
	private static final String SQL_GET_GROUP_NAMES = "SELECT groupname FROM writinggroups";
	private static final String SQL_GET_GROUPS = "SELECT * FROM writinggroups";
	private static final String SQL_GET_WRITING_GROUP = "SELECT * FROM writinggroups WHERE groupname=?";
	private static final String SQL_INSERT_WRITING_GROUP = "INSERT INTO writinggroups (groupname, "
			+ "headwriter, yearformed, subject) VALUES (?,?,?,?)";
	private static final String SQL_DELETE_WRITING_GROUP = "DELETE FROM writinggroups WHERE groupname=?";

	private final PreparedStatement PSTMT_GET_BOOK;
	private final PreparedStatement PSTMT_INSERT_BOOK;
	private final PreparedStatement PSTMT_UPDATE_PUBLISHERS;
	private final PreparedStatement PSTMT_DELETE_BOOK;
	private final PreparedStatement PSTMT_GET_PUBLISHER;
	private final PreparedStatement PSTMT_INSERT_PUBLISHER;
	private final PreparedStatement PSTMT_DELETE_PUBLISHER;
	private final PreparedStatement PSTMT_GET_WRITING_GROUP;
	private final PreparedStatement PSTMT_INSERT_WRITING_GROUP;
	private final PreparedStatement PSTMT_DELETE_WRITING_GROUP;

	public OpsImpl(Connection con) throws SQLException {
		this.con = con;
		this.PSTMT_GET_BOOK = con.prepareStatement(SQL_GET_BOOK);
		this.PSTMT_INSERT_BOOK = con.prepareStatement(SQL_INSERT_BOOK);
		this.PSTMT_UPDATE_PUBLISHERS = con.prepareStatement(SQL_UPDATE_PUBLISHERS);
		this.PSTMT_DELETE_BOOK = con.prepareStatement(SQL_DELETE_BOOK);
		this.PSTMT_GET_PUBLISHER = con.prepareStatement(SQL_GET_PUBLISHER);
		this.PSTMT_INSERT_PUBLISHER = con.prepareStatement(SQL_INSERT_PUBLISHER);
		this.PSTMT_DELETE_PUBLISHER = con.prepareStatement(SQL_DELETE_PUBLISHER);
		this.PSTMT_GET_WRITING_GROUP = con.prepareStatement(SQL_GET_WRITING_GROUP);
		this.PSTMT_INSERT_WRITING_GROUP = con.prepareStatement(SQL_INSERT_WRITING_GROUP);
		this.PSTMT_DELETE_WRITING_GROUP = con.prepareStatement(SQL_DELETE_WRITING_GROUP);
	}

	@Override
	public List<String> listBookTitles() throws SQLException {
		Statement stmt = this.con.createStatement();
		List<String> titles = new LinkedList<>();
		ResultSet results = stmt.executeQuery(SQL_GET_TITLES);

		while (results.next()) {
			titles.add(results.getString(1));
		}

		results.close();
		stmt.close();

		return titles;
	}

	@Override
	public List<Book> listBooks() throws SQLException {
		Statement stmt = this.con.createStatement();
		List<Book> books = new LinkedList<>();
		ResultSet results = stmt.executeQuery(SQL_GET_BOOKS);

		while (results.next()) {
			books.add(new Book(results.getString(2), results.getString(1), results.getString(3), results.getString(4),
					results.getInt(5)));
		}

		results.close();
		stmt.close();

		return books;
	}

	@Override
	public Book getBook(String title, String writingGroup) throws SQLException {
		this.PSTMT_GET_BOOK.setString(1, title);
		this.PSTMT_GET_BOOK.setString(2, writingGroup);
		ResultSet results = this.PSTMT_GET_BOOK.executeQuery();

		if (results.next()) {
			Book book = new Book(results.getString(2), results.getString(1), results.getString(3), results.getString(4),
					results.getInt(5));
			results.close();
			return book;

		} else {
			results.close();
			return null;
		}
	}

	@Override
	public Book getBook(BookKeyData key) throws SQLException {
		return getBook(key.bookTitle, key.writingGroup);
	}

	@Override
	public BookDetail getBookDetails(String title, String writingGroup) throws SQLException {
		Book book = getBook(title, writingGroup);

		if (book == null) {
			return null;
		}

		Publisher pub = getPublisher(book.publisherName);
		WritingGroup wg = getWritingGroup(writingGroup);

		if (pub == null || wg == null) {
			return null;
		}

		return new BookDetail(book, pub, wg);
	}

	@Override
	public BookDetail getBookDetails(BookKeyData key) throws SQLException {
		return getBookDetails(key.bookTitle, key.writingGroup);
	}

	@Override
	public void insertBook(Book book) throws SQLIntegrityConstraintViolationException, SQLException {
		if (!checkYearString(book.yearPublished)) {
			throw new IllegalArgumentException("Book yearPublished is inproperly formatted");
		}
		this.PSTMT_INSERT_BOOK.setString(1, book.groupName);
		this.PSTMT_INSERT_BOOK.setString(2, book.bookTitle);
		this.PSTMT_INSERT_BOOK.setString(3, book.publisherName);
		this.PSTMT_INSERT_BOOK.setString(4, book.yearPublished);
		this.PSTMT_INSERT_BOOK.setInt(5, book.numberPages);

		PSTMT_INSERT_BOOK.executeUpdate();
	}

	@Override
	public void replacePublisher(String oldName, String newName)
			throws SQLIntegrityConstraintViolationException, SQLException {
		this.PSTMT_UPDATE_PUBLISHERS.setString(1, newName);
		this.PSTMT_UPDATE_PUBLISHERS.setString(2, oldName);

		this.PSTMT_UPDATE_PUBLISHERS.executeUpdate();
	}

	@Override
	public void deleteBook(String title, String writingGroup) throws SQLException {
		this.PSTMT_DELETE_BOOK.setString(1, title);
		this.PSTMT_DELETE_BOOK.setString(2, writingGroup);
		this.PSTMT_DELETE_BOOK.executeUpdate();
	}

	@Override
	public void deleteBook(BookKeyData key) throws SQLException {
		deleteBook(key.bookTitle, key.writingGroup);
	}

	@Override
	public List<String> listPublisherNames() throws SQLException {
		Statement stmt = this.con.createStatement();
		List<String> pubs = new LinkedList<>();
		ResultSet results = stmt.executeQuery(SQL_GET_PUBLISHER_NAMES);

		while (results.next()) {
			pubs.add(results.getString(1));
		}

		results.close();
		stmt.close();

		return pubs;
	}

	@Override
	public List<Publisher> listPublishers() throws SQLException {
		Statement stmt = this.con.createStatement();
		List<Publisher> pubs = new LinkedList<>();
		ResultSet results = stmt.executeQuery(SQL_GET_PUBLISHERS);

		while (results.next()) {
			pubs.add(new Publisher(results.getString(1), results.getString(2), results.getString(3),
					results.getString(4)));
		}

		return pubs;
	}

	@Override
	public Publisher getPublisher(String name) throws SQLException {
		this.PSTMT_GET_PUBLISHER.setString(1, name);
		ResultSet results = this.PSTMT_GET_PUBLISHER.executeQuery();

		if (results.next()) {
			Publisher pub = new Publisher(results.getString(1), results.getString(2), results.getString(3),
					results.getString(4));
			results.close();
			return pub;

		} else {
			return null;
		}
	}

	@Override
	public void insertPublisher(Publisher info) throws SQLIntegrityConstraintViolationException, SQLException {
		PSTMT_INSERT_PUBLISHER.setString(1, info.publisherName);
		PSTMT_INSERT_PUBLISHER.setString(2, info.publisherAddress);
		PSTMT_INSERT_PUBLISHER.setString(3, info.publisherPhone);
		PSTMT_INSERT_PUBLISHER.setString(4, info.publisherEmail);
		PSTMT_INSERT_PUBLISHER.execute();
	}

	@Override
	public void deletePublisher(String name) throws SQLIntegrityConstraintViolationException, SQLException {
		PSTMT_DELETE_PUBLISHER.setString(1, name);
		PSTMT_DELETE_PUBLISHER.execute();
	}

	@Override
	public List<String> listWritingGroupNames() throws SQLException {
		Statement stmt = con.createStatement();
		List<String> names = new LinkedList<>();
		ResultSet results = stmt.executeQuery(SQL_GET_GROUP_NAMES);

		while (results.next()) {
			names.add(results.getString(1));
		}

		results.close();
		stmt.close();

		return names;
	}

	@Override
	public List<WritingGroup> listWritingGroups() throws SQLException {
		Statement stmt = con.createStatement();
		List<WritingGroup> groups = new LinkedList<>();
		ResultSet results = stmt.executeQuery(SQL_GET_GROUPS);

		while (results.next()) {
			groups.add(new WritingGroup(results.getString(1), results.getString(2), results.getString(3),
					results.getString(4)));
		}

		results.close();

		return groups;
	}

	@Override
	public WritingGroup getWritingGroup(String name) throws SQLException {
		this.PSTMT_GET_WRITING_GROUP.setString(1, name);
		ResultSet result = this.PSTMT_GET_WRITING_GROUP.executeQuery();

		if (result.next()) {
			WritingGroup group = new WritingGroup(result.getString(1), result.getString(2), result.getString(3),
					result.getString(4));
			result.close();
			return group;
		}

		return null;
	}

	@Override
	public void insertWritingGroup(WritingGroup group) throws SQLIntegrityConstraintViolationException, SQLException {
		this.PSTMT_INSERT_WRITING_GROUP.setString(1, group.groupName);
		this.PSTMT_INSERT_WRITING_GROUP.setString(2, group.headWriter);
		this.PSTMT_INSERT_WRITING_GROUP.setString(3, group.yearFormed);
		this.PSTMT_INSERT_WRITING_GROUP.setString(4, group.subject);
		this.PSTMT_INSERT_WRITING_GROUP.executeUpdate();
	}

	@Override
	public void deleteWritingGroup(String groupName) throws SQLIntegrityConstraintViolationException, SQLException {
		this.PSTMT_DELETE_WRITING_GROUP.setString(1, groupName);
		this.PSTMT_DELETE_WRITING_GROUP.executeUpdate();
	}

	/**
	 * Checks the given string is a valid year string, that is, exactly 4
	 * characters in length, all of which are digits.
	 *
	 * @param year the string to check
	 * @return is year valid
	 */
	public static boolean checkYearString(String year) {
		if (year.length() > 4) {
			return false;
		}
		for (int i = 0; i < year.length(); i++) {
			if (!Character.isDigit(year.charAt(i))) {
				return false;
			}
		}

		return true;
	}

}
