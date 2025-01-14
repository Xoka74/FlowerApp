package com.shurdev.domain.models.plant


data class PlantFilters(
    val toxicCategories: HashSet<ToxicCategory> = hashSetOf(),
    val illuminations: HashSet<Illumination> = hashSetOf(),
    val wateringFrequencies: HashSet<WateringFrequency> = hashSetOf(),
) {
    fun selectedCount(): Int = toxicCategories.size + illuminations.size + wateringFrequencies.size
}
