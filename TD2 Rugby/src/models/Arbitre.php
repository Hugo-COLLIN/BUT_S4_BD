<?php
declare(strict_types=1);


use \Illuminate\Database\Eloquent as Eloq;

class Arbitre extends Eloq\Model
{
    protected $table = 'arbitre';
    protected $primaryKey = 'numArbitre';
    public $timestamps = false;
}