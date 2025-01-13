<?php

namespace Src\Community\Infrastucture;

use App\Models\Community;
use Src\Community\Application\CommunityDTO;
use Src\Community\Domain\CommunityEntity;
use Src\Municipality\Infrastructure\MunicipalityMapper;
use Src\WaterBody\Infrastucture\WaterBodyMapper;

class CommunityMapper
{
    public static function toEloquent(CommunityEntity $communityEntity): Community
    {
        $eloquentCommunity = new Community($communityEntity->toArray());
        $eloquentCommunity->id = $communityEntity->getId();
        return $eloquentCommunity;
    }

    public static function toDTO(Community $eloquentCommunity, $depth = 1)
    {
        $communityDTO = new CommunityDTO(
            $eloquentCommunity->id,
            $eloquentCommunity->name,
            $eloquentCommunity->municipality_id,
        );
        if ($eloquentCommunity->municipality && $depth > 0) {
            $communityDTO->setMunicipality(MunicipalityMapper::toDTO($eloquentCommunity->municipality, $depth - 1));
        }
        if($eloquentCommunity->waterBodies && $depth > 0){
            $waterBodies = [];
            foreach ($eloquentCommunity->waterBodies as $waterBody) {
                $waterBodies[] = WaterBodyMapper::toDTO($waterBody, $depth - 1);
            }
            $communityDTO->setWaterBodies($waterBodies);
        }
        return $communityDTO;
    }
}
