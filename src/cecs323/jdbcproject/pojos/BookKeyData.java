package cecs323.jdbcproject.pojos;

/**
 * The BookKeyData class is a POJO (Plain Old Java Object) that stores the
 * primary key attributes of an entry in the Books table.
 * 
 * @author Nicholas
 */
public class BookKeyData {
    /**
     * The title of the book.
     */
    public String bookTitle;
    
    /**
     * The name of the writing group that wrote the book.
     */
    public String writingGroup;
    
    /**
     * Constructor for BookKeyData
     * @param bookTitle title of a book
     * @param writingGroup name of a writing group
     */
    public BookKeyData (String bookTitle, String writingGroup){
    	this.bookTitle=bookTitle;
    	this.writingGroup=writingGroup;
    }
}
