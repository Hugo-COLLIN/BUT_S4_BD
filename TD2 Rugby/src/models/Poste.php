<?php
declare(strict_types=1);


use \Illuminate\Database\Eloquent as Eloq;

class Poste extends Eloq\Model
{
    protected $table = 'poste';
    protected $primaryKey = 'numero';
    public $timestamps = false;
}