package com.example.myapplication.fragments.list

import android.app.AlertDialog
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.Animation
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.ToDoViewModel
import com.example.myapplication.data.models.Priority
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.fragments.SharedViewModel
import com.example.myapplication.fragments.list.adapter.ListAdapter
import com.google.android.material.snackbar.Snackbar
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import jp.wasabeef.recyclerview.animators.LandingAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class ListFragment : Fragment(),SearchView.OnQueryTextListener {
    private val mToDoViewModel :ToDoViewModel by viewModels()
     var _view :FragmentListBinding? =null
      private val view get() =_view!!
    private val mSharedViewModel :SharedViewModel  by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("Hello , sorry chto musory zdes'")

         _view  =FragmentListBinding.inflate(inflater,container,false)
        view.lifecycleOwner =this
        view.mSharedViewModel =mSharedViewModel

        setUprecyclerView()
        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {

            data->
            run {
                mSharedViewModel.checkIfDataBaseIsEmpty(data)
                adapter.setData(data);
            /*
            becouse of this method of creating variables ,functions another observers nemnogo lagaet i kogda udalyaesh odin object iz dvuh pri vozvrashenii na milesicundu poyavlayetsya eta  "epmty" chtuka
            ya ispolzuy tolko dalya prikola ,tipa hz , v video tak
            moshet eto vashnaya hernya
             hochesh ubrat ettu herny ubery natpis below and vtoroy observer  i uncomment to cho chut nizhe
             */

            //forget this comms , this bag fixed








//                if(data.isEmpty()) view.listIsEmpty.visibility =View.VISIBLE
//                else view.listIsEmpty.visibility =View.GONE
            }
        })












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

                val search =menu.findItem(R.id.menu_search)
                val searchView =search.actionView as? SearchView
                searchView?.isSubmitButtonEnabled =true
                searchView?.setOnQueryTextListener(this@ListFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when(menuItem.itemId) {
                        R.id.delete_all -> {
                            Toast.makeText(
                                requireContext(),
                                "do yOu WAnnA Deleta all'",
                                Toast.LENGTH_LONG
                            ).show()
                            confirmDelete()
                            Toast.makeText(context, "All data Deleted", Toast.LENGTH_LONG).show();
                                            }
                        R.id.highestPriority -> mToDoViewModel.sorttoHighest.observe(viewLifecycleOwner, Observer {adapter.setData(it)} )
                        R.id.lowestPriority -> mToDoViewModel.sortToLowest.observe(viewLifecycleOwner, Observer {adapter.setData(it)} )

                        R.id.linerlLayout -> { changeLayout(); }
                    }
                // Validate and handle the selected menu item
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun changeLayout() {
        //nado realizovat' tipa menayesh lineral layouty i sohronat' na kokom layoute byl
        //esli poluchitsya dashe kogda polnost'u zakrivaesh i otrkrivayesh prilozhenie zanovo
        val recyclerView = view.recycler
               // recyclerView.adapter = adapter
        recyclerView.layoutManager =GridLayoutManager(requireActivity(),2)

        //kostil ebany
        adapter.setData(adapter.arr);
            }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDEleteCallBAck  =object:SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem  =adapter.arr[viewHolder.adapterPosition]
                mToDoViewModel.deleteData(deletedItem)
                Log.d("12312312321","${deletedItem.title} ${deletedItem.descriptions}")
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                restoreDeletedData(viewHolder.itemView, deletedItem, viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper =ItemTouchHelper(swipeToDEleteCallBAck)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(view:View ,deletedItem:ToDoData,position: Int){
        val snackbar =Snackbar.make(view,"deleted item restored ", Snackbar.LENGTH_LONG)
        snackbar.setAction("undo"){
            mToDoViewModel.insertData(deletedItem);
            adapter.notifyItemChanged(position)
        }
        snackbar.show()
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
    fun setUprecyclerView(){
        val recyclerView =view.recycler
        recyclerView.adapter = adapter
        recyclerView.layoutManager =GridLayoutManager(requireActivity(),2)


        //Animation
        recyclerView.itemAnimator = FadeInAnimator()
        recyclerView.itemAnimator?.apply {
            addDuration = 250

        }




        swipeToDelete(recyclerView)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _view =null
    }
    override fun onQueryTextSubmit(text: String?): Boolean {
        if(text!=null){
            searchThroughDatabase(text)
        }
        return true
    }

    override fun onQueryTextChange(text: String?): Boolean {
        if(text!=null){
            searchThroughDatabase(text)
        }
        return true
    }
fun searchThroughDatabase(txt:String){
    var str ="%$txt%"
    mToDoViewModel.searchData(str).observe(this, Observer{
        list ->
            list?.let{
                adapter.setData(it)
            }
    })
}

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}