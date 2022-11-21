package com.android.desafiofinalstarwars.ui.left.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.desafiofinalstarwars.R
import com.android.desafiofinalstarwars.databinding.FragmentNavesBinding
import com.android.desafiofinalstarwars.model.Starship
import com.android.desafiofinalstarwars.ui.DetalhesView
import com.android.desafiofinalstarwars.ui.left.LeftFragment
import com.android.desafiofinalstarwars.ui.left.viewmodels.StarshipsViewModel
import com.android.desafiofinalstarwars.ui.left.adapters.StarshipsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarshipsFragment : Fragment() {

    private var _binding: FragmentNavesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModel<StarshipsViewModel>()

    private val starshipsList : ArrayList<Starship> = ArrayList()

    private val adapter by lazy {
        StarshipsAdapter()
    }

    private var isClicked = 0

    private val fromVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.fromvisible)}
    private val toVisible : Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.tovisible)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNavesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentNavesRecyclerview.adapter = adapter
        setObserver()
        viewModel.getApiStarships()

        adapter.itemClickListener = {
            isClicked = 1
            descriptionTabCall(it)
        }
        LeftFragment.onTabReselectedStarshipsListener = {
            isClicked -= 1
            descriptionTabCall()
        }

    }

    private fun descriptionTabCall(starship: Starship? = null) {
        if (isClicked == 1){
            binding.fragmentNavesRecyclerview.startAnimation(fromVisible)
            binding.fragmentNavesRecyclerview.visibility = View.GONE
            binding.fragmentViewDetails.root.startAnimation(toVisible)
            binding.fragmentViewDetails.root.visibility = View.VISIBLE
            DetalhesView(binding.fragmentViewDetails).bind(starship!!)
        } else if (isClicked == 0) {
            binding.fragmentNavesRecyclerview.startAnimation(toVisible)
            binding.fragmentNavesRecyclerview.visibility = View.VISIBLE
            binding.fragmentViewDetails.root.startAnimation(fromVisible)
            binding.fragmentViewDetails.root.visibility = View.GONE
        }
    }

    private fun setObserver(){
        viewModel.starshipResponse.observe(viewLifecycleOwner){
            it?.let {
                starshipsList.addAll(it.results!!)
                adapter.atualiza(starshipsList)
            }
        }
        viewModel.loadStateLiveData.observe(viewLifecycleOwner){
            handleProgressBar(it)
        }
        viewModel.starshipError.observe(viewLifecycleOwner){
            Toast.makeText(context, "Api Error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleProgressBar(state: StarshipsViewModel.State?) {
        when(state){
            StarshipsViewModel.State.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            StarshipsViewModel.State.LOADING_FINISHED -> binding.progressCircular.visibility = View.GONE
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}