package opinion;

import java.util.ArrayList;

/**
 * Item Class 
 * is a base of interaction elements such as films or books.
 *
 * @author C LE GRUIEC - E LE DUC
 * @version V1.0 - May 2020
 */



public abstract class Item {
	
	/**
	* Instance Attribute : title : the title of the item
	*/
	protected String title;
	/**
	* Instance Attribute : kind : the kind of the film
	*/
	protected String kind;
	/**
	* Instance Attribute : reviews : the reviews of the item
	*/
	protected ArrayList<Review> reviews;
	
	
	
	
	/**
     * Constructor of Item
     *
     * @param title
     *            The title of the Item
     * @param kind
     *            The kind of the Item
    */
	public Item(String title_, String kind_){
		title=title_;
		kind=kind_;
		reviews= new ArrayList<Review>();
	}
	
	/**
     *  Instance Method that add a review in the reviews list
     *  
     *  @param review
     *            The review Object to add in reviews list
     */
	public void addReview(Review review_) {
		reviews.add(review_);
	}
	
	
	/**
     *  Instance Method that returns the title of the Item
     * @return title
     */
	public String getTitle() {
		return title;
	}
	
	/**
     *  Instance Method that returns the kind of the Item
     * @return kind
     */
	public String getKind() {
		return kind;
	}
	
	/**
     *  Instance Method that returns the reviews of the Item
     * @return reviews
     */
	public ArrayList<Review> getReviews() {
		return reviews;
	}
	

}
