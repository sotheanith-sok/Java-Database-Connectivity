package cecs323.jdbcproject.interconnect;

import cecs323.jdbcproject.pojos.Book;
import cecs323.jdbcproject.pojos.BookDetail;
import cecs323.jdbcproject.pojos.BookKeyData;
import cecs323.jdbcproject.pojos.Publisher;
import cecs323.jdbcproject.pojos.WritingGroup;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * The DatabaseOperations interface defines the interface for a class that can
 * perform operations on the database.
 * 
 * @author Nicholas Utz
 */
public interface DatabaseOperations {
	/**
	 * Returns a {@link List} of Strings, containing the titles of all of the
	 * entries in the Books table.
	 * 
	 * @return list of book titles
	 */
	public List<String> listBookTitles() throws SQLException;

	/**
	 * Returns a {@link List} of {@link Book}s, containing all of the
	 * information in the Books table.
	 * 
	 * @return list of all books
	 */
	public List<Book> listBooks() throws SQLException;

	/**
	 * Returns a {@link Book} storing all of the data pertaining to the book
	 * with the given title, written by the WritingGroup with the given name.
	 * 
	 * @param title the title of the book to fetch
	 * @param writingGroup the writing group that wrote the book to fetch
	 * @return book info
	 */
	public Book getBook(String title, String writingGroup) throws SQLException;

	/**
	 * Returns a {@link Book} storing all of the data pertaining to the book
	 * with the primary key data.
	 * 
	 * @param key the key data of the book to fetch
	 * @return book info
	 */
	public Book getBook(BookKeyData key) throws SQLException;

	/**
	 * Returns a {@link BookDetail} object, containing all available data
	 * pertaining to the book with the given title, written by the given writing
	 * group, including the publisher and writing group.
	 * 
	 * @param title the title of the book to fetch
	 * @param writingGroup the writing group name of the book to fetch
	 * @return book details
	 */
	public BookDetail getBookDetails(String title, String writingGroup) throws SQLException;

	/**
	 * Returns a {@link BookDetail} object, storing all of the data pertaining
	 * to the book with the given primary key data, and the publisher and
	 * writing group of the book.
	 * 
	 * @param key the key of the book to fetch
	 * @return book details
	 */
	public BookDetail getBookDetails(BookKeyData key) throws SQLException;

	/**
	 * Inserts the given book into the books table.
	 * 
	 * @param book the book to insert
	 * @throws SQLException if a SQLException occurs while attempting to insert
	 */
	public void insertBook(Book book) throws SQLIntegrityConstraintViolationException, SQLException;

	/**
	 * Replaces the given old publisher name with a new one, for all books
	 * published by the old publisher.
	 * 
	 * @param oldName the name of the publisher to be replaced
	 * @param newName the name of the publisher replacing the old one
	 * @throws SQLException if a SQLException occurs while updating
	 */
	public void replacePublisher(String oldName, String newName)
			throws SQLIntegrityConstraintViolationException, SQLException;

	/**
	 * Deletes the book with the given title and writing group from the books
	 * table.
	 * 
	 * @param title the title of the book to delete
	 * @param writingGroup the writing group who wrote the book to delete
	 * @throws SQLException if a SQLException occurs while deleting
	 */
	public void deleteBook(String title, String writingGroup) throws SQLException;

	/**
	 * Deletes the book with the given primary key data from the books table.
	 * 
	 * @param key the primary key data of the book to delete
	 * @throws SQLException if a SQL exception occurs while deleting
	 */
	public void deleteBook(BookKeyData key) throws SQLException;

	/**
	 * Returns a {@link List} of {@link String}s, representing the names of all
	 * of the entries in the Publishers table.
	 * 
	 * @return list of publisher names
	 */
	public List<String> listPublisherNames() throws SQLException;

	/**
	 * Returns a {@link List} of {@link Publisher}s, representing all of the
	 * data in the Publishers table.
	 * 
	 * @return list of publishers
	 */
	public List<Publisher> listPublishers() throws SQLException;

	/**
	 * Returns a {@link Publisher} object, storing all the data stored for the
	 * publisher with the given name in the Publishers table.
	 * 
	 * @param name the name of the publisher to fetch
	 * @return publisher info
	 */
	public Publisher getPublisher(String name) throws SQLException;

	/**
	 * Inserts a new entry into the Publishers table, using the given
	 * {@link Publisher} as a source of attribute data.
	 * 
	 * @param info the info of the publisher to insert
	 * @throws java.sql.SQLException if a SQLException occurs while inserting
	 */
	public void insertPublisher(Publisher info) throws SQLIntegrityConstraintViolationException, SQLException;

	/**
	 * Deletes the Publisher with the given name from the publishers table.
	 * 
	 * @param name the name of the publisher to delete
	 * @throws SQLIntegrityConstraintViolationException if there is a Book
	 *             dependent on the named publisher
	 * @throws SQLException if there is a problem deleting the publisher
	 */
	public void deletePublisher(String name) throws SQLIntegrityConstraintViolationException, SQLException;

	/**
	 * Returns a {@link List} of Strings, containing the names of all of the
	 * WritingGroups in the WritingGroups table.
	 * 
	 * @return list of writing groups' names
	 */
	public List<String> listWritingGroupNames() throws SQLException;

	/**
	 * Returns a {@link List} of {@link WritingGroup}s, representing all of the
	 * data in the WritingGroups table.
	 * 
	 * @return list of WritingGroups
	 */
	public List<WritingGroup> listWritingGroups() throws SQLException;

	/**
	 * Returns a {@link WritingGroup} object containing the data stored in the
	 * WritingGroups table for the WritingGroup with the given name.
	 * 
	 * @param name the name of the WritingGroup to fetch
	 * @return writing group info
	 * @throws NullPointerException if there is no entry in the writing groups
	 *             table with the given name.
	 */
	public WritingGroup getWritingGroup(String name) throws SQLException;

	/**
	 * Inserts a row in the WritingGroups table with the attribute values stored
	 * in the given {@link WritingGroup} object.
	 * 
	 * @param group the writing group to insert
	 * @throws SQLException if an exception is thrown while trying to insert
	 */
	public void insertWritingGroup(WritingGroup group) throws SQLIntegrityConstraintViolationException, SQLException;

	/**
	 * Deletes the writing group with the given name.
	 * 
	 * @param groupName the name of the writing group to delete
	 * @throws SQLException if an exception is raised while deleting
	 */
	public void deleteWritingGroup(String groupName) throws SQLIntegrityConstraintViolationException, SQLException;
}
