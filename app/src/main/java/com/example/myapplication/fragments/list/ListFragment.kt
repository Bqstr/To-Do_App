package com.example.myapplication.fragments.list

import android.app.AlertDialog
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import androidx.recyclerview.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ToDo
import com.example.myapplication.data.ToDoDao
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.ToDoViewModel
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.fragments.SharedViewModel
import com.example.myapplication.fragments.update.ChangeFragment
import org.w3c.dom.Text


class ListFragment : Fragment() {
    private val mToDoViewModel :ToDoViewModel by viewModels()
     var _view :FragmentListBinding? =null
      private val view get() =_view!!
    private val mSharedViewModel :SharedViewModel  by viewModels()
    private val adapter:ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         _view  =FragmentListBinding.inflate(inflater,container,false)
        view.lifecycleOwner =this
        view.mSharedViewModel =mSharedViewModel
        var recyclerView = view.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager =LinearLayoutManager(requireActivity())
       //Log.d("lastovaya",mToDoViewModel.getSome.get(mToDoViewModel.getSome.size-1).get(1).title)
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {

            data->
            run {
                mSharedViewModel.checkIfDataBaseIsEmpty(data)
//  becouse of this method of creating variables ,functions another observers nemnogo lagaet i kogda udalyaesh odin object iz dvuh pri vozvrashenii na milesicundu poyavlayetsya eta  "epmty" chtuka
            //ya ispolzuy tolko dalya prikola ,tipa hz , v video tak
            //moshet eto vashnaya hernya
            //hochesh ubrat ettu herny ubery natpis below and vtoroy observer  i uncomment to cho chut nizhe
                adapter.setData(data);


//                if(data.isEmpty()) view.listIsEmpty.visibility =View.VISIBLE
//                else view.listIsEmpty.visibility =View.GONE
            }
        })





        listeners()




        return view.root;

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()

    }
    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.list_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    if(menuItem.itemId==R.id.delete_all){
                        Toast.makeText(requireContext(),"do yOu WAnnA Deleta all'",Toast.LENGTH_LONG).show()
                        confirmDelete()
                        Toast.makeText(context ,"All data Deleted",Toast.LENGTH_LONG).show();
                    }
                // Validate and handle the selected menu item
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    fun listeners() {
    }
    fun confirmDelete(){
        val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){
            _,_->
            mToDoViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully Removed Everything!",
                Toast.LENGTH_SHORT
            ).show()

        }
        builder.setNegativeButton("No"){_,_->}
            builder.setTitle("Delete Everything")
            builder.setMessage("Are you sure about it ")
            builder.create().show();

    }

    override fun onDestroyView() {

        super.onDestroyView()
        _view =null

    }
}