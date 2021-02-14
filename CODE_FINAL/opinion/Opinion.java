package opinion;

/**
 * Opinion Class 
 * Allows you to create objects of type Opinion and to interact with these objects
 * 
 * @author C LE GRUIEC - E LE DUC
 * @version V1.0 - May 2020
 */

public class Opinion {
	/**
	* Instance Attribute : mark : the rating given to a review
	*/
	private float mark;
	/**
	* Instance Attribute : reviewer : The author of the opinion
	*/
	private Member reviewer;
	
	
	/**
     * Constructor of Opinion
     *
     * @param mark
     *            mark of the opinion
     * @param reviewer
     *            the reviewer (author) of the opinion
    */
	public Opinion(float mark, Member reviewer) {
		this.mark = mark;
		this.reviewer = reviewer;
	}
	
	
	/**
     * Method that allows to modify the mark of the opinion
     *
     * @param mark
     *            mark of the opinion
    */
	public void modifyOpinion(float mark) {
		this.mark = mark;
	}
	
	/**
     *  Instance Method that returns the mark of the opinion
     * @return mark
     */
	public float getMark() {
		return mark;
	}
	
	
	/**
     *  Instance Method that returns the reviewer of the opinion
     * @return reviewer
     */
	public Member getReviewer() {
		return reviewer;
	}

}
