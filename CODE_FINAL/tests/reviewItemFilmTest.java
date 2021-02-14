package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;


import exceptions.BadEntryException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

/**
 * Tests for the SocialNetwork.<i>reviewItemFilm()</i> method.
 * 
 * @author C LE GRUIEC, E LE DUC
 * @version V1.0 - May 2020
 */


public class reviewItemFilmTest {

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
	private static int reviewItemFilmBadEntryException(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {

		
		try {
			sn.reviewItemFilm(login, password, title, mark, comment); // Try to add a review
			// Reaching this point means that no exception was thrown by reviewItemFilm
			System.out.println("Test " + testId + " : " + errorMessage); // display the error message
																		
			return 1; // and return the "error" value
		} catch (BadEntryException e) { // BadEntry exception was thrown by reviewItemFilm : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : BadEntry was thrown"); // Display a specific error message
			return 0; // return "error" value
			
		} catch (Exception e) { // An exception was thrown by reviewItemFilm, but it was not the expected exception BadEntry
			
			System.out.println("Test " + testId + " : unexpected exception. "+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
	}
	
	
	
	private static int[] reviewItemFilmMoy(ISocialNetwork sn) {
		float moyDepart=0;
		float moyCourant=0;
		//0 test //1err
		int testErr[] = {0,0};
		
		System.out.println("\n-- cas normal ajout --\n");
		//ajout d'une review, sans pb normalement
		try {
			moyCourant=sn.reviewItemFilm("michel", "1234", "ouioui", 5, "pas mal");
			if(moyCourant==5) {
				System.out.println("Test 8.1 : Ajout review OK");
				testErr[0]++;
			}
			else {
				System.out.println("Err 8.1: echec ajout review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		
		//Le meme user ajoute une review sur le meme fil, ça doit modif l'ancienne
		try {
			moyCourant=sn.reviewItemFilm("michel", "1234", "ouioui", 2, "pas mal");
			if(moyCourant==2) {
				System.out.println("Test 8.2 : Modif review existante OK");
				testErr[0]++;
			}
			else {
				System.out.println("Err 8.2: echec ajout review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Un second user ajoute une note la moy doit changer
		try {
			moyCourant=sn.reviewItemFilm("robert", "1234", "ouioui", 4, "pas mal");
			if(moyCourant==3) {
				System.out.println("Test 8.3 : AJout seconde review OK");
				testErr[0]++;
				//sn.toString();
			}
			else {
				System.out.println("Err 8.4: echec ajout review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		//Le meme user ajoute une review sur le meme fil, ça doit modif l'ancienne
		try {
			moyCourant=sn.reviewItemFilm("robert", "1234", "ouioui", 0, "pas mal");
			if(moyCourant==1) {
				System.out.println("Test 8.3 : Modif seconde review OK");
				testErr[0]++;
				//sn.toString();
			}
			else {
				System.out.println("Err 8.4: echec modif seconde review");
				testErr[1]++;
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		
		System.out.println("\n-- NotMember --\n");
		//ajou avec un user qui n'existe pas
		try {
			moyCourant=sn.reviewItemFilm("alain", "1234", "ouioui", 4, "pas mal");
			if(moyCourant==2) {
				System.out.println("Err 8.4 : Ajout d'un film avec compte faux");
				testErr[1]++;
			}
			
			
		} catch (NotMemberException e) { // BadEntry exception was thrown by reviewItemFilm : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test 8.4 : NotMemberException was thrown");
			testErr[0]++;
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		
		//ajou avec un film qui n'existe pas
		System.out.println("\n-- NoItem --\n");
		try {
			moyCourant=sn.reviewItemFilm("michel", "1234", "existepas", 4, "pas mal");
			System.out.println("Err 8.4 : Ajout d'un review avec film faux");
			
			
		} catch (NotMemberException e) { // BadEntry exception was thrown by reviewItemFilm : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test 8.4 : NotMemberException was thrown");
			testErr[1]++;
			
		} catch (NotItemException e) { // BadEntry exception was thrown by reviewItemFilm : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test 8.4 : NotItemException was thrown");
			testErr[0]++;
			
		} catch (Exception e) {
			System.out.println(e);
			testErr[1]++;
		}
		
		
		
		
		
		

		return testErr;

	}
	
	

	

	

	/**
	 * <i>reviewItemFilm()</i> main test :
	 * <ul>
	 * <li>check if a member can add a review on Film</li>
	 * <li>check if incorrect parameters cause reviewItemFilm() to throw BadEntryException</li>
	 * </ul>
	 * 
	 * @return a summary of the performed tests
	 */
	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();
		int nbTests=0,nbErrors=0;
		
		//Ajout de reviews

		//je créer 2 user valide pour ajouter des films et j'ajoute 2 films:

		try {
			sn.addMember("michel", "1234", "Mon profile");
			sn.addMember("robert", "1234", "Mon profile");
			sn.addItemFilm("michel", "1234", "ouioui", "enfant", "director", "scenarist", 40);
			sn.addItemFilm("robert", "1234", "nonnon", "enfant", "director", "scenarist", 40);
		} catch (BadEntryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotMemberException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ItemFilmAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MemberAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		System.out.println("Testing reviewItemFilm()");
		
		//BadEntry
		System.out.println("\n-- BadEntry --\n");
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, null, "1234", "OuiOui", (float) 5.0, "trop bien", "7.1", "reviewItemFilm() doesn't reject null logins");
		
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "  ", "1234", "OuiOui", (float) 5.0, "trop bien", "7.2", "reviewItemFilm() doesn't reject logins invalid login (only spaces)");
		
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "login", null, "OuiOui", (float) 5.0, "trop bien", "7.3", "reviewItemFilm() doesn't reject null password");
		
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "login", "q", "OuiOui", (float) 5.0, "trop bien", "7.4", "reviewItemFilm() doesn't reject password with with less than 4 carate");
		
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "michel", "1234", null,  (float) 5.0, "trop bien", "7.5", "reviewItemFilm() doesn't reject null title");
		
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "michel", "1234", "    ",  (float) 5.0, "trop bien", "7.6", "reviewItemFilm() doesn't reject invalid title (only spaces)");
				
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "michel", "1234", "ouioui",  -1, "trop bien", "7.7", "reviewItemFilm() doesn't reject negative mark");
		
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "michel", "1234", "ouioui",  10, "trop bien", "7.8", "reviewItemFilm() doesn't reject more than 5 mark");
		
		nbTests++;
		nbErrors += reviewItemFilmBadEntryException(sn, "michel", "1234", "ouioui",  10, null, "7.9", "reviewItemFilm() doesn't reject null comment");
		
				

		
		//Test d'ajout d'une review:
		int testErr[]=reviewItemFilmMoy(sn);
		nbTests+=testErr[0];
		nbErrors+=testErr[1];
		
		
		
		
		
		
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("\nreviewItemFilm : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in reviewItemFilm test code - Can't return valuable test results");
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
