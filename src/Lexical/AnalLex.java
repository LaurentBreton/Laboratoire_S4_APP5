package Lexical;
/** @author Ahmed Khoumsi */

import Utils.*;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
//  ...
  private String _toAnalyze;
  private int _lenToAnalyze;
  private int readPtr =0;

	
/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String string_to_analyze ) {  // arguments possibles
    //
      _toAnalyze = string_to_analyze;
      _lenToAnalyze = _toAnalyze.length();
  }


/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal( ) {
    //

    return readPtr < _lenToAnalyze;

  }
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  public Terminal prochainTerminal( ) {
     //

    int state = 0;
    char newChar;
    Terminal result = new Terminal();

    while (readPtr != _lenToAnalyze){
      newChar = _toAnalyze.charAt(readPtr);
      readPtr ++;

      if(state ==0){

        switch (newChar) {

          case '+' :
            result.chaine = String.valueOf(newChar);
            return result;

          case '0':
          case '1' :
            state=1;
            result.chaine += newChar;
            break;
          default:
            ErreurLex("At pointer : " + readPtr +" char : " + newChar + " Invalid lexical error");

            return new Terminal();

        }

      } else if (state ==1 ) {

        if(newChar == '0' || newChar == '1'){
          result.chaine+= newChar;
        }else {
          readPtr --;
          return result;
        }

      }


    }
return result;
  }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {	
     //

    System.out.println(s);
  }

  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);

    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t;
    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();
      toWrite += t.chaine + "\n" ;  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
