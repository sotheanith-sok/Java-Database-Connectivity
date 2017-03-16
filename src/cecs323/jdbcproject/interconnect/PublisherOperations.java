package cecs323.jdbcproject.interconnect;

import cecs323.jdbcproject.pojos.Publisher;
import java.sql.SQLException;
import java.util.List;

/**
 * An interface for a class that can perform all of the required operations on
 * the Publishers table.
 * 
 * @author Nicholas
 */
public interface PublisherOperations {
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
    public void insertPublisher(Publisher info) throws SQLException;
}
