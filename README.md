"# CamlToJava" 

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
			
