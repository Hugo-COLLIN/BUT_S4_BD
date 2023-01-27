<?php
declare(strict_types=1);

namespace mywishlist\models;

use \Illuminate\Database\Eloquent as Eloq;

class Item extends Eloq\Model
{
    protected $table = 'item';
    protected $primaryKey = 'id';
    public $timestamps = false;

    //TD1.Ex2.0
    public function liste(): Eloq\Relations\BelongsTo
    {
        return $this->belongsTo("mywishlist\models\Liste", "liste_id");
    }
}