package sa.factory.iiotindustrialiot1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import sa.factory.iiotindustrialiot1.model.IiotIndustrialItem

@Entity(tableName = "industrial_iot_items_cache")
data class IiotIndustrialEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val priceSar: Double,
    val badge: String,
    val category: String,
    val status: String
) {
    fun toDomain(): IiotIndustrialItem = IiotIndustrialItem(
        id = id,
        title = title,
        description = description,
        priceSar = priceSar,
        badge = badge,
        category = category,
        status = status
    )
}
