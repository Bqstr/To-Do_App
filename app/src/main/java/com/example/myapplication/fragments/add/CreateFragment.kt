package com.example.myapplication.fragments.add

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.ToDoData
import com.example.myapplication.data.ToDoViewModel
import com.example.myapplication.databinding.FragmentCreateBinding
import com.example.myapplication.fragments.SharedViewModel


class CreateFragment : Fragment() {
private val mViewModel: ToDoViewModel by viewModels()
private val sViewModel:SharedViewModel by viewModels()
lateinit var binding :FragmentCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("1231231","inCreate ")
        binding = FragmentCreateBinding.inflate(inflater, container, false)

        var spiner =binding.spinnerInCreate
        var ball =binding.spinnerInCreate
        binding.spinnerInCreate.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                  0->  binding.priorityIndicator.setImageResource(R.drawable.red)
                  1-> binding.priorityIndicator.setImageResource(R.drawable.yellow)
                  else-> binding.priorityIndicator.setImageResource(R.drawable.gren)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }

//        binding.create.setOnClickListener{
//            var name =binding.editText.text.toString();
//            var des =binding.editText2.text.toString()
//            var priority:String =binding.spinner.selectedItem.toString();//check this pleeeeaseeeeee
//            //val action = CreateFragmentDi.actionExamInformationFragmentToExamScoreFragment(exam)
//           // findNavController().navigate(123)
//        }

        binding.name.requestFocus()


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
                menuInflater.inflate(R.menu.add_fragment_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.addToDo){
                    insertIntoDataBase()
                    //findNavController().navigate(R.id.)
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }




    private fun insertIntoDataBase() {
        var name = binding.name.text.toString()
        var des = binding.desInCreate.text.toString()
        var priority: String = binding.spinnerInCreate.selectedItem.toString()
        val kk =sViewModel.verifyDataFromUser(name,des)
        if(kk){
            val data = ToDoData(0,name,sViewModel.getPriority(priority),des)
            mViewModel.insertData(data)
            Toast.makeText(context,"Successfully added",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.listFragment)
        }
        else{
            Toast.makeText(context,"Please fill out all fields",Toast.LENGTH_SHORT).show()

        }


    }


}


