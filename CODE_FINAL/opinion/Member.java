package opinion;

import java.util.ArrayList;

/**
 * Member Class 
 * Allows you to create objects of type Member and to interact with these objects
 * 
 * @author C LE GRUIEC - E LE DUC
 * @version V1.0 - May 2020
 */

public class Member {
	
	
	/**
	* Instance Attribute :login : the login of the member
	*/
	private String login;
	/**
	* Instance Attribute : password :  the password of the member
	*/
	private String password;
	/**
	* Instance Attribute :  profile :  the profile of the member(a description for example)
	*/
	private String profile;
	/**
	* Instance Attribute : myreviews :  the member reviews
	*/
	private ArrayList<Review> myreviews;
	/**
	* Instance Attribute : karma : A value between 0.5 and 1.5 which gives more or less importance to a member
	*/
	private float karma;
	
	
	/**
     * Constructor of Book
     *
     * @param login_
     *            login of the member
     * @param password_
     *            password of the member
     * @param profile_
     *            profile of the member

    */
	public Member(String login_, String password_, String profile_) {
		login=login_;
		password=password_;
		profile=profile_;
		myreviews=new ArrayList<Review>();
		karma = 1;
		
	}
	
	/**
     *  Instance Method that returns true if credentials are correct, else false.
     *  @param login_
     *            login of the member
     *  @param password_
     *            password of the member
     *  
     * @return Boolean
     */
	public Boolean checkCredential(String login_, String password_) {
		Boolean retour=false;
		if((login==login_) && (password==password_)) retour=true;
		return retour;
		
	}
	
	/**
     *  Instance Method that returns the login of the member
     * @return login
     */
	public String getLogin() {
		return login;
	}
	
	/**
     *  Instance Method that returns the profile of the member
     * @return profile
     */
	public String getPprofile() {
		return profile;
	}
	
	/**
     *  Instance Method that returns the reviews of the member
     * @return myreviews
     */
	public ArrayList<Review> getMyReviews() {
		return myreviews;
	}
	
	/**
     *  Instance Method that returns the karma of the member
     * @return karma
     */
	public float getKarma() {
		return karma;
	}
	
	/**
     *  Instance Method that returns the review correspond to title and category in parameter, else null review
     *  @param title
     *            title of the film
     *  @param category
     *            category of the item
     *  @return review
     */
	public Review checkMyReview(String title, String category) {
		Review retour=null;
		for (int i = 0; i < myreviews.size(); i++) {
			if(title.toLowerCase().replace(" " , "").equals(myreviews.get(i).getTitle().toLowerCase().replace(" " , "")) && category==myreviews.get(i).getCategory()) {
				retour=myreviews.get(i);
			}
		}
		return retour;
		
	}
	
		
	/**
     *  Instance Method that add a review in the member reviews
     *  @param Review
     *            Review to add in list
     */
	public void AddInMyReviews(Review r){
		myreviews.add(r);
	}
	
	/**
     *  Instance Method that calcul user karma with the average of average of opinions
     *  
    */
	public void computeKarma() {
		float moyOfMoy=0;
		Boolean check=false;
		int numbOfMoy=0;
		for (int i = 0; i < myreviews.size(); i++) {
			if(myreviews.get(i).getOpinions().size()>0) {
				check=true;
				moyOfMoy+=myreviews.get(i).meanOpinion();
				numbOfMoy++;
		}
		
		
		if(check) {
			karma=1+((moyOfMoy-2.5f)/5);
		}
		
			
		}
		
			
	}
	
}
