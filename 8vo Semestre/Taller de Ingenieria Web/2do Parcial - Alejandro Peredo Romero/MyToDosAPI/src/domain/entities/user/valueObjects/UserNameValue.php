<?php

namespace Src\domain\entities\user\valueObjects;

use InvalidArgumentException;

class UserNameValue
{
    private string $value;

    public function __construct(string $value)
    {
        if (empty($value)) {
            throw new InvalidArgumentException('Name cannot be empty');
        }
        $this->value = $value;
    }

    public function getValue(): string
    {
        return $this->value;
    }
}
