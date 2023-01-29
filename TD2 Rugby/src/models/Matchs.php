<?php
declare(strict_types=1);

namespace rugby\models;

use \Illuminate\Database\Eloquent as Eloq;

class Matchs extends Eloq\Model
{
    protected $table = 'matchs';
    protected $primaryKey = 'numMatch';
    public $timestamps = false;
}