package com.example.productssearch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productssearch.domain.ProductsInteractor
import com.example.productssearch.presentation.MainViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
class ViewModelModule {
    @Provides
    fun viewModelFactory(viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>):
            ViewModelProvider.Factory = ViewModelFactory(viewModels)

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun addMainViewModel(productsInteractor: ProductsInteractor):
            ViewModel = MainViewModel(productsInteractor)
}
