package com.android.desafiofinalstarwars.ui.search.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentSearchCharactersBinding
import com.android.desafiofinalstarwars.model.Character
import com.android.desafiofinalstarwars.ui.DetailsView
import com.android.desafiofinalstarwars.ui.characters.adapters.CharactersAdapter
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabReselectedCharactersSearchListener
import com.android.desafiofinalstarwars.ui.search.SearchFragment.Companion.onTabSelectedCharactersSearchListener
import com.android.desafiofinalstarwars.ui.search.viewmodels.CharactersSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersSearchFragment : Fragment() {
    private var _binding: FragmentSearchCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val charactersList: ArrayList<Character> = arrayListOf()

    private val viewModel by viewModel<CharactersSearchViewModel>()

    private var isClicked = 0

    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private val adapter by lazy {
        CharactersAdapter()
    }

    private val recyclerView by lazy {
        binding.fragmentCharactersSearchRecyclerview
    }

    private val fromVisible: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.fromvisible
        )
    }

    private val toVisible: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.tovisible
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()

        setupSearch()
    }

    private fun setupSearch() {
        onTabSelectedCharactersSearchListener = {
            viewModel.filter = it
            charactersList.clear()
        }
    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter

        setObserver()

        viewModel.getApiCharactersSearch()

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }

        onTabReselectedCharactersSearchListener = {
            isClicked -= 1
            descriptionTabCall()
        }
    }

    private fun addScrollListenerAdapter(isLastPage : String?) {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                    val visibleItemCount = layoutManager.childCount
                    val totalItemVisble = visibleItemCount + pastVisibleItems
                    val totalItemCount = layoutManager.itemCount
                    if (totalItemVisble >= totalItemCount && isLastPage != null) {
                        removeScrollListenerAdapter()
                        viewModel.getApiCharactersSearch()
                    } else if (totalItemVisble >= totalItemCount && isLastPage == null){
                        Toast.makeText(context, "List end reached.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    private fun removeScrollListenerAdapter() {
        if (::scrollListener.isInitialized) {
            recyclerView.removeOnScrollListener(scrollListener)
        }
    }

    private fun descriptionTabCall(character: Character? = null) {
        val viewDetails = binding.fragmentViewDetails.root
        if (isClicked == 1) {
            recyclerView.startAnimation(fromVisible)
            recyclerView.visibility = View.GONE
            viewDetails.startAnimation(toVisible)
            viewDetails.visibility = View.VISIBLE
            DetailsView(binding.fragmentViewDetails).bind(character!!)
        } else if (isClicked == 0) {
            recyclerView.startAnimation(toVisible)
            recyclerView.visibility = View.VISIBLE
            viewDetails.startAnimation(fromVisible)
            viewDetails.visibility = View.GONE
        }
    }

    private fun setObserver() {
        Log.i(ContentValues.TAG, "setObserver: ")
        viewModel.characterResponse.observe(viewLifecycleOwner) {
            it?.let {
                charactersList.addAll(it.results!!)
                adapter.update(charactersList)
                removeScrollListenerAdapter()
                addScrollListenerAdapter(it.next)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner) {
            handleProgressBar(it)
        }
    }

    private fun handleProgressBar(state: CharactersSearchViewModel.State?) {
        when (state) {
            CharactersSearchViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            CharactersSearchViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility =
                View.GONE
            else -> {}
        }
    }
}
