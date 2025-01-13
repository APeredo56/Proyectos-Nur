<?php

namespace Src\Point\Application;

use Src\User\Application\UserDTO;

class PointDTO
{
    private int $id;
    private int $user_id;
    private int $total_points;
    private int $level;
    private ?UserDTO $user;

    public function __construct(int $id, int $user_id, int $total_points, int $level)
    {
        $this->id = $id;
        $this->user_id = $user_id;
        $this->total_points = $total_points;
        $this->level = $level;
        $this->user = null;
    }

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return int
     */
    public function getUserId(): int
    {
        return $this->user_id;
    }

    /**
     * @param int $user_id
     */
    public function setUserId(int $user_id): void
    {
        $this->user_id = $user_id;
    }

    /**
     * @return int
     */
    public function getTotalPoints(): int
    {
        return $this->total_points;
    }

    /**
     * @param int $total_points
     */
    public function setTotalPoints(int $total_points): void
    {
        $this->total_points = $total_points;
    }

    /**
     * @return int
     */
    public function getLevel(): int
    {
        return $this->level;
    }

    /**
     * @param int $level
     */
    public function setLevel(int $level): void
    {
        $this->level = $level;
    }

    /**
     * @return UserDTO|null
     */
    public function getUser(): ?UserDTO
    {
        return $this->user;
    }

    /**
     * @param UserDTO|null $user
     */
    public function setUser(?UserDTO $user): void
    {
        $this->user = $user;
    }

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'user_id' => $this->user_id,
            'total_points' => $this->total_points,
            'level' => $this->level,
            'user' => $this->user?->toArray()
        ];
    }
}
