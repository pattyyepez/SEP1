package Exceptions;

public class InvalidTitleException extends RuntimeException {
  public InvalidTitleException(){
    super("A project with this title already exists. There cannot be two projects with the same titles.");
  }

  public InvalidTitleException(String title){
    super("A project with this title already exists. There cannot be two projects with the title: " + title);
  }
}
