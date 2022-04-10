package com.example.productssearch

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.productssearch.ProductTestData.productsList
import com.example.productssearch.domain.ProductsInteractor
import com.example.productssearch.domain.models.Product
import com.example.productssearch.presentation.MainViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subscribers.TestSubscriber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MainViewModelUnitTest {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.productId == newItem.productId
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }

        private val noListCallback = object : ListUpdateCallback {
            override fun onChanged(position: Int, count: Int, payload: Any?) {}
            override fun onMoved(fromPosition: Int, toPosition: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
    }
    private val productsInteractor: ProductsInteractor =
        Mockito.mock(ProductsInteractor::class.java)
    lateinit var mainViewModel: MainViewModel
    val differ = AsyncPagingDataDiffer(
        diffCallback = diffCallback,
        updateCallback = noListCallback,
        workerDispatcher = Dispatchers.Main
    )

    @get:Rule
    val coroutineRule = MainCoroutineRule()
    @get:Rule
    val rxRule = RxImmediateSchedulerRule()

    @Before
    fun setup() {
        mainViewModel = MainViewModel(productsInteractor)
    }

    @Test
    fun testRequestSuccess() {
        //Given
        val pagingData = PagingData.from(productsList)
        val testSubcriber = TestSubscriber<PagingData<Product>>()
        Mockito.`when`(productsInteractor.getProducts("123"))
            .thenReturn(Flowable.just(pagingData))

        //When
        mainViewModel.queryProducts("123").subscribe(testSubcriber)

        //Then
        testSubcriber.assertValue {
            runBlocking {
                differ.submitData(it)
                differ.snapshot().items == productsList
            }
        }
        Mockito.verify(productsInteractor).getProducts("123")
    }

    @Test
    fun testRequestSuccessEmptyResult() {
        //Given
        val pagingData: PagingData<Product> = PagingData.from(emptyList())
        val testSubcriber = TestSubscriber<PagingData<Product>>()
        Mockito.`when`(productsInteractor.getProducts("123"))
            .thenReturn(Flowable.just(pagingData))

        //When
        mainViewModel.queryProducts("123").subscribe(testSubcriber)

        //Then
        testSubcriber.assertValue {
            runBlocking {
                differ.submitData(it)
                differ.snapshot().items.isEmpty()
            }
        }
        Mockito.verify(productsInteractor).getProducts("123")
    }
}