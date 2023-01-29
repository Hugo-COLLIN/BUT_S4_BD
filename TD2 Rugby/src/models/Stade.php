<?php
declare(strict_types=1);

namespace rugby\models;

use \Illuminate\Database\Eloquent as Eloq;

class Stade extends Eloq\Model
{
    protected $table = 'stade';
    protected $primaryKey = 'numStade';
    public $timestamps = false;
}