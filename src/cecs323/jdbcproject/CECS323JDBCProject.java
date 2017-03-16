
package cecs323.jdbcproject;
import java.sql.*;
import java.util.*;

//Import Package
import cecs323.jdbcproject.interconnect.*;
import cecs323.jdbcproject.pojos.*;

/**
 * 
 * @author Sotheanith Sok
 *
 */
public class CECS323JDBCProject {


    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input= new Scanner(System.in);
        //Input data if required
        String DBNAME="";
        String USER="";
        String PASS="";
        
        //Database URL
        String DB_URL="jdbc:derby://localhost:1527/"+DBNAME+";user="+USER+";password="+PASS;
        Connection conn=null;
        //Statement stmt=null;
        boolean done=false;
        try{
            //Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            //Open Connection
            System.out.println("Connecting to database...");
            conn=DriverManager.getConnection(DB_URL);
            
            do{
                int choice= menu(input);
                switch(choice){
                    case 1:
                        System.out.println("-Listing All Writing Groups-");
                        break;
                    case 2:
                        System.out.println("-Listing all the data for a group-");
                        break;
                    case 3:
                        System.out.println("-Listing all publishers-");
                        break;
                    case 4:
                        System.out.println("-Listing all the data for publisher-");
                        break;
                    case 5:
                        System.out.println("-Listing all book titles-");
                        break;
                    case 6:
                        System.out.println("-Listing all the data for a book-");
                        break;
                    case 7:
                        System.out.println("-Inserting a new book-");
                        break;
                    case 8:
                        System.out.println("-Inserting a new publisher-");
                        break;
                    case 9:
                        System.out.println("-Removing a book-");
                        break;
                    case 10:
                        System.out.println("-Exiting-");
                        done=true;
                        break;
                    default:
                        System.out.println("-Invalid Input-");
                        break;        
                }
            }while(!done);
            //Close resource when there isn't any error.
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //Error then need to close resources.
            try{
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    
    
    /**
     * Print menu and get user input
     * @param in Scanner for keyboard
     * @return valid user choice
     */
    public static int menu(Scanner in){
        boolean done=false;
        int choice = 0; 
        System.out.println("--Menu--");
        System.out.println("1. List all writing groups ");
        System.out.println("2. List all the data for a group");
        System.out.println("3. List all publishers");
        System.out.println("4. List all the data for a publisher");
        System.out.println("5. List all book titles");
        System.out.println("6. List all the data for a book");
        System.out.println("7. Insert a new book");
        System.out.println("8. Insert a new publisher");
        System.out.println("9. Remove a book");
        System.out.println("10. Exit");
        while(!done){
            try{
                choice=in.nextInt();
                if (!(choice>=1&&choice<=10)){
                    throw new NumberFormatException();
                }
                done=true;
            }catch(InputMismatchException ime){
                in.next();
                System.out.print("Invalid Input. Re-enter: ");
            }catch(NumberFormatException nfe){
                System.out.print("Invalid Input. Re-enter: ");
            } 
        }
        return choice;
    }
    
    /**
     * List all data related to all writing groups
     * @param w WritingGroupOperations object.
     */
    public static void listAllWritingGroups(WritingGroupOperations w){
        List<WritingGroup> list=w.listWritingGroups();
        System.out.printf("%-10s%-10s%-10s%-10s", "GroupName","HeadWriter","YearFormed","Subject");
        for (int i=0; i<list.size();i++){
        	System.out.printf("%-10s%-10s%-10s%-10s",list.get(i).groupName,list.get(i).headWriter,list.get(i).subject,list.get(i).yearFormed);
        }
        
    }
    
    /**
     * List all data for a specific WritingGroup (4)
     * @param w WritingGroupOperations object
     * @param in Scanner for keyboard
     */
    public static void listDataForAWritingGroup(WritingGroupOperations w,Scanner in){
		int i=printDataAndGetInput(w,in);
		List<WritingGroup>list=w.listWritingGroups();
		System.out.printf("%-10s%-10s%-10s%-10s", "GroupName","HeadWriter","YearFormed","Subject");
        System.out.printf("%-10s%-10s%-10s%-10s",list.get(i).groupName,list.get(i).headWriter,list.get(i).subject,list.get(i).yearFormed);		
    }
    
    /**
     * List information related to all publishers (4)
     * @param p PublisherOperations object
     */
    public static void listAllPublisher(PublisherOperations p){
		List<Publisher> list=p.listPublishers();
		System.out.printf("%-10s%-10s%-10s%-10s", "PublisherName","PublisherAddress","PublisherPhone","PublisherEmail");
        for (int i=0; i<list.size();i++){
        	System.out.printf("%-10s%-10s%-10s%-10s",list.get(i).publisherName,list.get(i).publisherAddress,list.get(i).publisherPhone,list.get(i).publisherEmail);
        }
    }
    
   
    /**
     * List all the book title
     * @param b
     */
    public static void listAllBookTitle(BookOperations b){
    	List<String> list=b.listBookTitles();
    	System.out.printf("%-10s%","BookTitle");
        for (int i=0; i<list.size();i++){
        	System.out.printf("%-10s",list.get(i));
        }
    }
    
    /**
     * List all data for a specific book including related writing group and publisher.
     * @param b BookOperations object
     * @param in Scanner for keyboard
     */
    public static void listDataForABook(BookOperations b, Scanner in){
		System.out.print("Enter GroupName:");
		String GroupName=in.nextLine();
		System.out.print("Enter BookTitle: ");
		String BookTitle=in.nextLine();
		Book book= b.getBook(new BookKeyData(GroupName, BookTitle));
    }
    public static void insertABook(){
		
    }
    public static void insertAPublisher(){
        
    }
    public static void removeABook(){
		
    }
    
    /**
     * Print option for user to choose
     * @param o Object of types: BookOperations, PublisherOperations, or WritingGroupOperations.
     * @param in Scanner from keyboard
     * @return user choice
     */
    public static int printDataAndGetInput(Object o, Scanner in){
    	List<String> list;
    	boolean done=false;
    	int choice=-1;
    	//Get the list
    	if (o instanceof WritingGroupOperations){
    		list= ((WritingGroupOperations)o).listWritingGroupNames();
    	}else{
    		list= ((PublisherOperations)o).listPublisherNames();
    	}
    	
    	//Print the list
    	for(int i=0;i<list.size();i++){
    		System.out.println((i+1)+" . "+list.get(i));
    	}
    	
    	while(!done){
            try{
                choice=in.nextInt();
                if (!(choice>=1&&choice<=list.size())){
                    throw new NumberFormatException();
                }
                done=true;
            }catch(InputMismatchException ime){
                in.next();
                System.out.print("Invalid Input. Re-enter: ");
            }catch(NumberFormatException nfe){
                System.out.print("Invalid Input. Re-enter: ");
            } 
        }
    	return choice-1;
    }
}
