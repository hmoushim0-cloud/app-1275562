package sa.factory.iiotindustrialiot1.data.remote

import sa.factory.iiotindustrialiot1.model.IiotIndustrialItem
import sa.factory.iiotindustrialiot1.model.IiotIndustrialOrder

interface IiotIndustrialApiService {
    suspend fun fetchCatalog(): List<IiotIndustrialItem>
    suspend fun submitOrder(order: IiotIndustrialOrder): Boolean
}
