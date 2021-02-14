package tests;


import opinion.ISocialNetwork;
import opinion.SocialNetwork;
import exceptions.*;

/**
 * Tests for the SocialNetwork.<i>addItemFilm()</i> method.
 * 
 * @author C LE GRUIEC, E LE DUC
 * @version V1.0 - May 2020
 */

public class addItemFilmTest {
	
	private static int addItemFilmBadEntryTest(ISocialNetwork sn, String login, String password, String title, 
												String kind, String director, String scenarist, int duration,
												String testId, String errorMessage) {
		
		//System.out.println("addItemFilmBadEntryTest");
		try {
			sn.addItemFilm(login, password, title, kind, director, scenarist, duration); // Try to add a review
			// Reaching this point means that no exception was thrown by reviewItemFilm
			System.out.println("Err " + testId + " : " + errorMessage); // display the error message
																		
			return 1; // and return the "error" value
			
		} catch (BadEntryException e) { // BadEntry exception was thrown by reviewItemFilm : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : BadEntryExeption was thrown " +e); // Display a specific error message
			return 0; // return "error" value
			
		} catch (NotMemberException e) { // BadEntry exception was thrown by reviewItemFilm : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : NotMemberException was thrown " +e); // Display a specific error message
			return 0; // return "error" value
			
		} catch (ItemFilmAlreadyExistsException e) { // BadEntry exception was thrown by reviewItemFilm : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : ItemFilmAlreadyExistsxExeption was thrown" +e); // Display a specific error message
			return 0; // return "error" value
			
		} catch (Exception e) { // An exception was thrown by reviewItemFilm, but it was not the expected exception BadEntry
			
			System.out.println("Test " + testId + " : unexpected exception. "+ e); // Display a specific error message
			e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
	}
	
	
	
	/*
	 * 
	 * Tests standards NotMemberException
	 * 
	 */
	
	private static int addItemFilmNotMemberTest(ISocialNetwork sn, String login, String pwd, String title, String kind,
            String director, String scenarist, int duration, String testId, String errorMessage) {

        try {
            sn.addItemFilm(login, pwd, title, kind, director, scenarist, duration);
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
	 * additemFilmTest
	 * 
	 */
	
	private static int addItemFilmTest(ISocialNetwork sn, String login, String password, String title, 
			String kind, String director, String scenarist, int duration,
			String testId) {
		
		int nbFilms = sn.nbFilms();
		// process this method
		try {
			sn.addItemFilm(login, password, title, kind, director, scenarist, duration);
			// No exception was thrown. That's a good start !
			if (sn.nbFilms() != nbFilms + 1) { // But the number of members hasn't changed accordingly
			System.out.println("Err " + testId+ " : the number of members (" + nbFilms+ ") was not incremented"); // Error message displayed
			return 1; // return error code
			} else
			System.out.println("Test" + testId+ " : Film ajouté avec succès, tot de film: "+sn.nbFilms());	
			return 0; // return success code : everything is OK, nothing to
				// display
		} catch (ItemFilmAlreadyExistsException e) {
			System.out
			.println("Test " + testId + " : AlreadyExistexception  was thrown " + e +" - tot de film:"+ sn.nbFilms());
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
	
	
	/*
	 * 
	 * Réalisation des tests
	 * 
	 */
	public static TestReport test(){

		ISocialNetwork sn = new SocialNetwork();
		int nbTests=0,nbErrors=0;
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();
		
		
		System.out.println("Testing addItemFilm()");
		System.out.println("\n--BadEntry -- \n");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, null, "1234", "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", 45, "3.1", "login non instancié");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "   ", "1234", "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", 45, "3.2", "login ne contenant que des espaces");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", null, "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", 45, "3.3", "password non instancié");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1 ", "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", 45, "3.4", "password de moins de 4 caractères");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1234", null,"DessAnim","UneMetteurEnScne", "unScenariste", 45, "3.5", "title non instancié");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1234", "","DessAnim","UneMetteurEnScne", "unScenariste", 45, "3.6", "title de moins de 1 caractères");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1234", "ouioui", null, "UneMetteurEnScne", "unScenariste", 45, "3.7", "kind non instancié");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1234", "ouioui","DessAnim",null, "unScenariste", 45, "3.8", "director non instancié");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1234", "ouioui","DessAnim","UneMetteurEnScne", null, 45, "3.9", "scenarist non instancié");
		
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1234", "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", 0, "3.10", "durée non instancié.");
		//durée null crash
				
		nbTests++;
		nbErrors+=addItemFilmBadEntryTest(sn, "michel", "1234", "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", -1, "3.11", "durée négative");
		
		
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
		
		//j'ajoute 3 films
		
		
		nbTests++;
		nbErrors+=addItemFilmTest(sn, "michel", "1234", "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", 45, "4.1.1");
		nbTests++;
		nbErrors+=addItemFilmTest(sn, "michel", "1234", "nonon","DessAnim","UneMetteurEnScne", "unScenariste", 45, "4.1.2");
		nbTests++;
		nbErrors+=addItemFilmTest(sn, "michel", "1234", "OuiNon","DessAnim","UneMetteurEnScne", "unScenariste", 45, "4.1.3");
		
		//System.out.println(sn.nbFilms());
		
		//ajout d'un film existant
		nbTests++;
		nbErrors+=addItemFilmTest(sn, "michel", "1234", "ouioui","DessAnim","UneMetteurEnScne", "unScenariste", 45, "4.2");
		
		//ajout d'un film existant mais avec des espaces et une casse différente
		nbTests++;
		nbErrors+=addItemFilmTest(sn, "michel", "1234", " OuiOui   ","DessAnim","UneMetteurEnScne", "unScenariste", 45, "4.3");
		
		System.out.println("\n-- Member no exist -- \n");
		
		nbTests++;
        nbErrors += addItemFilmNotMemberTest(sn, "dfgdrg", "gdjkghrdhgjfd", "PeutEtre", "comédie", "freud", "kant", 98, "4.4", "Member doesn't exist maid add quand même");
        nbTests++;
        nbErrors += addItemFilmNotMemberTest(sn, "michel", "gdjkghrdhgjfd", "PeutEtre", "comédie", "freud", "kant", 98, "4.5", "Member doesn't exist maid add quand même");
        		
        System.out.println("\n\n");
        
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("\nAddItemFilm : " + tr);
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
