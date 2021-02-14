package opinion;


/**
 * Book Class
 * Allows you to create objects of type Book and to interact with these objects
 * 
 * @author C LE GRUIEC - E LE DUC
 * @version V1.0 - May 2020
 */


public class Book extends Item{
	
	
	/**
	* Instance Attribute :  number of pages of the book
	*/
	private int nbPages;
	/**
	* Instance Attribute :  author : the book author
	*/
	private String author;
	
	
	
	/**
     * Constructor of Book
     *
     * @param title
     *            title of the book
     * @param kind
     *            kind of the book
     * @param nbPages
     *            number of pages of the book
     * @param author
     *            author of the book
    */
	public Book(String title_, String kind_,int nbPages_, String author_) {
		super(title_, kind_);
		//title=title_;
		//kind=kind_;
		author=author_;
		nbPages=nbPages_;
		
	}
	
	/**
     *  Instance Method that returns the author of the book
     * @return auhtor
    */
	public String getAuthor() {
		return author;
	}
	
	/**
     *  Instance Method that returns the nbPAges of the book
     * @return nbPages
     */	
	public int getNnbPages() {
		return nbPages;
	}

}
