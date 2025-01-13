<?php

namespace Src\Community\Infrastucture;

use App\Models\Community;
use Src\Community\Application\CommunityDTO;
use Src\Community\Application\CommunityRepository;
use Src\Community\Domain\CommunityEntity;

class EloquentCommunityRepository implements CommunityRepository
{
    public function create(CommunityEntity $communityEntity): CommunityDTO
    {
        $model = CommunityMapper::toEloquent($communityEntity);
        $model->save();
        return CommunityMapper::toDTO($model);
    }

    public function update(CommunityEntity $communityEntity): CommunityDTO
    {
        $model = Community::find($communityEntity->getId());
        $model->update($communityEntity->toArray());
        $model->load('municipality');
        return CommunityMapper::toDTO($model);
    }

    public function delete(int $id): bool
    {
        $model = Community::find($id);
        return $model->delete();
    }

    public function getById(int $id): CommunityDTO
    {
        $model = Community::with('municipality')->find($id);
        return CommunityMapper::toDTO($model);
    }

    public function getAll(): array
    {
        $models = Community::with('municipality')->get();
        $communities = [];
        foreach ($models as $model) {
            $communities[] = CommunityMapper::toDTO($model);
        }
        return $communities;
    }
}
