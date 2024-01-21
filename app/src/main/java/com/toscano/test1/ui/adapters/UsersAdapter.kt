package com.toscano.test1.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toscano.test1.R
import com.toscano.test1.data.entities.Users
import com.toscano.test1.databinding.UsersLayoutItemsBinding

class UsersAdapter () : RecyclerView.Adapter<UsersAdapter.ViewHolderUsers>() {

    //Creamos una variable adapter
    var listUsers: List<Users> = listOf()
    //Creamos una CLase ViewHolder
    class ViewHolderUsers(view: View) : RecyclerView.ViewHolder(view){

        //esta variable no necesita un lateinit para ser inicializda.
        private var binding: UsersLayoutItemsBinding = UsersLayoutItemsBinding.bind(view)

        //Encargada de la iteracion de cada usuario
        fun render (item: Users) {

            //Ingreso de datos que se implementaran en la Interfaz
            binding.txtUserName.text = item.name
            binding.txtUserDesc.text = item.desc

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

    //Se encarga de gestionar el numero de datos exsiten y ver cuantas posiciones tiene.
    //Funcion {}
    /*
    override fun getItemCount(): Int {

        return listUsers.size
    }
    */
    //Funcion Lineal
    override fun getItemCount(): Int = listUsers.size


    //Ingresa cada uno de los elemoentos de las dos clases anteriores, los une y los envia.
    override fun onBindViewHolder(holder: ViewHolderUsers, position: Int) {

        holder.render(listUsers[position])
    }

}