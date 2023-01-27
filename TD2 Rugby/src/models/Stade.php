<?php
declare(strict_types=1);


use \Illuminate\Database\Eloquent as Eloq;

class Stade extends Eloq\Model
{
    protected $table = 'stade';
    protected $primaryKey = 'numStade';
    public $timestamps = false;
}