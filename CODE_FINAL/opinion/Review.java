package opinion;

import java.util.ArrayList;

/**
 * Review Class
 *  Allows you to create objects of type Review and to interact with these objects
 * @author C LE GRUIEC - E LE DUC
 * @version V1.0 - May 2020
 */

public class Review {
	
	
	/**
	* Instance Attribute : mark : the mark of the item
	*/
	private float mark;
	/**
	* Instance Attribute : comment : the comment attach to the review
	*/
	private String comment;
	/**
	* Instance Attribute : reviewer : the author of the review
	*/
	private Member reviewer;
	/**
	* Instance Attribute : category : Category of review (film, book, review...)
	*/
	private String category;
	/**
	* Instance Attribute : title : Title of the notice item
	*/
	private String title;
	/**
	* Instance Attribute : opinions : the list of objects Opinion
	*/
	private ArrayList<Opinion> opinions;
	
	
	/**
     * Constructor of Book
     *
     * @param mark_
     *            mark_ of the review
     * @param comment_
     *            comment_ of the review
     * @param reviewer_
     *            reviewer_ of the item
     * @param category_
     *            category_ of review
     * @param title_
     *            title_ of of the item reviewed
    */
	public Review(float mark_, String comment_, Member reviewer_, String category_, String title_) {
		mark=mark_;
		comment=comment_;
		reviewer=reviewer_;
		category=category_;
		title=title_;
		opinions = new ArrayList<Opinion>();
		
	}
	
	/**
    *  Instance Method that returns the mark of the review
    * @return mark
    */
	public float getMark() {
		return mark;
	}
	
	/**
	 *  Instance Method that returns the comment of the review
	 * @return comment
	*/
	public String getComment() {
		return comment;
	}
	
	
	/**
	 *  Instance Method that returns the member (author) of the review
	 * @return member
	*/
	public Member getReviewer() {
		return reviewer;
	}
	
	
	/**
	 *  Instance Method that returns the category of the review
	 * @return category
	*/
	public String getCategory() {
		return category;
	}
	
	
	/**
	 *  Instance Method that returns the title of the review
	 * @return title
	*/
	public String getTitle() {
		return title;
	}
	
	/**
	 *  Instance Method that returns the opinions list
	 * @return title
	*/
	public ArrayList<Opinion> getOpinions() {
		return opinions;
	}
	
	
	/**
	 *  Instance Method that modify an existent review
	 * @param mark_
     *            the mark of the review
     * @param comment_
     *            the comment associate withh the review
	*/
	public void modifyReview(float mark_, String comment_) {
		mark=mark_;
		comment=comment_;
	}
	
	/**
	 *  Instance Method that add an opinion to a review
	 *  @param o
     *            the opinion to add in list
     *            
	*/
	public void addOpinion(Opinion o) {
		opinions.add(o);
	}
	
	
	/**
	 *  Instance Method that check if there are an existing opinion for a reviewer
	 *  @param reviewer
     *            the potential reviewer of an opinion on a review
     *  @return opinion
     *            
	*/
	public Opinion checkMyOpinion(String reviewer) {
		Opinion retour = null;
		for (int i = 0; i < opinions.size(); i++) {
			if (opinions.get(i).getReviewer().getLogin().toLowerCase().replace(" " , "").equals(reviewer.toLowerCase().replace(" " , ""))) {
				retour = opinions.get(i);
			}
		}
		return retour;
	}
	
	/**
	 *  Instance Method that compute the mean of the opinions
	 *  @return Mean of the opinions
	*/
	public float meanOpinion() {
		float mean = 0;
		for (int i = 0; i < opinions.size(); i++) {
			mean += opinions.get(i).getMark();
		}
		return mean/opinions.size();
	}
	
	

}
