package com.shurdev.domain.models.myPlant

import com.shurdev.domain.models.plant.Illumination
import com.shurdev.domain.models.plant.ToxicCategory

data class PlantOtherInfo(
    val illumination: Illumination? = null,
    val toxicCategories: HashSet<ToxicCategory> = hashSetOf(),
)