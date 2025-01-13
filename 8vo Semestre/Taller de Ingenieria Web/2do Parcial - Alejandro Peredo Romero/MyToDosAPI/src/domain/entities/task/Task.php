<?php

namespace Src\domain\entities\task;

use DateTime;
use Src\domain\entities\task\valueObjects\TaskDateRangeValue;
use Src\domain\entities\task\valueObjects\TaskTitleValue;

class Task
{
    private int $id;
    private TaskTitleValue $title;
    private String $description;
    private TaskDateRangeValue $dateRange;
    private bool $completed;

    /**
     * @param int $id
     * @param String $title
     * @param String $description
     * @param DateTime $startDate
     * @param DateTime $endDate
     * @param bool $completed
     */
    public function __construct(int $id, string $title, string $description, DateTime $startDate, DateTime $endDate, bool $completed)
    {
        $this->id = $id;
        $this->title = new TaskTitleValue($title);
        $this->description = $description;
        $this->dateRange = new TaskDateRangeValue($startDate, $endDate);
        $this->completed = $completed;
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
     * @return String
     */
    public function getTitle(): string
    {
        return $this->title->getValue();
    }

    /**
     * @param String $title
     */
    public function setTitle(string $title): void
    {
        $this->title = new TaskTitleValue($title);
    }

    /**
     * @return String
     */
    public function getDescription(): string
    {
        return $this->description;
    }

    /**
     * @param String $description
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
     * @return DateTime
     */
    public function getEndDate(): DateTime
    {
        return $this->dateRange->getEndDate();
    }

    public function setDateRange(DateTime $startDate, DateTime $endDate): void
    {
        $this->dateRange = new TaskDateRangeValue($startDate, $endDate);
    }

    /**
     * @return bool
     */
    public function isCompleted(): bool
    {
        return $this->completed;
    }

    /**
     * @param bool $completed
     */
    public function setCompleted(bool $completed): void
    {
        $this->completed = $completed;
    }

    /**
     * @return DateTime
     */

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'title' => $this->title->getValue(),
            'description' => $this->description,
            'start_date' => $this->dateRange->getStartDate(),
            'end_date' => $this->dateRange->getEndDate(),
            'completed' => $this->completed
        ];
    }

}
