package Exceptions;

/**
 * A custom exception that is thrown whenever two titles match.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class InvalidTitleException extends RuntimeException {

  /**
   * A 1-argument constructor that gives the exception a message containing the problematic title.
   *
   * @param title   The title that caused the exception to be thrown.
   */
  public InvalidTitleException(String title){
    super("A project with this title already exists. There cannot be two projects with the title: " + title);
  }
}
