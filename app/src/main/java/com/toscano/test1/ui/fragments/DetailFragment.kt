package com.toscano.test1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.toscano.test1.R
import com.toscano.test1.databinding.FragmentDetailBinding
import com.toscano.test1.ui.viewmodels.DetailViewModel


class DetailFragment : Fragment() {

    private val detailViewModel : DetailViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding

    //Variable Args, para devolver argumnetos
    val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.bind(inflater.inflate(R.layout.fragment_detail,container,false))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

        detailViewModel.loadInfoAnime(args.idAnime)
    }

    private fun initObservers(){

        detailViewModel.anime.observe(requireActivity()){
                anime ->  binding.txtIdAnime.text = anime.id.toString()
                          binding.txtNameAnime.text = anime.name
                          binding.imgAnime.load(anime.big_image)
                          binding.txtInfoAnime.text = anime.synopsis
        }

        detailViewModel.error.observe(requireActivity()){
            errorMesseage -> Snackbar.make(requireActivity(), binding.btnRefresh, errorMesseage, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun initListeners(){
        binding.btnRefresh.setOnClickListener {
            detailViewModel.loadInfoAnime(91)
        }
    }
}