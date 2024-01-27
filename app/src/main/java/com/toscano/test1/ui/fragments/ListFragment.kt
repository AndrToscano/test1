package com.toscano.test1.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.toscano.test1.R
import com.toscano.test1.databinding.FragmentListBinding
import com.toscano.test1.logic.entities.FullInfoAnimeLG
import com.toscano.test1.logic.login.jikan.JikanGetTopAnimesUserCase
import com.toscano.test1.ui.adapters.UsersAdapterDiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    //Creamos una variable para la lista
    private var userList : MutableList<FullInfoAnimeLG> = ArrayList()


    //Creamos el adaptador como una variable de clase
    private var userDiffAdapter = UsersAdapterDiffUtil({deleteDiffUsers(it)}, {selectAnime(it)})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.bind(inflater.inflate(R.layout.fragment_list, container, false))
        return binding.root
    }

    //Ciclo de Vida de los Fragments
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
    }

    //Inicializamos nuestro RecyclerView
    private fun initRecycleView(){

        binding.rvUsers.adapter = userDiffAdapter
        binding.rvUsers.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)

        loadDataRecyclerView()

    }

    private fun loadDataRecyclerView(){

        lifecycleScope.launch (Dispatchers.Main){
            binding.progressBar.visibility = View.VISIBLE

            //Llamado intermediode de dos estados
            val resp = withContext(Dispatchers.IO){
                JikanGetTopAnimesUserCase().invoke()
            }

            resp.onSuccess {
                    listAnime -> userList.addAll(listAnime)

                insertUsersDiff(userList)
            }

            resp.onFailure {
                    ex -> Snackbar.make(requireActivity(), binding.rvUsers, ex.message.toString(), Snackbar.LENGTH_LONG).show()
            }
            binding.progressBar.visibility = View.GONE
        }
    }


    private fun insertUsersDiff(animes: List<FullInfoAnimeLG>){

        userList.addAll(animes)
        Log.d("LST",userList.toString())
        userDiffAdapter.submitList(userList.toList())

    }

    private fun deleteDiffUsers(position: Int){

        userList.removeAt(position)
        userDiffAdapter.submitList(userList.toList())

    }

    private fun selectAnime(animes: FullInfoAnimeLG){

        //Forma Tradicional
        //Snackbar.make(requireActivity(), binding.rvUsers, animes.name, Snackbar.LENGTH_LONG).show()

        //Forma Navigation Component
        //findNavController().navigate(R.id.action_listFragment_to_detailFragment)

        //Forma Safe Args
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(idAnime = animes.id))

    }

}