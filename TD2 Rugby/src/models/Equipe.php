<?php
declare(strict_types=1);

namespace rugby\models;

use \Illuminate\Database\Eloquent as Eloq;

class Equipe extends Eloq\Model
{
    protected $table = 'equipe';
    protected $primaryKey = 'id';
    public $timestamps = false;
}