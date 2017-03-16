package cecs323.jdbcproject.interconnect;

import cecs323.jdbcproject.pojos.WritingGroup;
import java.sql.SQLException;
import java.util.List;

/**
 * An interface for a class that can perform the required operations on the
 * WritingGroups table.
 * 
 * @author Nicholas
 */
public interface WritingGroupOperations {
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
     * table with the given name.
     */
    public WritingGroup getWritingGroup(String name) throws SQLException;
    
    /**
     * Inserts a row in the WritingGroups table with the attribute values stored
     * in the given {@link WritingGroup} object.
     * 
     * @param group the writing group to insert
     * @throws SQLException if an exception is thrown while trying to insert
     */
    public void insertWritingGroup(WritingGroup group) throws SQLException;
    
    /**
     * Deletes the writing group with the given name.
     * 
     * @param groupName the name of the writing group to delete
     * @throws SQLException if an exception is raised while deleting
     */
    public void deleteWritingGroup(String groupName) throws SQLException;
}
