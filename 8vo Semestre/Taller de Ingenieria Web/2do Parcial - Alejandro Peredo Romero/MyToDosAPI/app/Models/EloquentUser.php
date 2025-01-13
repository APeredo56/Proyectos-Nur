<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

/**
 *
 *
 * @property int $id
 * @property string $name
 * @property string $last_name
 * @property string $email
 * @property \Illuminate\Support\Carbon|null $email_verified_at
 * @property string $password
 * @property string $phone
 * @property string $date_of_birth
 * @property string|null $remember_token
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @property-read \Illuminate\Notifications\DatabaseNotificationCollection<int, \Illuminate\Notifications\DatabaseNotification> $notifications
 * @property-read int|null $notifications_count
 * @property-read \Illuminate\Database\Eloquent\Collection<int, \App\Models\EloquentTask> $tasks
 * @property-read int|null $tasks_count
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser query()
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereDateOfBirth($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereEmail($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereEmailVerifiedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereLastName($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereName($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser wherePassword($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser wherePhone($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereRememberToken($value)
 * @method static \Illuminate\Database\Eloquent\Builder<static>|EloquentUser whereUpdatedAt($value)
 * @mixin \Eloquent
 */
class EloquentUser extends Authenticatable
{
    /** @use HasFactory<\Database\Factories\UserFactory> */
    use HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'name',
        'last_name',
        'email',
        'password',
        'phone',
        'date_of_birth',
    ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * Get the attributes that should be cast.
     *
     * @return array<string, string>
     */
    protected function casts(): array
    {
        return [
            'email_verified_at' => 'datetime',
            'password' => 'hashed',
        ];
    }

    public function tasks()
    {
        return $this->belongsToMany(EloquentTask::class, "user_tasks");
    }
}
