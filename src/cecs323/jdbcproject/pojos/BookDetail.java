
package cecs323.jdbcproject.pojos;

/**
 * The BookDetail class combines the {@link Book}, {@link Publisher}, and
 * {@link WritingGroup} classes into one object.
 * 
 * @author Nicholas
 */
public class BookDetail {
    /**
     * The {@link Book} that this <code>BookDetails</code> stores details of.
     */
    public final Book book;
    
    /**
     * The {@link Publisher} of {@link #book}.
     */
    public final Publisher publisher;
    
    /**
     * The {@link WritingGroup} that wrote {@link #book}.
     */
    public final WritingGroup writingGroup;
    
    /**
     * Creates a new <code>BookDetail</code> for the given {@link Book} with the
     * given {@link Publisher} and {@link WritingGroup}.
     * @param b the book to detail
     * @param p the publisher of the book
     * @param wg the writing group of the book
     */
    public BookDetail(Book b, Publisher p, WritingGroup wg) {
        this.book = b;
        this.publisher = p;
        this.writingGroup = wg;
    }
}
