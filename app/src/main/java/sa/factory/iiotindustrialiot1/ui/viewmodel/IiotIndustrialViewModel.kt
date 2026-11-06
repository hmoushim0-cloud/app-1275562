package sa.factory.iiotindustrialiot1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.iiotindustrialiot1.domain.usecase.*
import sa.factory.iiotindustrialiot1.model.IiotIndustrialItem
import sa.factory.iiotindustrialiot1.model.IiotIndustrialOrder
import sa.factory.iiotindustrialiot1.model.IiotIndustrialTelemetry
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class IiotIndustrialUiState(
    val catalog: List<IiotIndustrialItem> = emptyList(),
    val filteredCatalog: List<IiotIndustrialItem> = emptyList(),
    val selectedCategory: String = "الكل",
    val cart: List<IiotIndustrialItem> = emptyList(),
    val telemetry: IiotIndustrialTelemetry? = null,
    val lastOrder: IiotIndustrialOrder? = null,
    val isCheckingOut: Boolean = false,
    val activeTab: String = "home"
)

class IiotIndustrialViewModel(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val manageCartUseCase: ManageCartUseCase,
    private val processCheckoutUseCase: ProcessCheckoutUseCase,
    private val observeHardwareTelemetryUseCase: ObserveHardwareTelemetryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IiotIndustrialUiState())
    val uiState: StateFlow<IiotIndustrialUiState> = _uiState.asStateFlow()

    init {
        // Observe Catalog
        getCatalogUseCase().onEach { list ->
            _uiState.update { it.copy(catalog = list, filteredCatalog = list) }
        }.launchIn(viewModelScope)

        // Observe Cart
        manageCartUseCase.cart.onEach { cartItems ->
            _uiState.update { it.copy(cart = cartItems) }
        }.launchIn(viewModelScope)

        // Observe Telemetry
        observeHardwareTelemetryUseCase().onEach { telem ->
            _uiState.update { it.copy(telemetry = telem) }
        }.launchIn(viewModelScope)
    }

    fun selectCategory(category: String) {
        _uiState.update { state ->
            val filtered = if (category == "الكل") state.catalog else state.catalog.filter { it.category == category }
            state.copy(selectedCategory = category, filteredCatalog = filtered)
        }
    }

    fun switchTab(tab: String) {
        _uiState.update { it.copy(activeTab = tab) }
    }

    fun addToCart(item: IiotIndustrialItem) = manageCartUseCase.add(item)
    fun removeFromCart(item: IiotIndustrialItem) = manageCartUseCase.remove(item)

    fun checkout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isCheckingOut = true) }
            val order = processCheckoutUseCase()
            _uiState.update { it.copy(isCheckingOut = false, lastOrder = order, activeTab = "checkout") }
        }
    }
}
