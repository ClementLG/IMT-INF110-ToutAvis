package tests;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

/**
 * Tests for the SocialNetwork.<i>consultItem()</i> method.
 * 
 * @author C LE GRUIEC, E LE DUC
 * @version V1.0 - May 2020
 */

public class ConsultItemTest {
	

	public static int ConsultBadEntry(ISocialNetwork sn, String title, String testId) {

		
		try {
			sn.consultItems(title);
			System.out.println("ERR " + testId + " : Aucune erreur levée (pas normal)");
			return 1;
		} catch (BadEntryException e) {
			System.out.println("Test " + testId + " : BadEntryExceptio was thrown ");
			
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "+ e);
			return 1;
		}

		
		
	}
	
	public static TestReport test(){
		
		System.out.println("\nTesting ConsultItem()\n");
		
		ISocialNetwork sn = new SocialNetwork();
		
		int nbBooks = sn.nbBooks(); // number of books in 'sn' (should be 0
										// here)
		int nbFilms = sn.nbFilms(); // number of films in 'sn' (should be 0
										// here)
	
		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests
		
		//j'ajoute 2 membres et 6 films et 6 Books
		try {
			//members
			sn.addMember("Michel", "1234", "profile");
			sn.addMember("Robert", "1234", "profile");
			//films
			sn.addItemFilm("Michel", "1234", "ouioui", "enfant", "sssss", "qqqqqqq", 5);
			sn.addItemFilm("Robert", "1234", "nonnon", "enfant", "sssss", "qqqqqqq", 5);
			
			//Books
			sn.addItemBook("Michel", "1234", "ouioui", "enfant", "qqqq", 8);
			sn.addItemBook("Robert", "1234", "peut-etre", "enfant", "qqqq", 8);
			
			
			System.out.println("\n-- initialistaion --\n");
			System.out.println(sn.nbFilms()+" films ont été ajoutés");
			System.out.println(sn.nbBooks()+" books ont été ajoutés\n");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Test avec mauvaise syntax
		System.out.println("\n-- Bad Entry --\n");
		nbTests++;
		nbErrors += ConsultBadEntry(sn, null, "11.1"); // title null
		
		nbTests++;
		nbErrors += ConsultBadEntry(sn, "  ", "11.2"); // title vide
		
		
		System.out.println("\n-- Check nb occurence --\n");
		
		try {
			//recherche d'occurence
			nbTests++;
			int nbof=2;
			int res=0;
			if(sn.consultItems("ouioui").size()==nbof) {
				System.out.println("Test 11.3 : Bon nombre d'occurence touvé");
			}
			
			else {
				System.out.println("Err 11.3 : mauvais nombre d'occurence");
				nbErrors++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			//recherche d'occurence
			nbTests++;
			int nbof=2;
			int res=0;
			if(sn.consultItems("ouioui").size()==nbof) {
				System.out.println("Test 11.4 : Bon nombre d'occurence touvé");
			}
			
			else {
				System.out.println("Err 11.4 : mauvais nombre d'occurence");
				nbErrors++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			//recherche d'occurence
			nbTests++;
			int nbof=1;
			int res=0;
			if(sn.consultItems("nonnon").size()==nbof) {
				System.out.println("Test 11.5 : Bon nombre d'occurence touvé");
			}
			
			else {
				System.out.println("Err 11.6 : mauvais nombre d'occurence");
				nbErrors++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			//recherche d'occurence
			nbTests++;
			int nbof=1;
			int res=0;
			if(sn.consultItems("peut-etre").size()==nbof) {
				System.out.println("Test 11.5 : Bon nombre d'occurence touvé");
			}
			
			else {
				System.out.println("Err 11.6 : mauvais nombre d'occurence");
				nbErrors++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Test avec une casse differrente:
		try {
			//recherche d'occurence
			nbTests++;
			int nbof=2;
			int res=0;
			if(sn.consultItems("OuIoUi").size()==nbof) {
				System.out.println("Test 11.7 : Bon nombre d'occurence touvé");
			}
			
			else {
				System.out.println("Err 11.7 : mauvais nombre d'occurence");
				nbErrors++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//Test avec des espaces:
		try {
			//recherche d'occurence
			nbTests++;
			int nbof=2;
			int res=0;
			if(sn.consultItems("         OuIoUi            ").size()==nbof) {
				System.out.println("Test 11.8 : Bon nombre d'occurence touvé");
			}
			
			else {
				System.out.println("Err 11.8 : mauvais nombre d'occurence");
				nbErrors++;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//Generation rapport
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("\nConsultItemTest : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in AddMemberTest test code - Can't return valuable test results");
			return null;
			}
		}
	
	
	
		public static void main(String[] args) {
			test();
		}
		
		
}


