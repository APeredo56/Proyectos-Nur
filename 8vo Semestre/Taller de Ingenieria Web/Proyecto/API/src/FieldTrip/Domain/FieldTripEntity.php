<?php

namespace Src\FieldTrip\Domain;

use DateTime;

class FieldTripEntity
{
    private int $id;
    private string $description;
    private DateRangeValue $dateRange;

    public function __construct(int $id, string $description, DateTime $start_date, DateTime $end_date)
    {
        $this->id = $id;
        $this->description = $description;
        $this->dateRange = new DateRangeValue($start_date, $end_date);
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
        return $this->dateRange->getStartDate();
    }

    /**
     * @return string
     */
    public function getEndDate(): string
    {
        return $this->dateRange->getEndDate();
    }

    /**
     * @param DateTime $start_date
     * @param DateTime $end_date
     */
    public function setDateRange(DateTime $start_date, DateTime $end_date): void
    {
        $this->dateRange = new DateRangeValue($start_date, $end_date);
    }

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'description' => $this->description,
            'start_date' => $this->dateRange->getStartDate(),
            'end_date' => $this->dateRange->getEndDate(),
        ];
    }
}
