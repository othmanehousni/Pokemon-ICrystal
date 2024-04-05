
## README


## DESCRIPTION POKEICRYSTAL

Le jeu consiste à devenir le plus grand dresseur de Pokemon à travers un certain nombre d’interactions et de combats contre d’autres dresseurs et/ou Pokemons. 

## ENTRÉE DANS LE JEU

Dès votre première entrée, vous serez accueilli par un dialogue vous expliquant que vous êtes officiellement entrés dans le jeu. Afin de passer ce premier dialogue mais aussi les prochains : APPUYER SUR ESPACE. Vous vous retrouverez alors dans votre maison avec un certain dresseur ayant l’air malicieux. Vous décidez tout de même de sortir de chez vous pour explorer les alentours en utilisant LES FLÈCHES DIRECTIONNELLES. La porte se trouve en bas de l’écran.

## PREMIÈRE RENCONTRE AVEC UN NPC

Dès votre sortie de la maison, vous serez invité à interagir avec l’assistante, présente pour vous aider et vous guider avec la touche L. Dès lors, on vous donne des instructions à suivre et qui ne vous laissent pas ne pas les respecter. Vous pouvez alors vous diriger vers le Laboratoire, où se trouve le professeur Oak, un homme de confiance qui va vous faire un petit cadeau. À partir de là, il est temps de parler à l’assistante pour comprendre ce qu’il faut faire.


## RECHERCHE POKEBALL

La prochaine étape est la recherche de la Pokéball pour engager le fight avec votre ennemi révélé, Garry. Cette Pokéball doit se trouver dans un recoin quelque part dans les herbes, nous avons dû la faire tomber en cultivant notre potager… 

## FIGHT 

Une fois la Pokéball récupérée, il est alors possible d’engager un combat avec Garry afin d’avancer vers votre but ultime. Ce dernier vous attend dans la House d’oú vous venez pour un combat sanglant (sans mains bien sûr). Le combat est alors engagé avec Garry, il est possible d’où bien fuir, en sélectionnant Run Away avec LES FLECHES DIRECTIONNELLES (dans ce cas, Garry, tout heureux s’enfuit pour éviter de trop gros ennuis) ou bien d’oser combattre le mal en sélectionnant Attack et le faire fuir définitivement.

## FIN COMBAT

Une fois le combat fini, avec votre victoire en poche, il vous est alors possible de rejoindre l’assistante qui va vous remercier de tout son cœur et avec un cadeau! Une fois cela fait, il est alors possible d’aller combattre d’autres Pokémon. Mais n’oubliez pas que votre Pokémon est affaibli, il ne tiendra pas contre les autres Pokémon s’il est dans un mauvais état. L’aventure commence ! (petit détail, les eaux du nord sont dites très dangereuses avec de nombreux piranhas, celle-ci vous est alors interdite!


## CONTRÔLES ET MISE EN OEUVRE COMBAT


MOUVEMENT ET SÉLECTION ACTION : flèches directionnelles
VÉLO : rester appuyer sur V
INTERACTION : touche L
PASSER UN DIALOGUE : touche ESPACE
RESET GAME : touche R

Les combats peuvent être engagés avec des attaques ou peuvent finir par une fuite du joueur, dans les deux cas, un résultat adapté est toujours présent.



      
## CONCEPTION



## NOUVEAUX SPRITES :
Une infirmière dans le Shop a été rajoutée, elle n’est pas interactable. Un vélo est maintenant accessible au joueur et lui permet d’aller plus vite en restant appuyé sur une certaine touche. Un autre Pokémon a aussi été ajouté, Raichu, qui apparaît dans l’inventaire après avoir combattu Garry.

## NOUVELLES CLASSES :
la classe AfterWinningGarry a été créée. Elle implique simplement un nouveau dialogue de félicitations par l’assistant à la sortie de la maison. Les classes PokemonSelectionEvent, PokemonSelectionMenu et AfterPokemonSelectionFightAction ont été créés comme tentative d’introduction a un menu de sélection de pokémon mais en vain. Les classes Viridian[...] ont été rajoutées pour modéliser une nouvelle ville voisine à l’aire principale. BikingAnimation a été créée pour s’occuper de problèmes de taille de sprite en se basant simplement sur la classe du game engine qu'est OrientedAnimation sans changer le gameEngine.

## NOUVEAUX DIALOGUES :
Tous les dialogues ont été changés de sorte à avoir une suite logique d'événements guidés par les dialogues qui évoluent avec le temps (avec le Chain Event)

## NOUVELLE AIRE :
Une nouvelle map a été rajoutée tout à droite de la map, sa behavior map a été faite et ses aires secondaires (similaires à Town) ont aussi été créés

## NOUVEAU SON :
Un son d’introduction à un combat a été mis en place, il ne dure que 6 sec.


Afin d’assurer le bonne complétion de tous les événements, il est conseillé que les événements se suivent de la sorte : 

Sortie maison, interaction assistante
Interaction Prof Oak dans le Lab
Interaction assistante expliquant l’endroit où se cache la balle
Recuperation PokeBall
Combat Garry
Interaction assistante donnant alors un excellent cadeau
Combat dans l’arene
VISITE !
