package com.example.myapplication.fragments.update

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.ToDoViewModel
import com.example.myapplication.data.models.Priority
import com.example.myapplication.databinding.FragmentChangeBinding
import com.example.myapplication.fragments.SharedViewModel


//ya zanimalsa implementing of update table(neudachno)
//








class ChangeFragment  : Fragment() {
    private val mViewModel: ToDoViewModel by viewModels()
    private val sViewModel: SharedViewModel by viewModels()

 var _binding : FragmentChangeBinding? =null
    val binding   get() =_binding!!


    private val args by navArgs<ChangeFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding  = FragmentChangeBinding.inflate(inflater,container,false)
        binding.args =args



    //this should to work as attr in layout ,bc of args ,but can't find setSelectedItem in Spinner class
    binding.spinnerInChange.setSelection(sViewModel.priorityToInt(args.currentItem.priority))


//not transferred to sViewModel bc its hard ,try if you can
        binding.spinnerInChange.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {

                    0 -> binding.priorityInChange.setImageResource(R.drawable.red)
                    1 -> binding.priorityInChange.setImageResource(R.drawable.yellow)
                    else -> binding.priorityInChange.setImageResource(R.drawable.gren)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMenu()
    }
    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.change_fragment_menu, menu)
            }


            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                if(menuItem.itemId==R.id.deleteToDo){
                    deleteItem()
                }
                else if(menuItem.itemId==R.id.saveToDo){
                    sendUpdatedData()

                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }




fun deleteItem(){
    mViewModel.deleteData(args.currentItem)
    findNavController().navigate(R.id.action_changeFragment_to_listFragment)
}
    fun sendUpdatedData(){
        var name =binding.nameInchage.text.toString();
        var des =binding.desInChange.text.toString()
        var priority =sViewModel.getPriority(binding.spinnerInChange.selectedItem.toString())
        val updatedData =ToDoData(args.currentItem.id,name,priority,des)

        var check =sViewModel.verifyDataFromUser(name,des);
        if(check){
            mViewModel.updateData(updatedData)
            Log.d("dsbshdbjdsc","in change    ${updatedData.id.toString()}")
            Toast.makeText(context,"builded SUCSESFULE",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_changeFragment_to_listFragment)
        }else{
            Toast.makeText(context,"Fill in Fields",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding =null
    }

}