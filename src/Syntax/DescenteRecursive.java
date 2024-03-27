package Syntax;

import Utils.*;



/** @author Ahmed Khoumsi */

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  private String _sts;
  private int readPtr;
  private int _oldPtr;

/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive(String in, int oldPtr) {
    //

  _sts = in;
  readPtr =0;
  _oldPtr = oldPtr;
}


/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
   //
  return Operator();

}

private ElemAST Operator(){

  if(readPtr < _sts.length()) {

    if (_sts.charAt(readPtr) == '+') {
      int i = _oldPtr + readPtr;
      ErreurSynt("Erreur syntaxique, commence par un + a la position : " +  i);
      return null;
    }
    String leftOp = "";
    while (_sts.charAt(readPtr) != '+') {
      leftOp += _sts.charAt(readPtr);
      readPtr++;
      if(readPtr >= _sts.length()){
        return new FeuilleAST(_sts);
      }

    }
    String subString = _sts.substring(readPtr+1);


    DescenteRecursive newDescente = new DescenteRecursive(subString, (readPtr+_oldPtr));
    return new NoeudAST('+', new FeuilleAST(_sts.substring(0,readPtr)), newDescente.AnalSynt());
  }
  return null;
}








// Methode pour chaque symbole non-terminal de la grammaire retenue
// ... 
// ...



/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
    //
  System.out.println(s);
}



  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0){
      args = new String [2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    Reader reader = new Reader(args[0]);
    DescenteRecursive dr = new DescenteRecursive(reader.toString(), 0);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1],toWriteLect+toWriteEval); // Ecriture de toWrite 
                                                              // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

