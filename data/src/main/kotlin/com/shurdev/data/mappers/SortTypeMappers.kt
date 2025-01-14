package com.shurdev.data.mappers

import com.shurdev.data.models.SortDirection
import com.shurdev.data.models.SortField
import com.shurdev.domain.models.plant.SortType

fun SortType.toSortField(): SortField = when (this) {
    SortType.ByNameAscending -> SortField.Name
    SortType.ByNameDescending -> SortField.Name
    SortType.BySizeAscending -> SortField.Size
    SortType.BySizeDescending -> SortField.Size
}

fun SortType.toSortDirection(): SortDirection = when (this) {
    SortType.ByNameAscending -> SortDirection.Ascending
    SortType.ByNameDescending -> SortDirection.Descending
    SortType.BySizeAscending -> SortDirection.Ascending
    SortType.BySizeDescending -> SortDirection.Descending
}