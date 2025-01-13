<?php

namespace Src\Point\Domain;

class TotalPointsValue
{
    private int $value;

    public function __construct(int $value)
    {
        if ($value < 0) {
            $value = 0;
        }
        $this->value = $value;
    }

    /**
     * @return int
     */
    public function getValue(): int
    {
        return $this->value;
    }

    /**
     * @param int $value
     */
    public function setValue(int $value): void
    {
        $this->value = $value;
    }
}
