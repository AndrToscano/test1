package com.toscano.test1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.toscano.test1.R
import com.toscano.test1.databinding.UsersLayoutItemsBinding
import com.toscano.test1.logic.entities.FullInfoAnimeLG

class UsersAdapterDiffUtil (private val onDeleteItem : (Int )-> Unit,
                            private val onSelectItem: ( FullInfoAnimeLG ) -> Unit) :
                            ListAdapter<FullInfoAnimeLG, UsersAdapterDiffUtil.ViewHolderUsers>(DiffUtilUserCallBack) {


    //Creamos una CLase ViewHolder
    class ViewHolderUsers(view: View) : RecyclerView.ViewHolder(view){

        //esta variable no necesita un lateinit para ser inicializda.
        private var binding: UsersLayoutItemsBinding = UsersLayoutItemsBinding.bind(view)

        //Encargada de la iteracion de cada usuario
        fun render (item: FullInfoAnimeLG, onDeleteItem : ( Int)-> Unit, onSelectItem: ( FullInfoAnimeLG ) -> Unit) {

            //Ingreso de datos que se implementaran en la Interfaz
            binding.txtUserName.text = item.name
            binding.txtUserDesc.text = item.synopsis
            binding.imgUser.load(item.big_image)

            binding.btnBorrar.setOnClickListener {
                onDeleteItem(adapterPosition)
            }

            binding.imgUser.setOnClickListener {
                onSelectItem(item)
            }
        }
    }

    //Implementacion de la Clases Abstractas
    //Encargada de crear el Holder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUsers {

        //Encargado del inflado de la informacion
        val inflater = LayoutInflater.from(parent.context)

        //Se tiene el control del UsersItems
        return ViewHolderUsers(inflater.inflate(R.layout.users_layout_items, parent,false))
    }

    //Ingresa cada uno de los elemoentos de las dos clases anteriores, los une y los envia.
    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {

        holder.render(getItem(position), onDeleteItem, onSelectItem)
    }

    private object DiffUtilUserCallBack : DiffUtil.ItemCallback<FullInfoAnimeLG>(){

        //Elementos que ingresan y se comparan items
        override fun areItemsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {

            return (oldItem.id == newItem.id)
        }

        //Elementos que ingresan y se comparan el contenido
        override fun areContentsTheSame(oldItem: FullInfoAnimeLG, newItem: FullInfoAnimeLG): Boolean {

            return (oldItem == newItem)
        }


    }

}