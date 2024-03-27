package Syntax;

/** @author Ahmed Khoumsi */

import javax.swing.text.DefaultEditorKit;

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  private char _symbol;
  private  ElemAST[] _feuilles = new ElemAST[2];

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(char  operator, ElemAST operand1, ElemAST operand2 ) { // avec arguments
    //
    _symbol = operator;
    _feuilles[0] = operand1;
    _feuilles[1] = operand2;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {

    switch (_symbol){
      case '+' :
          return _feuilles[0].EvalAST() + _feuilles[1].EvalAST();


      default: ErreurEvalAST("unsupported operation " + _symbol);
    }
     //
    return -1;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     //
    return _feuilles[0].LectAST() + _symbol + _feuilles[1].LectAST();

  }

}


