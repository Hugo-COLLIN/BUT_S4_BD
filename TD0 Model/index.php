<?php

declare(strict_types=1);

require './vendor/autoload.php';

use \mywishlist\models\listHereYourModels;

use Illuminate\Database\Capsule\Manager as DB;

$db = new DB();
$db->addConnection(parse_ini_file('src/conf/conf.ini'));
$db->setAsGlobal();
$db->bootEloquent();