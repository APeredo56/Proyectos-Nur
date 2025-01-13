<?php

namespace Src\Point\Domain;

class PointEntity{
    private int $id;
    private int $user_id;
    private TotalPointsValue $total_points;
    private int $level;

    /**
     * @param int $id
     * @param int $user_id
     * @param int $total_points
     */
    public function __construct(int $id, int $user_id, int $total_points)
    {
        $this->id = $id;
        $this->user_id = $user_id;
        $this->total_points = new TotalPointsValue($total_points);
        $this->level = $this->calculateLevel();
    }

    function calculateLevel(): int {
        $basePoints = 100;
        $increment = 100;
        $level = 1;

        $remainingPoints = $this->total_points->getValue();

        while ($remainingPoints >= $basePoints) {
            $level++;
            $remainingPoints -= $basePoints;
            $basePoints += $increment;
        }

        return $level;
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
        return $this->total_points->getValue();
    }

    /**
     * @param int $total_points
     */
    public function setTotalPoints(int $total_points): void
    {
        $this->total_points = new TotalPointsValue($total_points);
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

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'user_id' => $this->user_id,
            'total_points' => $this->total_points->getValue(),
            'level' => $this->level,
        ];
    }


}
