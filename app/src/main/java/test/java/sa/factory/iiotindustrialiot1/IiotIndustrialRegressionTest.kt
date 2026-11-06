package sa.factory.iiotindustrialiot1

import sa.factory.iiotindustrialiot1.data.repository.IiotIndustrialRepositoryImpl
import sa.factory.iiotindustrialiot1.hardware.HardwareSensorDriver
import sa.factory.iiotindustrialiot1.model.IiotIndustrialItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class IiotIndustrialRegressionTest {

    @Test
    fun testCheckoutCalculationAndTax() = runBlocking {
        val sensorDriver = HardwareSensorDriver()
        val repository = IiotIndustrialRepositoryImpl(sensorDriver)

        val item1 = IiotIndustrialItem("t1", "خدمة تجريبية أ", "الوصف", 100.0, "معتمد", "عام")
        val item2 = IiotIndustrialItem("t2", "خدمة تجريبية ب", "الوصف", 200.0, "معتمد", "عام")

        repository.addToCart(item1)
        repository.addToCart(item2)

        val order = repository.checkout("Mada EMV")

        assertEquals(300.0, order.subtotalSar, 0.01)
        assertEquals(45.0, order.vatSar, 0.01)
        assertEquals(345.0, order.totalSar, 0.01)
        assertEquals(0, repository.activeCart.value.size)
    }
}
