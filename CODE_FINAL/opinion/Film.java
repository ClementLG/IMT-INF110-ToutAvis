package opinion;


/**
 * Film Class 
 * Allows you to create objects of type Film and to interact with these objects
 * 
 * @author C LE GRUIEC - E LE DUC
 * @version V1.0 - May 2020
 */


public class Film extends Item {
	
	/**
	* Instance Attribute : director : the film director
	*/
	private String director;
	/**
	* Instance Attribute :  scriptWriter : The film scriptWriter
	*/
	private String scriptWriter;
	/**
	* Instance Attribute :  duration :  The film duration
	*/
	private int duration;
	
	
	
	/**
     * Constructor of Film
     *
     * @param title
     *            title of the Film
     * @param kind
     *            kind of the film
     * @param director
     *            Film director
     * @param scripWriter
     *            scripWriter_ of the Film
     * @param duration_
     *            duration of the Film
    */
	public Film(String title_, String kind_,String director_, String scripWriter_, int duration_) {
		super(title_, kind_);
		//title=title_;
		//kind=kind_;
		director=director_;
		scriptWriter=scripWriter_;
		duration=duration_;
		
	}
	
	
	
	/**
    *  Instance Method that returns the Director of the Film
    * @return director
    */
	public String getDirector() {
		return director;
	}
	
	
	/**
	*  Instance Method that returns the scriptWriter of the Film
	* @return scriptWriter
	*/
	public String getScriptWriter() {
		return scriptWriter;
	}
	
	
	/**
	*  Instance Method that returns the duration of the Film
	* @return duration
	*/
	public int getDuration() {
		return duration;
	}
	
	

}
