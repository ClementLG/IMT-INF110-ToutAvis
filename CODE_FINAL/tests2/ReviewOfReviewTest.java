package tests2;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.ISocialNetworkPremium;
import opinion.SocialNetwork;

/**
 * Tests for the SocialNetwork.<i>reviewOpinion()</i> method.
 * 
 * @author C LE GRUIEC, E LE DUC
 * @version V1.0 - May 2020
 */

public class ReviewOfReviewTest {
	
	private static int addOpinionTest(ISocialNetworkPremium sn, String login, String password, float mark, String category, String title, 
			String reviewer,String testId, String errorMessage) {
		
		//System.out.println("addItemFilmBadEntryTest");
		try {
			sn.reviewOpinion(login, password, mark, category, title, reviewer); // Try to add an opinion
			// Reaching this point means that no exception was thrown by addOpinion
			System.out.println("Err " + testId + " : " + errorMessage); // display the error message
												
			return 1; // and return the "error" value
		
		} catch (BadEntryException e) { // BadEntry exception was thrown by addOpinion : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : BadEntryExeption was thrown " +e); // Display a specific error message
			return 0; // return "error" value
			
		} catch (NotMemberException e) { //NotMemberException exception was thrown by addOpinion : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : NotMemberException was thrown " +e); // Display a specific error message
			return 0; // return "error" value
			
		} catch (NotItemException e) { // NotItemException was thrown by addOpinion : this is a good start! Let's now check if 'sn' was not impacted
			System.out.println("Test "+ testId+ " : NotItemException was thrown " +e); // Display a specific error message
			return 0; // return "error" value
			
		} catch (Exception e) { // An exception was thrown by addOpinion, but it was not the expected exception BadEntry
		
			System.out.println("Test " + testId + " : unexpected exception. "+ e); // Display a specific error message
			//e.printStackTrace(); // Display contextual info about what happened
			return 1; // return error value
		}
		
	}
	
	public static TestReport test(){
		System.out.println("Testing reviewOpinion()\n");
		ISocialNetworkPremium sn = new SocialNetwork();
		int nbTests=0,nbErrors=0;
		
		//Test initialization with members and films
		
		try {
			sn.addMember("michel", "1234", "Profile de Michel");
			sn.addMember("robert", "1234", "Profile de Robert");
			sn.addItemBook("michel", "1234", "ouiouiBook", "enfant", "author", 40);
			sn.addItemFilm("michel", "1234", "ouiouiFilm", "enfant", "director", "scenarist", 40);
			sn.addItemBook("robert", "1234", "nonnonBook", "enfant", "author", 40);
			sn.addItemFilm("robert", "1234", "nonnonFilm", "enfant", "director", "scenarist", 40);
			sn.reviewItemBook("michel", "1234", "ouiouiBook", 5, "Le livre etait très bien");
			sn.reviewItemFilm("robert", "1234", "ouiouiFilm", 3, "Le film etait passable");
			
			//sn.reviewOpinion("robert", "1234", 5, "film", "ouiouiFilm", "michel");
			//sn.reviewOpinion("michel", "1234", 5, "film", "ouiouiFilm", "michel");
			
			System.out.println("####Initialization : \n\n>Items added\n \nnb of films added: "+sn.nbFilms()+"\nnb of livres added: "+sn.nbBooks());
			System.out.println("\n>Reviews added :");
			sn.toString();
			
		} catch (BadEntryException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			nbErrors++;
		} catch (NotMemberException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			nbErrors++;
		} catch (ItemBookAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			nbErrors++;
		} catch (MemberAlreadyExistsException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			nbErrors++;
		} catch (ItemFilmAlreadyExistsException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			nbErrors++;
		} catch (NotItemException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			nbErrors++;
		}
		
		//BadEntry
		System.out.println("\n-- BadEntry --\n");
		nbTests++;
		addOpinionTest(sn, null, "1234", 5, "film", "ouiouiFilm", "michel","12.1", "doesn't reject null logins");
		nbTests++;
		addOpinionTest(sn, "     ", "1234", 5, "film", "ouiouiFilm", "michel","12.2", "doesn't reject logins invalid login (only spaces)");
		nbTests++;
		addOpinionTest(sn, "robert", null, 5, "film", "ouiouiFilm", "michel","12.3", "doesn't reject null password");
		nbTests++;
		addOpinionTest(sn, "robert", "    s", 5, "film", "ouiouiFilm", "michel","12.4", "doesn't reject null password");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", -1, "film", "ouiouiFilm", "michel","12.5", "doesn't reject negative mark");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 6, "film", "ouiouiFilm", "michel","12.6", "doesn't reject to high mark");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 4, null, "ouiouiFilm", "michel","12.7", "doesn't reject to null category");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 4, "film", null, "michel","12.8", "doesn't reject to null title");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 4, "film", "    ", "michel","12.9", "doesn't reject to null title");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 4, "film", "ouiouiFilm", null,"12.10", "doesn't reject null login reviewer target");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 4, "film", "ouiouiFilm", null,"12.11", "doesn't reject incorrect login reviewer target");
		System.out.println("\n-- Not Member --\n");
		nbTests++;
		addOpinionTest(sn, "wrong", "1234", 4, "film", "ouiouiFilm", "michel","13.1", "doesn't reject wrong user");
		nbTests++;
		addOpinionTest(sn, "robert", "wrong", 4, "film", "ouiouiFilm", "michel","13.2", "doesn't reject good user with wrong password");
		System.out.println("\n-- No item --\n");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 4, "film", "wrong", "michel","13.3", "doesn't reject invalid item (film)");
		nbTests++;
		addOpinionTest(sn, "robert", "1234", 4, "book", "wrong", "michel","13.4", "doesn't reject invalid item (book)");
		//sn.toString();
		
		
		
		System.out.println("\n-- MEANs Tests --\n");
		
		//Tests avec des Karma différents
		nbTests++;
		try {
			sn.reviewOpinion("robert", "1234", 5, "book", "ouiouiBook", "michel");
			//karma de michel = 1+(5-2.5)/5 = 1.5 (MAX)
			sn.reviewOpinion("michel", "1234", 0, "film", "ouiouiFilm", "robert");
			//karma de robert = 1+(5-2.5)/5 =0.5 (MIN)
			
			//Test calcul:
			float MoyTest = sn.reviewItemBook("robert", "1234", "ouiouiBook", 3, "Le film etait pas mal");
			//we want 4.5 --> 0.5*3 + 1.5*5 / 2 = 4.5
			if (MoyTest!=4.5) {
				nbErrors++;
				System.out.println("ERR 13.5 : the expected average is not correct for ouiouibook");
			}
			else System.out.println("TEST 13.5 : the expected average is correct for ouiouibook");
			System.out.println("");
			sn.toString();
		} catch (BadEntryException | NotMemberException | NotItemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Tests avec des Karma modifiés
		nbTests++;
		try {
			sn.reviewOpinion("robert", "1234", 0, "book", "ouiouiBook", "michel");
			//karma de michel = 1+(0-2.5)/5 =0.5 (MIN)
			sn.reviewOpinion("michel", "1234", 0, "film", "ouiouiFilm", "robert");
			//karma de robert = 1+(0-2.5)/5 =0.5 (MIN)
			
			//Test calcul:
			float MoyTest = sn.reviewItemBook("robert", "1234", "ouiouiBook", 3, "Le film etait pas mal");
			//we want 4 --> 0.5*3 +0.5*5 / 2 = 4
			if (MoyTest!=4) {
				nbErrors++;
				System.out.println("ERR 13.6 : the expected average is not correct for ouiouibook");
			}
			else System.out.println("TEST 13.6 : the expected average is correct for ouiouibook\n");
			sn.toString();
		} catch (BadEntryException | NotMemberException | NotItemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
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
