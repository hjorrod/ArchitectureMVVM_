package com.inditex.architecturemvvm.domain.mappers

import com.inditex.architecturemvvm.data.dao.ExampleEntity
import com.inditex.architecturemvvm.data.model.ExampleDTO
import com.inditex.architecturemvvm.domain.model.ExampleBO
import com.inditex.architecturemvvm.presentation.model.ExampleVO

fun ExampleDTO.mapToExampleBO(): ExampleBO = ExampleBO (
        this.id,
        this.name,
        this.imageUrl
)

fun ExampleEntity.mapToExampleDTO(): ExampleDTO = ExampleDTO (
        this.id,
        this.name,
        this.imageUrl
)

fun ExampleDTO.mapToExampleEntity(): ExampleEntity = ExampleEntity (
        this.id,
        this.name,
        this.imageUrl
)

fun ExampleBO.mapToExampleVO(): ExampleVO = ExampleVO (
        this.id,
        this.name,
        this.imageUrl
)