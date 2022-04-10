package com.example.productssearch

import androidx.paging.PagingData
import com.example.productssearch.ProductTestData.productsList
import com.example.productssearch.domain.ProductsInteractor
import com.example.productssearch.domain.ProductsRepository
import com.example.productssearch.domain.models.Product
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ProductInteractorUnitTest {
    private val productsRepository: ProductsRepository = mock(ProductsRepository::class.java)
    lateinit var productsInteractor: ProductsInteractor

    @Before
    fun setup() {
        productsInteractor = ProductsInteractor(productsRepository)
    }

    @Test
    fun testRequestSuccessEmptyResponse() {
        //Given
        val pagingData: PagingData<Product> = PagingData.from(emptyList())
        val testSubcriber = TestSubscriber<PagingData<Product>>()
        Mockito.`when`(productsRepository.getProducts("123"))
            .thenReturn(Flowable.just(pagingData))

        //When
        productsInteractor.getProducts("123").subscribe(testSubcriber)

        //Then
        testSubcriber.assertComplete()
        testSubcriber.assertValue(pagingData)
        verify(productsRepository).getProducts("123")
    }

    @Test
    fun testRequestSuccessEmptyQuery() {
        //Given
        val pagingData: PagingData<Product> = PagingData.from(emptyList())
        val testSubcriber = TestSubscriber<PagingData<Product>>()
        Mockito.`when`(productsRepository.getProducts(""))
            .thenReturn(Flowable.just(pagingData))

        //When
        productsInteractor.getProducts("").subscribe(testSubcriber)

        //Then
        testSubcriber.assertComplete()
        testSubcriber.assertValue(pagingData)
        verify(productsRepository).getProducts("")
    }

    @Test
    fun testRequestSuccess() {
        //Given
        val pagingData = PagingData.from(productsList)
        val testSubcriber = TestSubscriber<PagingData<Product>>()
        Mockito.`when`(productsRepository.getProducts("123"))
            .thenReturn(Flowable.just(pagingData))

        //When
        productsInteractor.getProducts("123").subscribe(testSubcriber)

        //Then
        testSubcriber.assertComplete()
        testSubcriber.assertValue(pagingData)
        verify(productsRepository).getProducts("123")
    }

    @Test
    fun testRequestError() {
        //Given
        val testSubcriber = TestSubscriber<PagingData<Product>>()
        Mockito.`when`(productsRepository.getProducts("123")).thenReturn(Flowable.error(NullPointerException()))

        //When
        productsInteractor.getProducts("123").subscribe(testSubcriber)

        //Then
        testSubcriber.assertFailure(NullPointerException::class.java)
        verify(productsRepository).getProducts("123")
    }
}