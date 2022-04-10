package com.example.productssearch.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productssearch.App
import com.example.productssearch.R
import com.example.productssearch.databinding.ActivityMainBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private val TAG = MainActivity::class.simpleName
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private val disposable = CompositeDisposable()
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        mainViewModel= ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = ProductsAdapter()
        binding.productsList.layoutManager = LinearLayoutManager(this)
        binding.productsList.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        subscribeToQuery(null)
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.action_search)

        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_products)
        searchView.setOnQueryTextListener(this)
        searchView.isIconified = true
        searchView.setOnCloseListener {
            subscribeToQuery(null)
            false
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        subscribeToQuery(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    private fun subscribeToQuery(query: String?) {
        disposable.add(mainViewModel.queryProducts(query?:"").subscribe( {
            adapter.submitData(lifecycle, it)
        }, {
            Log.e(TAG, it.message?: "error processing query")
        }))
    }
}
