package cecs323.jdbcproject.pojos;

/**
 * The WritingGroup class is a POJO (Plain Old Java Object) that is used to
 * compartmentalize the attributes of an entry in the WritingGroups table.
 * 
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

	/**
	 * Creates a new WritingGroup object with the given name, head writer, year
	 * and subject.
	 * 
	 * @param name the name of the writing group
	 * @param head the head writer of the writing group
	 * @param year the year in which the group formed
	 * @param subj the subject that the group writes about
	 */
	public WritingGroup(String name, String head, String year, String subj) {
		this.groupName = name;
		this.headWriter = head;
		this.yearFormed = year;
		this.subject = subj;
	}
}
