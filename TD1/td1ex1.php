<?php

declare(strict_types=1);

require './vendor/autoload.php';

use \mywishlist\models\Item;
use \mywishlist\models\Liste;

use Illuminate\Database\Capsule\Manager as DB;

$db = new DB();
$db->addConnection(parse_ini_file('src/conf/conf.ini'));
$db->setAsGlobal();
$db->bootEloquent();

//Exercice 1
//Q1
/*
$liste = Liste::get();
foreach ($liste as $li) {
    echo "{$li->no},
            {$li->user_id},
            {$li->titre},
            {$li->description}
            {$li->expiration},
            {$li->token}<br>";
}*/

//Q2
/*
if (!isset($_GET["id"])) {
    $items = Item::get();
    foreach ($items as $item)
        echo "{$item->id},
           {$item->liste_id},
           {$item->nom},
           {$item->descr},
           <img src='img/{$item->img}' height='100'>,
           {$item->url},
           {$item->tarif}<br>";
}
//Q3
if (isset($_GET["id"])) {
    $item = Item::where('id', '=', $_GET["id"])->first();
    echo "{$item->id},
       {$item->liste_id},
       {$item->nom},
       {$item->descr},
       <img src='img/{$item->img}' height='100'>,
       {$item->url},
       {$item->tarif}<br>";
}
*/

//Q4
/*
$newItem = new Item();
//$newItem->id = "28";
$newItem->liste_id = 1;
$newItem->nom = "Diplôme";
$newItem->descr = "Un diplôme qui atteste qu'on a réussi le Bac, c'est mieux !";
$newItem->img = "laser.jpg";
$newItem->tarif = "00.00";
$newItem->save();
*/

