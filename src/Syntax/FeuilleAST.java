package Syntax;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
    private String _symbol;


/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST( String symbol) {  // avec arguments
    //
      _symbol = symbol;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
    //

      return Integer.parseInt(_symbol);
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    //
      return _symbol;
  }

}
