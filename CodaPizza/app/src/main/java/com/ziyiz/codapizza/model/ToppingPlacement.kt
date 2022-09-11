package com.ziyiz.codapizza.model

import com.ziyiz.codapizza.R

enum class ToppingPlacement(
    val label: Int
) {
    Left(R.string.placement_left),
    Right(R.string.placement_right),
    All(R.string.placement_all)
}