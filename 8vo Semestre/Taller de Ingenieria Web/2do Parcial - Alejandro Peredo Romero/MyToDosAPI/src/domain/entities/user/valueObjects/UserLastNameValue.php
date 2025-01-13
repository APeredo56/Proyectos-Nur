<?php

namespace Src\domain\entities\user\valueObjects;

use InvalidArgumentException;

class UserLastNameValue
{
    private string $value;

    public function __construct(string $value)
    {
        if (empty($value)) {
            throw new InvalidArgumentException('LastName cannot be empty');
        }
        $this->value = $value;
    }

    public function getValue(): string
    {
        return $this->value;
    }
}
