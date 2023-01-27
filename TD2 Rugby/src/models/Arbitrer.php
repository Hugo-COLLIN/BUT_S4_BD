<?php
declare(strict_types=1);


use \Illuminate\Database\Eloquent as Eloq;

class Arbitrer extends Eloq\Model
{
    protected $table = 'arbitrer';
    protected $primaryKey = ['numMatch', 'numArbitre'];
    public $timestamps = false;
}