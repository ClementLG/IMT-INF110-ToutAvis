package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;


import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

/**
 * Tests for the SocialNetwork.<i>reviewItemBook()</i> method.
 * 
 * @author C LE GRUIEC, E LE DUC
 * @version V1.0 - May 2020
 */

public class reviewItemBookTest {

	/**
	 * Add in the <i>SocialNetwork</i> a new review for a book on behalf of a
	 * specific member.</br> If this member has already given a review for this
	 * same book before, the new review replaces the previous one.
	 * 
	 * @param login
	 *            login of the member adding the review
	 * @param password
	 *            password of the member adding the review
	 * @param title
	 *            the reviewed book's title
	 * @param mark
	 *            the mark given by the member for this book
	 * @param comment the comment given by the member for this book
	 *            
	 * @return mean of the marks for this book
	 */
	private static int reviewItemBookBadEntryException(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {

		
		try {
			sn.reviewItemBook(login, password, title, mark, comment); // Try to add a review
			// Reaching this point means that no exception was thrown by reviewItemBook
			System.out.println("Test " + testId + " : " + errorMessage); // display the error message
																		
			return 1; // and return the "error" value
		} catch (BadEntryException e) { // BadEntry exception was thrown by reviewItemBook : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : BadEntry was thrown"); // Display a specific error message
			return 0; // return "error" value
			
		} catch (Exception e) { // An exception was thrown by reviewItemBook, but it was not the expected exception BadEntry
			
			System.out.println("Test " + testId + " : unexpected exception. "+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
	}
	
	
	
	private static int[] reviewItemBookMoy(ISocialNetwork sn) {
		float moyDepart=0;
		float moyCourant=0;
		//0 test //1err
		int testErr[] = {0,0};
		
		System.out.println("\n-- Cas Normal Ajout --\n");
		//ajout d'une review, sans pb normalement
		try {
			moyCourant=sn.reviewItemBook("michel", "1234", "ouioui", 5, "pas mal");
			if(moyCourant==5) {
				System.out.println("Test 10.1 : Ajout review OK");
				testErr[0]++;
			}
			else {
				System.out.println("Err 10.1: echec ajout review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		
		//Le meme user ajoute une review sur le meme fil, ça doit modif l'ancienne
		try {
			moyCourant=sn.reviewItemBook("michel", "1234", "ouioui", 2, "pas mal");
			if(moyCourant==2) {
				System.out.println("Test 10.2 : Modif review existante OK");
				testErr[0]++;
			}
			else {
				System.out.println("Err 10.2: echec ajout review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Un second user ajoute une note la moy doit changer
		try {
			moyCourant=sn.reviewItemBook("robert", "1234", "ouioui", 4, "pas mal");
			if(moyCourant==3) {
				System.out.println("Test 10.3 : AJout seconde review OK");
				testErr[0]++;
			}
			else {
				System.out.println("Err 10.3: echec ajout review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		//Le meme user ajoute une review sur le meme fil, ça doit modif l'ancienne
		try {
			moyCourant=sn.reviewItemBook("robert", "1234", "ouioui", 0, "pas mal");
			if(moyCourant==1) {
				System.out.println("Test 10.4 : Modif seconde review OK");
				testErr[0]++;
			}
			else {
				System.out.println("Err 10.4: echec modif seconde review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		//ajou avec un user qui n'existe pas
		System.out.println("\n-- NotMember --\n");
		try {
			moyCourant=sn.reviewItemBook("alain", "1234", "ouioui", 4, "pas mal");
			if(moyCourant==2) {
				System.out.println("Err 10.5 : Ajout d'un Book avec compte faux");
				testErr[1]++;
			}
			
			
		} catch (NotMemberException e) { // BadEntry exception was thrown by reviewItemBook : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test 10.5 : NotMemberException was thrown");
			testErr[0]++;
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		System.out.println("\n-- NO item --\n");
		//ajou avec un Book qui n'existe pas
		try {
			moyCourant=sn.reviewItemBook("michel", "1234", "existepas", 4, "pas mal");
			System.out.println("Err 10.6 : Ajout d'un review avec Book faux");
			
			
		} catch (NotMemberException e) { // BadEntry exception was thrown by reviewItemBook : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test 10.6 : NotMemberException was thrown");
			testErr[1]++;
			
		} catch (NotItemException e) { // BadEntry exception was thrown by reviewItemBook : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test 10.6 : NotItemException was thrown");
			testErr[0]++;
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		
		
		
		
		

		return testErr;

	}
	
	

	

	

	/**
	 * <i>reviewItemBook()</i> main test :
	 * <ul>
	 * <li>check if a member can add a review on Book</li>
	 * <li>check if incorrect parameters cause reviewItemBook() to throw BadEntryException</li>
	 * </ul>
	 * 
	 * @return a summary of the performed tests
	 */
	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();
		int nbTests=0,nbErrors=0;
		
		System.out.println("Testing reviewItemBook()");
		
		//je créer 2 user valide pour ajouter des Books et j'ajoute 2 Books:

		try {
			sn.addMember("michel", "1234", "Mon profile");
			sn.addMember("robert", "1234", "Mon profile");
			sn.addItemBook("michel", "1234", "ouioui", "enfant", "author", 40);
			sn.addItemBook("robert", "1234", "nonnon", "enfant", "author", 40);
		} catch (BadEntryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotMemberException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ItemBookAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MemberAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//BadEntry
		System.out.println("\n-- BadEntry --\n");
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, null, "1234", "OuiOui", (float) 5.0, "trop bien", "9.1", "reviewItemBook() doesn't reject null logins");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "  ", "1234", "OuiOui", (float) 5.0, "trop bien", "9.2", "reviewItemBook() doesn't reject logins invalid login (only spaces)");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "login", null, "OuiOui", (float) 5.0, "trop bien", "9.3", "reviewItemBook() doesn't reject null password");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "login", "q", "OuiOui", (float) 5.0, "trop bien", "9.4", "reviewItemBook() doesn't reject password with with less than 4 carate");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "michel", "1234", null,  (float) 5.0, "trop bien", "9.5", "reviewItemBook() doesn't reject null title");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "michel", "1234", "    ",  (float) 5.0, "trop bien", "9.6", "reviewItemBook() doesn't reject invalid title (only spaces)");
				
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "michel", "1234", "ouioui",  -1, "trop bien", "9.9", "reviewItemBook() doesn't reject negative mark");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "michel", "1234", "ouioui",  10, "trop bien", "9.10", "reviewItemBook() doesn't reject more than 5 mark");
		
		nbTests++;
		nbErrors += reviewItemBookBadEntryException(sn, "michel", "1234", "ouioui",  10, null, "9.11", "reviewItemBook() doesn't reject null comment");
		
				

		//Ajout de reviews

		
		
		//Test d'ajout d'une review:
		int testErr[]=reviewItemBookMoy(sn);
		nbTests+=testErr[0];
		nbErrors+=testErr[1];
		
		
		
		
		
		
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("\nreviewItemBook : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in reviewItemBook test code - Can't return valuable test results");
			return null;
			}
		}
 
	
	
	/**
	 * Launches test()
	 * @param args not used
	 */
	public static void main(String[] args) {
		test();
	}
}
