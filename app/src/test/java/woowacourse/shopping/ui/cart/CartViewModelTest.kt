package woowacourse.shopping.ui.cart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.shopping.FakeCartRepository
import woowacourse.shopping.domain.repository.CartRepository
import woowacourse.shopping.getOrAwaitValue

class CartViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartRepository: CartRepository

    @Before
    fun setUp() {
        cartRepository = FakeCartRepository()
        cartViewModel = CartViewModel(cartRepository)
    }

    @Test
    fun `장바구니에_담긴_모든_물품들을_조회한다`() {
        cartViewModel.getAllCartProducts()
        val value = cartViewModel.cartProducts.getOrAwaitValue()
        assertThat(value[2].name).isEqualTo("우테코 삼겹살")
    }
}
