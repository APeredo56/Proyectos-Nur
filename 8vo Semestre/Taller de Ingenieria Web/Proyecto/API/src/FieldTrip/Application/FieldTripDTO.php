<?php

namespace Src\FieldTrip\Application;

use Src\Department\Application\DepartmentDTO;
use DateTime;

class FieldTripDTO
{
    private int $id;
    private string $description;
    private DateTime $startDate;
    private DateTime $endDate;
    private array $users;
    private array $waterBodies;

    public function __construct(int $id, string $description, DateTime $startDate, DateTime $endDate)
    {
        $this->id = $id;
        $this->description = $description;
        $this->startDate = $startDate;
        $this->endDate = $endDate;
        $this->users = [];
        $this->waterBodies = [];
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
     * @return string
     */
    public function getDescription(): string
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return DateTime
     */
    public function getStartDate(): DateTime
    {
        return $this->startDate;
    }

    /**
     * @param DateTime $startDate
     */
    public function setStartDate(DateTime $startDate): void
    {
        $this->startDate = $startDate;
    }

    /**
     * @return DateTime
     */
    public function getEndDate(): DateTime
    {
        return $this->endDate;
    }

    /**
     * @param DateTime $endDate
     */
    public function setEndDate(DateTime $endDate): void
    {
        $this->endDate = $endDate;
    }

    /**
     * @return array
     */
    public function getUsers(): array
    {
        return $this->users;
    }

    /**
     * @param array $users
     */
    public function setUsers(array $users): void
    {
        $this->users = $users;
    }

    /**
     * @return array
     */
    public function getWaterBodies(): array
    {
        return $this->waterBodies;
    }

    /**
     * @param array $waterBodies
     */
    public function setWaterBodies(array $waterBodies): void
    {
        $this->waterBodies = $waterBodies;
    }



    /**
     * @return array
     */

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'description' => $this->description,
            'start_date' => $this->startDate,
            'end_date' => $this->endDate,
            'users' => array_map(fn($user) => $user->toArray(), $this->users),
            'water_bodies' => array_map(fn($waterBody) => $waterBody->toArray(), $this->waterBodies)
        ];
    }
}
