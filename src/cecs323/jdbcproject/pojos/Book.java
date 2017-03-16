package cecs323.jdbcproject.pojos;

/**
 * The Book class is a POJO (Plain Old Java Object) that is used to encapsulate
 * the attributes of an entry in the Books table.
 * 
 * @author Nicholas
 */
public class Book {
    /**
     * The name of the {@link WritingGroup} that wrote this Book.
     * 
     * Must be no more than 30 characters in length, and the name
     * of an existing <code>WritingGroup</code>.
     */
    public String groupName;
    
    /**
     * The title of this Book.
     * 
     * Must be no more than 40 characters in length, and unique within
     * the {@link WritingGroup} that wrote this Book.
     * 
     * @see #groupName
     */
    public String bookTitle;
    
    /**
     * The name of the {@link Publisher} that published this Book.
     * 
     * Must be no more than 30 characters in length, ane equivalent to the name
     * of an existing <code>Publisher</code>.
     */
    public String publisherName;
    
    /**
     * The year in which this Book was published.
     * 
     * Must be exactly 4 characters in length.
     */
    public String yearPublished;
    
    /**
     * The number of pages in this Book.
     */
    public int numberPages;
    
    /**
     * Creates a new Book object with the given values.
     * 
     * @param title the title of the book
     * @param groupName the name of the WritingGroup that wrote this book
     * @param pubName the name of the publisher that published this book
     * @param year the year that this book was published
     * @param pages the number of pages in this book
     */
    public Book(String title, String groupName, String pubName, String year, int pages) {
        this.bookTitle = title;
        this.groupName = groupName;
        this.publisherName = pubName;
        this.numberPages = pages;
    }
}
