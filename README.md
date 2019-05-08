# Projet CamlToJava

###### Le but du projet CamlToJava est de créer un compilateur qui permet de traduire un code Caml en Java. L'objectif est de faire la CAM en Caml et en Java ainsi qu'une fonction de compilation permettant d'avoir une liste d'instruction qui pourra être lu en Java. Suite à ça, le programme Main indiquera le résultat du programme Caml.


## Exécution du projet
Quand vous êtes dans le répertoire du projet, Faîtes la commande ```cd /Tests``` afin d'être dans le répertoire des tests.
- Ecrivez votre code Caml dans un fichier dans ce répertoire, par exemple `test.ml`.
- Une fois que votre code est prêt, allez dans le répertoire Caml avec la commande ```cd ../Caml```
- Arrivé dans le répertoire Caml, compilez vos fichier avec la commande ```make```.
* *Si aucune erreur ne se présente, c'est que tous vos fichier caml se sont bien compilés* *
- Faîtes la commande ```./comp "../Tests/test.ml" "../Java/Gen.java"```
	* *Cette commande vous fera générer un fichier Java.* *
- Une fois le fichier généré, allez dans le répertoire Java avec la commande ```cd ../Java```
- Une fois dans le répertoire Java, compilez vos fichier avec la commande ```javac *.java```
- Si il y a pas d'erreur, vous pouvez compilez juste le main ```javac Main.java``` puis compilez
celui-ci avec la commande ```java Main```  
__Et espérons, votre fichier java affiche le résultat de la fonction que vous avez écrite dans le
fichier test.ml.__   :pray:

## Difficultés rencontrés : 

- L'implémentation des appels récursifs avec surtout l'écriture du addDefs en java.  
En effet, l'idée de base était de partir avec une hashmap. Mais l'écriture de Caml en Java 
fût compliqué. Puisque lorsque l'on appelé AddDefs, il devait recevoir le même type que LLE 
soit une LinkedList. On ne peut pas mettre un couple en paramètre d'une LinkedList,
il faut donc créer une classe Couple. 
- Il y aussi des problèmes de copie qui ont été résolu par l'initiative de la classe LinkedList 
qui permet de faire une copie. (comme souligné dans le sujet)
- Implémentation de la fonction d'écriture dans un fichier Java.
- Des problèmes de Cast. Notamment résolu grâce à la méthode addFirst. En effet, je ne faisais
que ajouter dans la pile avec add. Or, c'est une pile donc on ajoute au début.
- Problème de cast avec le NullV. Qui a provoqué aussi des bug à certains tests.
Ces problèmes ont été résolus par le problème de copies. Mais aussi, certains problèmes ont aussi
été présent à cause du mauvais enchainement de certaines étapes dans la CAM en Java.


## Tests :
- J'ai commencé par faire un test simple (fichier *test_simple.ml*) comportant le code suivant :  
	- ```2+3;;```, qui suite à l'exécution du projet donne 5.
- J'ai fais d'autre tests pour tester différentes implémentations :  
	- ```10/2 = 5-3;;``` (pour tester les comparaison -> voir fic *test_comp.ml*)  
		- Affiche bien False  
	- ```(fun x -> fun y -> x) 2 3;;``` (pour tester les fonctions -> voir fic *test_fonc.ml*)  
		- Affiche bien 2  
	- ```((fun x -> if x>2 then 4 else x) 1);;``` (pour tester les branchement conditionnel -> voir fic *test_branch.ml*)  
		- Affiche bien 1  

- J'ai aussi fais des tests avec les fonctions donnée dans le diapo
	- ```(fun v -> v) 3;;``` (Exemple dans le cours au diapo 117 -> voir fic *test_ex_diapo_117.ml*) 
		- Affiche bien 3
	- ```let rec f = fun n -> n + 1 in (let rec g = fun n -> n + 2 in let rec f = fun n -> n + 3 in f 4) + f 4 ;;```  
		- (Exemple donnée dans le cours Diapo 122 -> voir fic *test_ex_diapo_122.ml*) 
			- Affiche bien 12
	- ```let rec f = fun n -> n+1 in ((fun g -> f 3) (fun m ->m+2));;``` 
		- Affiche bien 4
	- ```let rec f = fun n -> n+1 in ((fun f -> f 3) (fun m ->m+2));;``` 
		- Affiche bien 5  
		- (Ces deux dernières fonctions sont des exemples données dans le cours au diapo 124 :
				voir fic *test_ex1_diapo_124.ml* et *test_ex2_diapo_124.ml*)

- Mais aussi les fichiers disponible sur Moodle tel que:
	- test.ml -> affichant bien 6
	- ackermann_iter.ml -> affichant bien 29
	- ackermann_trad.ml -> affichant bien 29
	- even_odd.ml -> affichant bien false

## Information :
Ayant été bloqué à quelques reprise, mon collègue 
Benjamin BARDY m'a aidé. Je le remercie! :innocent:  
C'est donc possible que vous retrouviez des morceaux de codes ressemblant 
notamment pour la fonction qui permet d'écrire sur le fichier java.



__Merci d'avance.__



## AMBROISE Matthias.
