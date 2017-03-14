package cecs323.jdbcproject.pojos;

/**
 * The WritingGroup class is a POJO (Plain Old Java Object) that is used
 * to compartmentalize the attributes of an entry in the WritingGroups
 * table.
 * @author Nicholas
 */
public class WritingGroup {
    /**
     * The name of the WritingGroup.
     * 
     * Must be no more than 30 characters in length.
     */
    public String groupName;
    
    /**
     * The name of the head writer in this WritingGroup.
     * 
     * Must be no more than 30 characters in length.
     */
    public String headWriter;
    
    /**
     * The year that this WritingGroup was formed.
     * 
     * Must be exactly 4 characters in length.
     */
    public String yearFormed;
    
    /**
     * The subject that this WritingGroup writes about.
     * 
     * Must be no more than 50 characters in length.
     */
    public String subject;
}
