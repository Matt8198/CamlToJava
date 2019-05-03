Le but du projet CamlToJava est de créer un compilateur qui permet de traduire un code Caml en Java.
L'objectif est de faire la CAM en Caml et en Java ainsi qu'une fonction de compilation permettant d'avoir une liste d'instruction qui pourra être lu en Java. Suite à ça, le programme Main indiquera le résultat du programme Caml.

Voici un exemple de commande a enchainer :

Sur le terminal :
- Make

Ensuite, sur Ocaml :

	- #use "use.ml";;
	- open Miniml;;
	- let p = parse "../Tests/test.ml";;
	- let inst = compile_prog(p);
		Deuxieme facon de compiler.
			- let mlexp = mlexp_of_prog(p);;
			- let inst = compile([],mlexp);;
			
- #use "use.ml";;
- open Miniml;;
- let p = parse "../Tests/test.ml";;
- let inst = compile_prog(p);
	- Deuxieme facon de compiler.
		- let mlexp = mlexp_of_prog(p);;
		- let inst = compile([],mlexp);;
		
Difficultés rencontrés : 
- L'implémentation des appels récursifs
- Implémentation de la fonction d'écriture dans un fichier Java.


Ayant été bloqué pendant un moment, mon collègue Benjamin BARDY m'a aidé. C'est possible que vous retrouviez des morceaux de codes ressemblant notamment pour la fonction qui permet d'écrire sur le fichier java.

Merci d'avance.

AMBROISE Matthias.

