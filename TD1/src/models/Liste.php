<?php
declare(strict_types=1);

namespace mywishlist\models;

class Liste extends \Illuminate\Database\Eloquent\Model
{
    protected $table = 'liste';
    protected $primaryKey = 'no';
    public $timestamps = false;

    //TD1.Ex2.0
    public function items()
    {
        return $this->hasMany("mywishlist\models\Item", "liste_id");
        //                                                                  ^ attribut de items ?! normalement non
    }
}