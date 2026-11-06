package sa.factory.iiotindustrialiot1.domain.usecase

import sa.factory.iiotindustrialiot1.domain.repository.IiotIndustrialRepository
import sa.factory.iiotindustrialiot1.model.IiotIndustrialItem
import sa.factory.iiotindustrialiot1.model.IiotIndustrialOrder
import sa.factory.iiotindustrialiot1.model.IiotIndustrialTelemetry
import kotlinx.coroutines.flow.Flow

class GetCatalogUseCase(private val repository: IiotIndustrialRepository) {
    operator fun invoke(): Flow<List<IiotIndustrialItem>> = repository.catalogFlow
}

class ManageCartUseCase(private val repository: IiotIndustrialRepository) {
    val cart = repository.activeCart
    fun add(item: IiotIndustrialItem) = repository.addToCart(item)
    fun remove(item: IiotIndustrialItem) = repository.removeFromCart(item)
    fun clear() = repository.clearCart()
}

class ProcessCheckoutUseCase(private val repository: IiotIndustrialRepository) {
    suspend operator fun invoke(paymentMethod: String = "Mada Contactless EMV"): IiotIndustrialOrder =
        repository.checkout(paymentMethod)
}

class ObserveHardwareTelemetryUseCase(private val repository: IiotIndustrialRepository) {
    operator fun invoke(): Flow<IiotIndustrialTelemetry> = repository.telemetryFlow
}
