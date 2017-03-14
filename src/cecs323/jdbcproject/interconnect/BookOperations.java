package cecs323.jdbcproject.interconnect;

import cecs323.jdbcproject.pojos.Book;
import cecs323.jdbcproject.pojos.BookDetail;
import cecs323.jdbcproject.pojos.BookKeyData;
import java.sql.SQLException;
import java.util.List;

/**
 * Defines the interface of a class that can perform the required operations
 * on the Books table.
 * 
 * @author Nicholas
 */
public interface BookOperations {
    /**
     * Returns a {@link List} of Strings, containing the titles of all of the
     * entries in the Books table.
     * 
     * @return list of book titles
     */
    public List<String> listBookTitles();
    
    /**
     * Returns a {@link List} of {@link Book}s, containing all of the 
     * information in the Books table.
     * 
     * @return list of all books
     */
    public List<Book> listBooks();
    
    /**
     * Returns a {@link Book} storing all of the data pertaining to the
     * book with the given title, written by the WritingGroup with the
     * given name.
     * 
     * @param title the title of the book to fetch
     * @param writingGroup the writing group that wrote the book to fetch
     * @return book info
     */
    public Book getBook(String title, String writingGroup);
    
    /**
     * Returns a {@link Book} storing all of the data pertaining to the
     * book with the primary key data.
     * 
     * @param key the key data of the book to fetch
     * @return book info
     */
    public Book getBook(BookKeyData key);
    
    /**
     * Returns a {@link BookDetail} object, containing all available data 
     * pertaining to the book with the given title, written by the given
     * writing group, including the publisher and writing group.
     * 
     * @param title the title of the book to fetch
     * @param writingGroup the writing group name of the book to fetch
     * @return book details
     */
    public BookDetail getBookDetails(String title, String writingGroup);
    
    /**
     * Returns a {@link BookDetail} object, storing all of the data pertaining
     * to the book with the given primary key data, and the publisher and 
     * writing group of the book.
     * 
     * @param key the key of the book to fetch
     * @return book details
     */
    public BookDetail getBookDetails(BookKeyData key);
    
    /**
     * Inserts the given book into the books table.
     * 
     * @param book the book to insert
     * @throws SQLException if a SQLException occurs while attempting to insert
     */
    public void insertBook(Book book) throws SQLException;
    
    /**
     * Replaces the given old publisher name with a new one, for all books
     * published by the old publisher.
     * 
     * @param oldName the name of the publisher to be replaced
     * @param newName the name of the publisher replacing the old one
     * @throws SQLException if a SQLException occurs while updating
     */
    public void replacePublisher(String oldName, String newName) throws SQLException;
    
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
}
