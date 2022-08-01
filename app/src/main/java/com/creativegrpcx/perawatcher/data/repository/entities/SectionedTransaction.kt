package com.creativegrpcx.perawatcher.data.repository.entities

import com.creativegrpcx.perawatcher.domain.types.CategoryType

data class SectionedTransaction(
    val id : String,
    val sectionType : CategoryType,
    val sectionItems : List<Transaction>
)
