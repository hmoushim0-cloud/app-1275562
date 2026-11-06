package sa.factory.iiotindustrialiot1.di

import sa.factory.iiotindustrialiot1.data.repository.IiotIndustrialRepositoryImpl
import sa.factory.iiotindustrialiot1.domain.repository.IiotIndustrialRepository
import sa.factory.iiotindustrialiot1.domain.usecase.*
import sa.factory.iiotindustrialiot1.hardware.HardwareSensorDriver
import sa.factory.iiotindustrialiot1.ui.viewmodel.IiotIndustrialViewModel

class DiContainer {
    val sensorDriver = HardwareSensorDriver()
    val repository: IiotIndustrialRepository = IiotIndustrialRepositoryImpl(sensorDriver)

    val getCatalogUseCase = GetCatalogUseCase(repository)
    val manageCartUseCase = ManageCartUseCase(repository)
    val processCheckoutUseCase = ProcessCheckoutUseCase(repository)
    val observeHardwareTelemetryUseCase = ObserveHardwareTelemetryUseCase(repository)

    fun provideViewModel(): IiotIndustrialViewModel = IiotIndustrialViewModel(
        getCatalogUseCase,
        manageCartUseCase,
        processCheckoutUseCase,
        observeHardwareTelemetryUseCase
    )
}
