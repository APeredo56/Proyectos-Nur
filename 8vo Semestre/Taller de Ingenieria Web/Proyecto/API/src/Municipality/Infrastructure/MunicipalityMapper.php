<?php

namespace Src\Municipality\Infrastructure;

use App\Models\Municipality;
use Src\Community\Infrastucture\CommunityMapper;
use Src\Department\Infraestructure\DepartmentMapper;
use Src\Municipality\Application\MunicipalityDTO;
use Src\Municipality\Domain\MunicipalityEntity;

class MunicipalityMapper
{
    public static function toEloquent(MunicipalityEntity $municipalityEntity): Municipality
    {
        $eloquentMunicipality = new Municipality($municipalityEntity->toArray());
        $eloquentMunicipality->id = $municipalityEntity->getId();
        return $eloquentMunicipality;
    }

    public static function toDTO(Municipality $eloquentMunicipality, $depth = 1)
    {
        $municipalityDTO = new MunicipalityDTO(
            $eloquentMunicipality->id,
            $eloquentMunicipality->name,
            $eloquentMunicipality->department_id,
        );
        if ($eloquentMunicipality->department && $depth > 0) {
            $municipalityDTO->setDepartment(DepartmentMapper::toDTO($eloquentMunicipality->department, $depth - 1));
        }
        if ($eloquentMunicipality->communities && $depth > 0) {
            $communities = [];
            foreach ($eloquentMunicipality->communities as $community) {
                $communities[] = CommunityMapper::toDTO($community, $depth - 1);
            }
            $municipalityDTO->setCommunities($communities);
        }

        return $municipalityDTO;
    }
}
