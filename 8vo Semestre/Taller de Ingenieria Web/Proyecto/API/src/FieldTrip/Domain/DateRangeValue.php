<?php

namespace Src\FieldTrip\Domain;

use DateTime;
use InvalidArgumentException;

class DateRangeValue
{
    private DateTime $startDate;
    private DateTime $endDate;

    public function __construct(DateTime $startDate, DateTime $endDate)
    {
        if ($startDate > $endDate) {
            throw new InvalidArgumentException('Start date cannot be greater than end date');
        }
        $this->startDate = $startDate;
        $this->endDate = $endDate;
    }

    public function getStartDate(): DateTime
    {
        return $this->startDate;
    }

    public function getEndDate(): DateTime
    {
        return $this->endDate;
    }
}
