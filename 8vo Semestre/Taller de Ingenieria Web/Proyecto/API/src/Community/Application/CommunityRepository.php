<?php

namespace Src\Community\Application;

use Src\Community\Domain\CommunityEntity;

interface CommunityRepository
{
    public function create(CommunityEntity $communityEntity): CommunityDTO;
    public function update(CommunityEntity $communityEntity): CommunityDTO;
    public function delete(int $id): bool;
    public function getById(int $id): CommunityDTO;
    public function getAll(): array;
}
