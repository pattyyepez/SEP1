package Model;

/**
 * The GUINode class is not a functional representation of any real life project.
 * It still inherits from the Project class as it is needed for polymorphism.
 * It's only purpose in life is to exist in TreeTableViews.
 *
 * @author Group SiedemSyvSiete
 * @version 1.0
 */
public class GUINode extends Project{

  /**
   * Constructs a GUI node with a custom title.
   *
   * @param t GUI node title.
   */
  public GUINode(String t){
    super(t);
  }
}
