package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

public interface ISocialNetworkPremium extends ISocialNetwork{
	
	
	/**
     *  Instance Method which searches for all the elements which correspond to the word in parameter
     * @param login
     *            the login of the member
     * @param password
     *            the password of the member
     * @param mark
     *            the mark you want to give to the review
     * @param category
     *            the category of the item to review
     * @param title
     *            the title the item where the review is left
     * @param reviewer
     *            the reviewer of the review you want to mark
     *  @return List of items found
     *  
     * @throws BadEntryException
     *         if title is not instantiated or contains less than one
	 *         non space character
	 * @throws NotMemberException
	 *             if login does not match with the login of a registered member
	 *             in SocialNetwork or if password does not correspond to
	 *             his registered password.
	 * @throws NotItemException
	 *             if title is not registered as a film's title in the SocialNetwork
    */	
	public float reviewOpinion(String login, String password, float mark, String category, String title, String reviewer) throws BadEntryException,NotMemberException, NotItemException ;
	
	
	/**
     *  Instance Method which searches for all the elements which correspond to the word in parameter
     * @param title
     *            the title researched (a part of title for example)
     *  @return List of items found
     *  
     * @throws BadEntryException
     *         if title is not instantiated or contains less than one
	 *         non space character
    */	
	public LinkedList<String> searchItemPremium(String title) throws BadEntryException;
}

