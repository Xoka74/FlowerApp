package com.shurdev.domain.models.plant

data class SearchOptions(
    val filters: PlantFilters = PlantFilters(),
    val sorting: SortType = SortType.ByNameAscending,
    val search: String? = null,
)