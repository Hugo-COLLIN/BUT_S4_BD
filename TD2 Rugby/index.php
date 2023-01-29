<?php

declare(strict_types=1);

require './vendor/autoload.php';

use \rugby\models\Arbitre;
use \rugby\models\Arbitrer;
use \rugby\models\Equipe;
use \rugby\models\Jouer;
use \rugby\models\Joueur;
use \rugby\models\Matchs;
use \rugby\models\Poste;
use \rugby\models\Stade;

use \Illuminate\Database\Eloquent as Eloq;
use Illuminate\Database\Capsule\Manager as DB;

$db = new DB();
$db->addConnection(parse_ini_file('src/conf/conf.ini'));
$db->setAsGlobal();
$db->bootEloquent();

//Q1
/*
$gets = [Arbitre::get(),
    Arbitrer::get(),
    Equipe::get(),
    Jouer::get(),
    Joueur::get(),
    Arbitre::get(),
    Arbitre::get(),
    Arbitre::get()
];

foreach ($gets as $get) {
    //$n = get_class($get);
    //echo "$n <ul>";
    echo "<ul>";
    foreach ($get as $g) {
        echo "<li>{$g->nomArbitre}</li>";
    }
}
*/

echo "Equipes :<ul>";
$equipes = Equipe::get();

foreach ($equipes as $e) {
    echo "<li>{$e->id} {$e->codeEquipe} {$e->pays} {$e->couleur} {$e->entraineur}</li>";
}
echo "</ul>";


echo "Joueurs :<ul>";
$joueur = Joueur::get();

foreach ($joueur as $j) {
    echo "<li>{$j->numJoueur} {$j->prenom} {$j->nom} {$j->numPoste} {$j->numEquipe}</li>";
}
echo "</ul>";


echo "Poste :<ul>";
$poste = Poste::get();

foreach ($poste as $p) {
    echo "<li>{$p->numero} {$p->libelle}</li>";
}
echo "</ul>";



echo "Arbitres :<ul>";
$arbitre = \rugby\models\Arbitre::get();

foreach ($arbitre as $a) {
    echo "<li>{$a->numArbitre} {$a->nomArbitre} {$a->nationalite}</li>";
}
echo "</ul>";


echo "Stade :<ul>";
$stade = \rugby\models\Stade::get();

foreach ($stade as $s) {
    echo "<li>{$s->numStade} {$s->ville} {$s->nomStade} {$s->nationalite}</li>";
}
echo "</ul>";


echo "Match :<ul>";
$match = \rugby\models\Matchs::get();

foreach ($match as $m) {
    echo "<li>{$m->numMatch} {$m->dateMatch} {$m->nbSpect} {$m->numStade} {$m->numEquipeR} {$m->scoreR} {$m->nbEssaisR} {$m->numEquipeD} {$m->scodeD} {$m->nbEssaisD}</li>";
}
echo "</ul>";


//Q2

