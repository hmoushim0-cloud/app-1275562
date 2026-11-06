package sa.factory.iiotindustrialiot1.domain.repository

import sa.factory.iiotindustrialiot1.model.IiotIndustrialItem
import sa.factory.iiotindustrialiot1.model.IiotIndustrialOrder
import sa.factory.iiotindustrialiot1.model.IiotIndustrialTelemetry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IiotIndustrialRepository {
    val catalogFlow: Flow<List<IiotIndustrialItem>>
    val activeCart: StateFlow<List<IiotIndustrialItem>>
    val telemetryFlow: Flow<IiotIndustrialTelemetry>
    
    fun addToCart(item: IiotIndustrialItem)
    fun removeFromCart(item: IiotIndustrialItem)
    fun clearCart()
    suspend fun checkout(paymentMethod: String): IiotIndustrialOrder
    suspend fun refreshCatalog()
}
