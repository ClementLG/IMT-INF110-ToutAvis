package tests;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * Tests for the SocialNetwork.<i>addItemBook()</i> method.
 * 
 * @author C LE GRUIEC, E LE DUC
 * @version V1.0 - May 2020
 */


public class addItemBookTest {
	
	private static int addItemBookBadEntryTest(ISocialNetwork sn, String login, String password, String title, 
			String kind, String author, int nbPages,
			String testId, String errorMessage) {
		
		//System.out.println("addItemFilmBadEntryTest");
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages); 
			System.out.println("Err " + testId + " : " + errorMessage);
											
			return 1; 
		
		} catch (BadEntryException e) { 
			System.out.println("Test "+ testId+ " : BadEntryExeption was thrown " +e); 
			return 0; 
		
		} catch (NotMemberException e) { 
			System.out.println("Test "+ testId+ " : NotMemberException was thrown " +e);
			return 0;
		
		} catch (ItemBookAlreadyExistsException e) { 
			System.out.println("Test "+ testId+ " : ItemFilmAlreadyExistsxExeption was thrown" +e);
			return 0; 
		
		} catch (Exception e) { 
		
			System.out.println("Test " + testId + " : unexpected exception. "+ e);
			e.printStackTrace();
			return 1; 
		}
		
	}
	
	
	/*
	 * 
	 * Tests standards NotMemberException
	 * 
	 */
	
	private static int addItemBookNotMemberTest(ISocialNetwork sn, String login, String password, String title, 
												String kind, String author, int nbPages,
												String testId, String errorMessage) {

        try {
            sn.addItemBook(login, password, title, kind, author, nbPages);
            System.out.println("Err " + testId + " : " + errorMessage); // display the error message
            return 1; // and return the "error" value

        } catch (NotMemberException e) {
            System.out.println("Test " + testId + " : NotMember was thrown "+ e); // display the error message
            return 0;

        } catch (Exception e) { // An exception was thrown by AddItemFilm, but it was not the expected exception
                                // NotMember
            System.out.println("Err " + testId + " : unexpected exception. " + e); // Display a specific error message
            e.printStackTrace(); // Display contextual info about what happened
            return 1; // return error value
        }
    }
	
	/*
	 * 
	 * Tests AlreadyExist ou normal
	 * 
	 */
	
	private static int addItemBookTest(ISocialNetwork sn, String login, String password, String title, 
										String kind, String author, int nbPages,
										String testId) {
		
		int nbBooks = sn.nbBooks();
		// process this method
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			// No exception was thrown. That's a good start !
			if (sn.nbBooks() != nbBooks + 1) { // But the number of members hasn't changed accordingly
			System.out.println("Err " + testId+ " : the number of members (" + nbBooks+ ") was not incremented"); // Error message displayed
			return 1; // return error code
			} else
			System.out.println("Test" + testId+ " : Book ajouté avec succès, tot de film: "+sn.nbBooks());	
			return 0; // return success code : everything is OK, nothing to
				// display
		} catch (ItemBookAlreadyExistsException e) {
			System.out
			.println("Test " + testId + " : AlreadyExistexception  was thrown " + e +" - tot de film:"+sn.nbBooks());
			return 0; // return error code
			
		} catch (NotMemberException e) {
		System.out.println("Test" + testId + " : NotMemberexception  was thrown" + e); 
		return 0; 
		
	    }	catch (BadEntryException e) {
		System.out.println("Test " + testId + " : BadEntryExeption was thrown " + e); 
		return 0; // return error code
		
	    }	catch (Exception e) {
	    	System.out.println("Err " + testId + " : unexpected exception " + e);
	    	return 1;
		}

	}
	
	
	/**
	 * 
	 * TESTS
	 */
	
	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();
		int nbTests=0,nbErrors=0;
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();
		
		
		System.out.println("Testing addItemBook()");
		System.out.println("\n--BadEntry -- \n");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, null, "1234", "ouioui", "Enfant", "lauteur", 13, "5.1", "login non instancié");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "      ", "1234", "ouioui", "Enfant", "lauteur", 13, "5.2", "login ne contenant que des espaces");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", null, "ouioui", "Enfant", "lauteur", 13, "5.3", "password non instancié");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", "1     ", "ouioui", "Enfant", "lauteur", 13, "5.4", "password de moins de 4 caractères");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", "1234", null, "Enfant", "lauteur", 13, "5.5", "title non instancié");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", "1234", "     ", "Enfant", "lauteur", 13, "5.6", "title de moins de 1 caractères");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", "1234", "ouioui", null, "lauteur", 13, "5.7", "kind non instancié");
		
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", "1234", "ouioui", "Enfant", null, 13, "5.8", "auteur non instancié");

		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", "1234", "ouioui", "Enfant", "lauteur", 0, "5.9", "nb pages non instancié.");
				
		nbTests++;
		nbErrors+=addItemBookBadEntryTest(sn, "Michel", "1234", "ouioui", "Enfant", "lauteur", -1, "5.10", "nbPages négative");
		
		System.out.println("\n--AlreadyExist -- \n");
		
		//je créer un user valide pour ajouter des films:
		try {
			sn.addMember("michel", "1234", "Mon profile");
		} catch (BadEntryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MemberAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//j'ajoute 3 films correct
		
		
		nbTests++;
		nbErrors+=addItemBookTest(sn, "michel", "1234", "ouioui", "Enfant", "lauteur", 13, "6.1.1");
		nbTests++;
		nbErrors+=addItemBookTest(sn, "michel", "1234", "nonnon", "Enfant", "lauteur", 13, "6.1.2");
		nbTests++;
		nbErrors+=addItemBookTest(sn, "michel", "1234", "ouinon", "Enfant", "lauteur", 13, "6.1.3");
		
		//ajout d'un film existant
		nbTests++;
		nbErrors+=addItemBookTest(sn, "michel", "1234", "ouioui", "Enfant", "lauteur", 13, "6.2");
				
		//ajout d'un film existant mais avec des espaces et une casse différente
		nbTests++;
		nbErrors+=addItemBookTest(sn, "michel", "1234", "OuiOui    ", "Enfant", "lauteur", 13, "6.2");
				
		System.out.println("\n-- Member no exist -- \n");
				
		nbTests++;
		nbErrors += addItemBookNotMemberTest(sn, "adadaz", "adazdd", "Les 3 petits cochons", "Enfant", "lauteur", 13, "6.3", "Le membre n'existe pas mais ajoute quand meme");
		
		nbTests++;
		nbErrors += addItemBookNotMemberTest(sn, "michel", "adazdd", "Les 3 petits cochons", "Enfant", "lauteur", 13, "6.4", "Le membre existe, mdp incorrect, mais ajoute quand meme");
		
		
		
		
		 System.out.println("\n\n");
	        
			try{
				TestReport tr = new TestReport(nbTests, nbErrors);	
				System.out.println("AddItemBook : " + tr);
				return tr;	
			}
			catch (NotTestReportException e){ //This shouldn't happen
				System.out.println("Unexpected error in addItemFilm test code - Can't return valuable test results");
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
