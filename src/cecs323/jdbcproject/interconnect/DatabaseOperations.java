package cecs323.jdbcproject.interconnect;

/**
 * The DatabaseOperations interface aggregates the {@link BookOperations},
 * {@link PublisherOperations}, and {@link WritingGroupOperations} interfaces.
 * 
 * @author Nicholas Utz
 */
public interface DatabaseOperations extends 
        BookOperations, PublisherOperations, WritingGroupOperations {
    
}
