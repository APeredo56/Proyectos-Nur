<?php

namespace Src\domain\entities\task\valueObjects;

use InvalidArgumentException;

class TaskTitleValue
{
    private string $value;

    public function __construct(string $value)
    {
        if (empty($value)) {
            throw new InvalidArgumentException('Title cannot be empty');
        }
        $this->value = $value;
    }

    public function getValue(): string
    {
        return $this->value;
    }
}
