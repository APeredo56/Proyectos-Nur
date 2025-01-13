<?php

namespace Src\WaterBody\Domain;

enum WaterBodyType: string
{
    case RIVER = 'rio';
    case STREAM = 'arroyo';
    case LAKE = 'lago';
    case WETLAND = 'humedal';
}
