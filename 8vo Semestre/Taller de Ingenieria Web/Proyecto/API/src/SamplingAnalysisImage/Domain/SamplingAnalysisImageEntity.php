<?php

namespace Src\SamplingAnalysisImage\Domain;

class SamplingAnalysisImageEntity
{
    private int $id;
    private string $image_url;
    private int $sampling_analysis_id;

    public function __construct(int $id, string $image_url, int $sampling_analysis_id)
    {
        $this->id = $id;
        $this->image_url = $image_url;
        $this->sampling_analysis_id = $sampling_analysis_id;
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
    public function getImageUrl(): string
    {
        return $this->image_url;
    }

    /**
     * @param string $image_url
     */
    public function setImageUrl(string $image_url): void
    {
        $this->image_url = $image_url;
    }

    /**
     * @return int
     */
    public function getSamplinganalysisId(): int
    {
        return $this->sampling_analysis_id;
    }

    /**
     * @param int $sampling_analysis_id
     */
    public function setSamplinganalysisId(int $sampling_analysis_id): void
    {
        $this->sampling_analysis_id = $sampling_analysis_id;
    }



    public function toArray(): array
    {
        return [
            'id' => $this->id,
            'image_url' => $this->image_url,
            'sampling_analysis_id' => $this->sampling_analysis_id,
        ];
    }
}
