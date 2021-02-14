package tests2;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.ISocialNetworkPremium;
import opinion.SocialNetwork;

/**
 * Tests for the SocialNetwork.<i>searchPremium()</i> method.
 * 
 * @author C LE GRUIEC, E LE DUC
 * @version V1.0 - May 2020
 */


public class ConsultItemTestPremium {
	
	public static int ConsultBadEntry(ISocialNetworkPremium sn, String title, String testId) {

		
		try {
			sn.searchItemPremium(title);
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
		
		System.out.println("\nTesting ConsultItemPremium()\n");
		
		ISocialNetworkPremium sn = new SocialNetwork();
		
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
			sn.addItemFilm("Michel", "1234", "j'entend le loup", "enfant", "sssss", "qqqqqqq", 5);
			sn.addItemFilm("Robert", "1234", "j'entend le renard", "enfant", "sssss", "qqqqqqq", 5);
			sn.addItemFilm("Michel", "1234", "j'entend la belette", "enfant", "sssss", "qqqqqqq", 5);
			sn.addItemFilm("Robert", "1234", "j'entend plus", "enfant", "sssss", "qqqqqqq", 5);
			sn.addItemFilm("Michel", "1234", "Olive et tom", "enfant", "sssss", "qqqqqqq", 5);
			sn.addItemFilm("Robert", "1234", "Tom et Olive", "enfant", "sssss", "qqqqqqq", 5);
			//Books
			sn.addItemBook("Michel", "1234", "J'entends un muet qui cri", "enfant", "qqqq", 8);
			sn.addItemBook("Robert", "1234", "J'entends trop de trucs je crois", "enfant", "qqqq", 8);
			sn.addItemBook("Michel", "1234", "Oliver twist", "enfant", "qqqq", 8);
			sn.addItemBook("Robert", "1234", "TomTom et nana", "enfant", "qqqq", 8);
			sn.addItemBook("Michel", "1234", "la biographie de Tom Hank", "enfant", "qqqq", 8);
			sn.addItemBook("Robert", "1234", "Les ananas", "enfant", "qqqq", 8);
			
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
			int nbof=6;
			int res=0;
			if(sn.searchItemPremium("entend").size()==nbof) {
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
			int nbof=3;
			int res=0;
			if(sn.searchItemPremium("olive").size()==nbof) {
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
			int nbof=2;
			int res=0;
			if(sn.searchItemPremium("nana").size()==nbof) {
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
			int nbof=6;
			int res=0;
			if(sn.searchItemPremium("eNtEnD").size()==nbof) {
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
			int nbof=6;
			int res=0;
			if(sn.searchItemPremium("         eNtEnD            ").size()==nbof) {
				System.out.println("Test 11.7 : Bon nombre d'occurence touvé");
			}
			
			else {
				System.out.println("Err 11.7 : mauvais nombre d'occurence");
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


