<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Builder;
use Illuminate\Database\Eloquent\Collection;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Carbon;

/**
 *
 *
 * @property int $id
 * @property string $title
 * @property string $description
 * @property string $start_date
 * @property string $end_date
 * @property int $completed
 * @property Carbon|null $created_at
 * @property Carbon|null $updated_at
 * @property-read Collection<int, EloquentUser> $users
 * @property-read int|null $users_count
 * @method static Builder<static>|EloquentTask newModelQuery()
 * @method static Builder<static>|EloquentTask newQuery()
 * @method static Builder<static>|EloquentTask query()
 * @method static Builder<static>|EloquentTask whereCompleted($value)
 * @method static Builder<static>|EloquentTask whereCreatedAt($value)
 * @method static Builder<static>|EloquentTask whereDescription($value)
 * @method static Builder<static>|EloquentTask whereEndDate($value)
 * @method static Builder<static>|EloquentTask whereId($value)
 * @method static Builder<static>|EloquentTask whereStartDate($value)
 * @method static Builder<static>|EloquentTask whereTitle($value)
 * @method static Builder<static>|EloquentTask whereUpdatedAt($value)
 * @mixin \Eloquent
 */
class EloquentTask extends Model
{
    protected $fillable = [
        'title',
        'description',
        'start_date',
        'end_date',
        'completed'
    ];

    public function users()
    {
        return $this->belongsToMany(EloquentUser::class, "user_tasks");
    }
}
