<?php
declare(strict_types=1);

namespace rugby\models;

use \Illuminate\Database\Eloquent as Eloq;

class Joueur extends Eloq\Model
{
    protected $table = 'joueur';
    protected $primaryKey = 'numJoueur';
    public $timestamps = false;
}