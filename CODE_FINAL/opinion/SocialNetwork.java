package opinion;

import java.nio.channels.MembershipKey;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

/**
 * SocialNetwork (MAIN) Class.
 *
 * @author C LE GRUIEC - E LE DUC
 * @version V1.0 - May 2020
 */

public class SocialNetwork implements ISocialNetworkPremium {
	
	
	/**
	* Instance Attribute : members : the members list of SocialNetwork
	*/
	private ArrayList<Member> members=new ArrayList<Member>();
	/**
	* Instance Attribute : films :  the film list added in the socialNetwork
	*/
	private ArrayList<Film> films=new ArrayList<Film>();
	/**
	* Instance Attribute :  books the book list added in the socialNetwork
	*/
	private ArrayList<Book> books=new ArrayList<Book>();
	
	
	
	@Override
	public int nbMembers() {
		return members.size();
	}
	
	
	@Override
	public int nbFilms() {
		return films.size();
	}
	
	
	@Override
	public int nbBooks() {
		return books.size();
	}

	
	@Override
	public void addMember(String login, String password, String profile) throws BadEntryException, MemberAlreadyExistsException{
			//check all syntax before before continuing
			if (checkSyntaxLengh(login, 1) && checkSyntaxLengh(password, 4) && checkSyntaxLengh(profile, 0)) {
				//if all syntax are OK, try to connect the User
				if (!checkMemberExist(login)) {
					//finally add the member
					members.add(new Member(login, password, profile));
									}
				}
			
	}

	@Override
	public void addItemFilm(String login, String password, String title,
			String kind, String director, String scriptwriter, int duration)
			throws BadEntryException, NotMemberException,
			ItemFilmAlreadyExistsException {
			
			//check all syntax before before continuing
			if (checkSyntaxLengh(login, 1) && checkSyntaxLengh(password, 4) && checkSyntaxLengh(title, 1) && checkDuration(duration)
							&& checkSyntaxLengh(director, 0) && checkSyntaxLengh(scriptwriter, 0) && checkSyntaxLengh(kind, 0)){
				
				//if all syntax are OK, try to connect the User
				Member connexion=connexion(login, password);
				if (!connexion.equals(null) && !checkFilmExist(title)) {
					//finally, add the film
					films.add(new Film(title, kind, director, scriptwriter, duration));
					
					
				}
			}
		

	}

	@Override
	public void addItemBook(String login, String password, String title,
			String kind, String author, int nbPages) throws BadEntryException,
			NotMemberException, ItemBookAlreadyExistsException {
		
		//check all syntax before before continuing
		if (checkSyntaxLengh(login, 1) && checkSyntaxLengh(password, 4) && checkSyntaxLengh(title, 1)
				&& checkSyntaxLengh(author, 0) && checkNbPage(nbPages) && checkSyntaxLengh(kind, 0)){
			
			//if all syntax are OK, try to connect the User
			Member connexion=connexion(login, password);
			if (!connexion.equals(null) && !checkBookExist(title)) {
				//finally, add the Book
				books.add(new Book(title, kind, nbPages, author));
			}
		}
	

	}
	

	@Override
	public float reviewItemFilm(String login, String password, String title,float mark, String comment) throws BadEntryException,NotMemberException, NotItemException {
		float retour=0;
		
		//check all syntax before before continuing
		if (checkSyntaxLengh(login, 1) && checkSyntaxLengh(password, 4) && checkSyntaxLengh(title, 1)
				&& checkMark(mark) && checkSyntaxLengh(comment,0)){
			
			//if all syntax are OK, try to connect the User and search the film
			Member connexion=connexion(login, password);
			Film film=findFilm(title);
			//System.out.println(film.getTitle());
			
			if (!connexion.equals(null) && !film.equals(null)) {
				Review myreview=connexion.checkMyReview(title, "film");
				
				//Case where no review has been added
				if (myreview==null) {
					Review r=new Review(mark, comment, connexion, "film", title);
					film.addReview(r);
					connexion.AddInMyReviews(r);
					
				} else {
					//Case where review has been added already, the method modify the review
					myreview.modifyReview(mark, comment);
				}
				
				//return the mean of the film
				retour=meanMarkKarma(film);
				//debug
				//System.out.println("membre: "+connexion.getMyReviews().get(0).getComment()+"\n Film: "+film.getReviews().get(0).getComment()+"\n");
			}
		}
		
		
		return retour;
	}

	@Override
	public float reviewItemBook(String login, String password, String title,float mark, String comment) throws BadEntryException,NotMemberException, NotItemException {
		float retour=0;
		
		//check all syntax before before continuing
		if (checkSyntaxLengh(login, 1) && checkSyntaxLengh(password, 4) && checkSyntaxLengh(title, 1)
				&& checkMark(mark) && checkSyntaxLengh(comment,0)){
			
			//if all syntax are OK, try to connect the User and search the Book
			Member connexion=connexion(login, password);
			Book book=findBook(title);
			//System.out.println(film.getTitle());
			
			if (!connexion.equals(null) && !book.equals(null)) {
				Review myreview=connexion.checkMyReview(title, "book");
				
				//Case where no review has been added
				if (myreview==null) {
					Review r=new Review(mark, comment, connexion, "book", title);
					book.addReview(r);
					connexion.AddInMyReviews(r);
					
				} else {
					//Case where review has been added already, the method modify the review
					myreview.modifyReview(mark, comment);
				}
				
				//return the mean of the book
				retour=meanMarkKarma(book);
				
				//debug
				//System.out.println("membre: "+connexion.getMyReviews().get(0).getComment()+"\n book: "+book.getReviews().get(0).getComment()+"\n");
			}
		}
		
		
		return retour;
	}
	
	@Override
	public float reviewOpinion(String login, String password, float mark, String category, String title, String reviewer) throws BadEntryException,NotMemberException, NotItemException {
		float retour=0;
		
		//check all syntax before before continuing
		if (checkSyntaxLengh(login, 1) && checkSyntaxLengh(password, 4) && checkSyntaxLengh(title, 1) && checkSyntaxLengh(reviewer, 1) && checkMark(mark) && checkCategoryExist(category)){
			
			//if all syntax are OK, try to connect the User and search the Item
			Member connexion=connexion(login, password);
			ArrayList<Review> reviews=new ArrayList<Review>();
			
			if (category.equals("film")) {
				reviews = findFilm(title).getReviews();
			}
			else if (category.equals("book")) {
				reviews = findBook(title).getReviews();
			}
			else {
				throw new NotItemException("Aucun Item correspondant !");
			}
			
			
			if (!connexion.equals(null) && reviews.size()!=0) {
				Review reviewToComment=findReview(reviews, reviewer);
				Opinion myopinion=null;
				try {
					myopinion = reviewToComment.checkMyOpinion(login);
				} catch (Exception e) {}
				
								
				//Case where no review has been added
				if (reviewToComment!=null && myopinion==null) {
					Opinion o = new Opinion(mark, connexion);
					reviewToComment.addOpinion(o);
				} else if(reviewToComment!=null && myopinion!=null){
					//Case where review has been added already, the method modify the review
					myopinion.modifyOpinion(mark);
				} else {
					throw new BadEntryException("Impossible d'ajouter, pas de reviews");
				}
				
				reviewToComment.getReviewer().computeKarma();
				//return the mean of the book
				retour=reviewToComment.meanOpinion();
			}
		}
		
		
		return retour;
	}
	


	@Override
	public LinkedList<String> consultItems(String title)
			throws BadEntryException {
		
		LinkedList<String> itemMatches=new LinkedList<String>();
		
		if (checkSyntaxLengh(title, 1)){
			try {
				Film FilmAjout=findFilm(title);
				if(!FilmAjout.equals(null)) itemMatches.add(FilmAjout.getTitle());
			} catch (NotItemException e) {}
			
			try {
				//if a Book match with the title, he has been added in list
				Book BookAjout=findBook(title);
				if(!BookAjout.equals(null)) itemMatches.add(BookAjout.getTitle());
			} catch (NotItemException e) {}
			
		}
		
		
		return itemMatches;
	}
	
	@Override
	public LinkedList<String> searchItemPremium(String title)
			throws BadEntryException {
		
		LinkedList<String> itemMatches=new LinkedList<String>();
		
		if (checkSyntaxLengh(title, 1)){
			for (int i = 0; i < films.size(); i++) {
				if(films.get(i).getTitle().toLowerCase().replace(" " , "").contains(title.toLowerCase().replace(" " , ""))) {
					itemMatches.add(films.get(i).getTitle());
				}		
				
			}
			for (int i = 0; i < books.size(); i++) {
				if(books.get(i).getTitle().toLowerCase().replace(" " , "").contains(title.toLowerCase().replace(" " , ""))) {
					itemMatches.add(books.get(i).getTitle());
				}		
				
			}
			
		}
		
		
		return itemMatches;
	}
	
	
	
	/**
     *  Instance Method that returns the mean of the item in parameter and multiplying it by the karma.
     * @param Item
     *           item for which we want to calculate the average
     *  @return Mean
    */
	private float meanMarkKarma(Item item) { //weight marks by multiplying by karma between 0 and 2
		float moy=0;
		float sum=0;
		if(!item.getReviews().isEmpty()) {
			for (int i = 0; i < item.getReviews().size(); i++) {
				item.getReviews().get(i).getReviewer().computeKarma();
				moy+=item.getReviews().get(i).getMark()*item.getReviews().get(i).getReviewer().getKarma();
				sum+=item.getReviews().get(i).getReviewer().getKarma();
				//System.out.println("\n\nmoy= "+moy+"  sum= "+sum+"\n\n");
			}
			moy=moy/sum;
		}
		return moy;
	}
	

	
	
	
	/**
     *  Instance Method that returns the member object if the credentials are correct, else exception. Allow to connect user
     * @param login
     *           the login of the member
     * @param password
     *           the password of the member
     * @return Boolean
    */
	private Member connexion(String login, String password) throws NotMemberException {
		Member retour=null;
		for (int i = 0; i < members.size(); i++) {
			if (members.get(i).getLogin()==login) {
				//System.out.println("#######ok"+login);
				if (members.get(i).checkCredential(login, password)) {
					retour=members.get(i);
				}
				else {
					throw new NotMemberException("Echec de la connexion : Le mot de passe est incorrect !");
				}
				
			}
			
		}
		
		if(retour==null) throw new NotMemberException("Echec de la connexion : L'utilisateur n'existe pas !");;
		
		return retour;
	}
	
	
	/**
     *  Instance Method that returns a boolean true if syntax correspond to limitation in parameter, else exception.
     *  The methods verify null and length.
     * @param toCheck
     *           The string to check
     * @param minLengh
     *           the minimal length you want for the string
     * @return Boolean
    */
	private boolean checkSyntaxLengh(String toCheck, int minLengh) throws BadEntryException {
		boolean retour=false;
		if(toCheck==null) throw new BadEntryException("Mauvaise entrée : paramètre non instancié");
		else if (toCheck.replaceAll(" ", "").length()>=minLengh) {
			retour= true;
		} else {
			throw new BadEntryException("Mauvaise entrée : null ou longueure invalide, inférieur à "+minLengh);
				
		}
		
		return retour;
	}
	
	/**
     *  Instance Method that returns a boolean true if mark is correct (0<mark<5), else exception.
     * @param mark
     *           the mark to check
     * @return Boolean
    */
	private boolean checkMark(float mark) throws BadEntryException {
		boolean retour=false;
		
		if(mark<=5.0 && mark>=0.0) {
			retour=true;
		}
		else {
			throw new BadEntryException("Mauvaise entrée : Note non comprise entre 0.0 et 5.0");
		}
		return retour;
	}
	
	/**
     *  Instance Method that returns a boolean true if duration is correct (duration >0), else exception.
     * @param duration
     *           the duration to check
     * @return Boolean
    */
	private boolean checkDuration(float duration) throws BadEntryException {
		boolean retour=false;
		if(duration>0.0) {
			retour=true;
		}
		else {
			throw new BadEntryException("Mauvaise entrée : la durée doit être supérieur à 0.0");
		}
		return retour;
	}
	
	/**
     *  Instance Method that returns a boolean true if the category exist, else exception.
     * @param category
     *           the category to check
     * @return Boolean
    */
	private boolean checkCategoryExist(String category) throws BadEntryException {
		boolean retour=false;
		if(checkSyntaxLengh(category, 1)){
			if ((category.toLowerCase().replace(" " , "").equals("film")) || (category.toLowerCase().replace(" " , "").equals("book"))) {
				retour = true;
			} else {
				retour = false;
				throw new BadEntryException("Mauvaise entrée : la catégorie n'existe pas");
			}
			
		}
		
		return retour;
	}
	
	/**
     *  Instance Method that returns a boolean true if film exist, else exception.
     * @param title
     *           the film title
     * @return Boolean
    */
	private boolean checkFilmExist(String title) throws ItemFilmAlreadyExistsException {
		boolean retour=false;
		for (int i = 0; i < films.size(); i++) {
			if(films.get(i).getTitle().toLowerCase().replace(" " , "").equals(title.toLowerCase().replace(" " , ""))) {
				retour=true;
				throw new ItemFilmAlreadyExistsException();
			}				
			
		}
		return retour;
	}
	
	/**
     *  Instance Method that returns a boolean true if book exist, else exception.
     * @param title
     *           the book title
     * @return Boolean
    */
	private boolean checkBookExist(String title) throws ItemBookAlreadyExistsException {
		boolean retour=false;
		for (int i = 0; i < books.size();) {
			if(books.get(i).getTitle().toLowerCase().replace(" " , "").equals(title.toLowerCase().replace(" " , ""))) {
				retour=true;
				throw new ItemBookAlreadyExistsException();
			}
			break;
			
		}
		return retour;
	}
	
	/**
     *  Instance Method that returns a boolean true if member exist, else exception.
     * @param login
     *           the login of a member
     * @return Boolean
    */
	private boolean checkMemberExist(String login) throws MemberAlreadyExistsException {
		boolean retour=false;
		for (int i = 0; i < members.size(); i++) {
			if(members.get(i).getLogin().toLowerCase().replace(" " , "").equals(login.replaceAll(" ", "").toLowerCase())) {
				retour=true;
				throw new MemberAlreadyExistsException();
			}
		}
		return retour;
	}
	
	
	/**
     *  Instance Method that returns a boolean true if the number of pages is correct (nbPages >0), else exception.
     * @param login
     *           the number of pages of a book to check
     * @return Boolean
    */
	private boolean checkNbPage(int nbPage) throws BadEntryException {
		boolean retour=false;
		if(nbPage>0) retour=true;
		else throw new BadEntryException("Nb pages Incorrect !");
		return retour;
	}
	
	
	/**
     *  Instance Method that returns a film if the title in parameter math with a title in films List, else exception.
     * @param title
     *           the film title
     *  @return Film
    */
	private Film findFilm(String title) throws NotItemException {
		Film retour=null;
		for (int i = 0; i < films.size(); i++) {
			if(films.get(i).getTitle().toLowerCase().replace(" " , "").equals(title.toLowerCase().replace(" " , ""))) {
				retour=films.get(i);
			}		
			
		}
		if (retour==null) throw new NotItemException("Le film recherché n'existe pas ! :"+title);
		return retour;
	}
	
	/**
     *  Instance Method that returns a book if the title in parameter math with a title in books List, else exception.
     * @param title
     *           the book title
     *  @return Book
    */
	private Book findBook(String title) throws NotItemException {
		Book retour=null;
		for (int i = 0; i < books.size(); i++) {
			if(books.get(i).getTitle().toLowerCase().replace(" " , "").equals(title.toLowerCase().replace(" " , ""))) {
				retour=books.get(i);
			}		
			
		}
		if (retour==null) throw new NotItemException("Le Book recherché n'existe pas ! :"+title);
		return retour;
	}
	
	/**
     *  Instance Method that returns a review if the member in parameter match in list, else exception.
     * @param reviews
     *           an array of review
     * @param login
     *           a login of a member
     *  @return Film
    */
	private Review findReview(ArrayList<Review> reviews, String login) throws NotItemException {
		Review retour=null;
		for (int i = 0; i < reviews.size(); i++) {
			if(reviews.get(i).getReviewer().getLogin().toLowerCase().replace(" " , "").equals(login.toLowerCase().replace(" " , ""))) {
				retour=reviews.get(i);
			}		
			
		}
		
		return retour;
	}
	
		
	/**
     *  Instance Method that help for debug
     * @param Item
     *           item to show
    */
	private void viewAllRewiews(Item item) {
		String aff="";
		if(!item.equals(null)) {
			for (int i = 0; i < item.getReviews().size(); i++) {
				aff+=item.getReviews().get(i).getReviewer().getLogin()+" k: "+item.getReviews().get(i).getReviewer().getKarma()+" - "+item.getReviews().get(i).getTitle()+" noté "+item.getReviews().get(i).getMark()+"/5 \n";
				aff+="commentaire associé: "+item.getReviews().get(i).getComment()+"\n";
				for (int j = 0; j < item.getReviews().get(i).getOpinions().size(); j++) {
					aff+="  -->"+item.getReviews().get(i).getOpinions().get(j).getReviewer().getLogin()+" - note de l'avis: "+item.getReviews().get(i).getOpinions().get(j).getMark()+"/5\n";
				}
				
			}
			System.out.println(aff);
			
		}
		
	}
	
	/**
    *  Instance Method that help for debug by show all item, review and opinions
    */
	@Override
   public String toString(){
		for (int i = 0; i < films.size(); i++) {
			viewAllRewiews(films.get(i));
			
		}
		for (int i = 0; i < books.size(); i++) {
			viewAllRewiews(books.get(i));
			
		}
	   return "";
	   
   }
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**ArrayList<E> e;
	
		Film a;
		a = new Film("", "", "", "", 01);
		e=a.getReviews();*/
		
		

	}
	
	

}
