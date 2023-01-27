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

//Exercice 2
//Q1

//pour 1 liste, il y a plusieurs items
//Liste 1 -> * Item

if (!isset($_GET["no"])) {
    //$items = Item::get();
    $items = Item::has('liste')->get();
    foreach ($items as $item)
        echo "{$item->id},
           {$item->liste_id},
           {$item->liste()->first()->title},
           {$item->nom},
           {$item->descr},
           <img src='img/{$item->img}' height='100'>,
           {$item->url},
           {$item->tarif}<br>";
}

//Q2
/*
if (isset($_GET["idL"])) {
    $item = Item::where('liste_id', '=', $_GET["idL"]);
    echo "{$item->id},
       {$item->liste_id},
       {$item->nom},
       {$item->descr},
       <img src='img/{$item->img}' height='100'>,
       {$item->url},
       {$item->tarif}<br>";
}
*/
if (isset($_GET["no"])) {
    $l = Liste::where("no", "=", $_GET["no"])->first();
    $items = $l->items;
    foreach ($items as $item)
        echo "{$item->id},
           {$item->liste_id},
           {$item->liste()->first()->title},
           {$item->nom},
           {$item->descr},
           <img src='img/{$item->img}' height='100'>,
           {$item->url},
           {$item->tarif}<br>";
}