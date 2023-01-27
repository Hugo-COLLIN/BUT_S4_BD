<?php
declare(strict_types=1);


use \Illuminate\Database\Eloquent as Eloq;

class Jouer extends Eloq\Model
{
    protected $table = 'jouer';
    protected $primaryKey = 'id';
    public $timestamps = false;
}