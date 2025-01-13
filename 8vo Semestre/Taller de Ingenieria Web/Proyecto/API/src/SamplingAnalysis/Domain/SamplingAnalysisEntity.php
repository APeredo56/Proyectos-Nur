<?php

namespace Src\SamplingAnalysis\Domain;

class SamplingAnalysisEntity
{
    private int $id;
    private float $turbidity;
    private float $water_velocity;
    private float $width;
    private float $average_depth;

    private float $flow_rate;
    private int $water_body_id;
    private int $user_id;
    private int $field_trip_id;

    /**
     * @param int $id
     * @param float $turbidity
     * @param float $water_velocity
     * @param float $width
     * @param float $average_depth
     * @param int $water_body_id
     * @param int $user_id
     * @param int $field_trip_id
     */
    public function __construct(int $id, float $turbidity, float $water_velocity, float $width, float $average_depth, int $water_body_id, int $user_id, int $field_trip_id)
    {
        $this->id = $id;
        $this->turbidity = $turbidity;
        $this->water_velocity = $water_velocity;
        $this->width = $width;
        $this->average_depth = $average_depth;
        $this->flow_rate = $this->calculateFlowRate();
        $this->water_body_id = $water_body_id;
        $this->user_id = $user_id;
        $this->field_trip_id = $field_trip_id;
    }

    public function calculateFlowRate(): float
    {
        return $this->water_velocity * $this->width * $this->average_depth;
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
     * @return float
     */
    public function getTurbidity(): float
    {
        return $this->turbidity;
    }

    /**
     * @param float $turbidity
     */
    public function setTurbidity(float $turbidity): void
    {
        $this->turbidity = $turbidity;
    }

    /**
     * @return float
     */
    public function getWaterVelocity(): float
    {
        return $this->water_velocity;
    }

    /**
     * @param float $water_velocity
     */
    public function setWaterVelocity(float $water_velocity): void
    {
        $this->water_velocity = $water_velocity;
        $this->flow_rate = $this->calculateFlowRate();
    }

    /**
     * @return float
     */
    public function getWidth(): float
    {
        return $this->width;
    }

    /**
     * @param float $width
     */
    public function setWidth(float $width): void
    {
        $this->width = $width;
        $this->flow_rate = $this->calculateFlowRate();
    }

    /**
     * @return float
     */
    public function getAverageDepth(): float
    {
        return $this->average_depth;
    }

    /**
     * @param float $average_depth
     */
    public function setAverageDepth(float $average_depth): void
    {
        $this->average_depth = $average_depth;
        $this->flow_rate = $this->calculateFlowRate();
    }

    /**
     * @return float
     */
    public function getFlowRate(): float
    {
        return $this->flow_rate;
    }

    /**
     * @return int
     */
    public function getWaterBodyId(): int
    {
        return $this->water_body_id;
    }

    /**
     * @param int $water_body_id
     */
    public function setWaterBodyId(int $water_body_id): void
    {
        $this->water_body_id = $water_body_id;
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
    public function getFieldTripId(): int
    {
        return $this->field_trip_id;
    }

    /**
     * @param int $field_trip_id
     */
    public function setFieldTripId(int $field_trip_id): void
    {
        $this->field_trip_id = $field_trip_id;
    }

    /**
     * @returns array
     */

    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'turbidity' => $this->turbidity,
            'water_velocity' => $this->water_velocity,
            'width' => $this->width,
            'average_depth' => $this->average_depth,
            'flow_rate' => $this->flow_rate,
            'water_body_id' => $this->water_body_id,
            'user_id' => $this->user_id,
            'field_trip_id' => $this->field_trip_id,
        ];
    }


}
