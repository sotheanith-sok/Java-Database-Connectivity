package cecs323.jdbcproject.pojos;

/**
 * The Publisher class is a POJO (Plain Old Java Object) used to encapsulate
 * the attributes of an entry in the Publishers table.
 * 
 * @author Nicholas
 */
public class Publisher {
    /**
     * The name of this Publisher.
     * 
     * Must be no more than 30 characters in length.
     */
    public String publisherName;
    
    /**
     * The address of this Publisher.
     * 
     * Must be no more than 30 characters in length.
     */
    public String publisherAddress;
    
    /**
     * The phone number of this Publisher.
     * 
     * Must be no more than 20 characters in length.
     */
    public String publisherPhone;
    
    /**
     * The email address of this Publisher.
     * 
     * Must be no more than 50 characters in length.
     */
    public String publisherEmail;
}
