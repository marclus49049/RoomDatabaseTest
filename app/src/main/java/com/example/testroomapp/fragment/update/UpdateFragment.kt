package com.example.testroomapp.fragment.update

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testroomapp.R
import com.example.testroomapp.data.User
import com.example.testroomapp.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private val mUserViewModel: UserViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.editFirstName.setText(args.currentUser.firstName)
        view.editLastName.setText(args.currentUser.lastName)
        view.editAge.setText(args.currentUser.age.toString())
        view.updateButton.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val firstName = editFirstName.text.toString()
        val lastName = editLastName.text.toString()
        val age = Integer.parseInt(editAge.text.toString())
        if(inputCheck(firstName, lastName, editAge.text)){
            // Create User Object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            // Update the User
            mUserViewModel.updateUser(updatedUser)
            Snackbar.make(requireView(), "Updated Successfully", Snackbar.LENGTH_SHORT).show()
            closeKeyboard()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Snackbar.make(requireView(), "Please fill out all fields", Snackbar.LENGTH_SHORT).show()
        }

    }
    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    private fun closeKeyboard() {
        val view: View? = requireActivity().currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete -> {
                deleteUser()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){
            _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Snackbar.make(requireView(), "Successfully removed ${args.currentUser.firstName}", Snackbar.LENGTH_SHORT).show()
            closeKeyboard()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _ -> }
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }
}