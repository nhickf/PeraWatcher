package com.creativegrpcx.perawatcher.repository.entities

import com.creativegrpcx.perawatcher.types.CategoryType

data class SectionedTransaction(
    val id : String,
    val sectionType : CategoryType,
    val sectionItems : List<Transaction>
)