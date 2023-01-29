<?php
declare(strict_types=1);

namespace ns\models;

use \Illuminate\Database\Eloquent as Eloq;

class ModelModel extends Eloq\Model
{
    protected $table = 'nomTable';
    protected $primaryKey = 'nomPk'; //['col1', 'col2']
    public $timestamps = false;

    //TD1.Ex2.0
    public function liste(): Eloq\Relations\BelongsTo
    {
        return $this->belongsTo("mywishlist\models\Liste", "liste_id");
    }
}