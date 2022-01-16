package com.example.homies.ui.addProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.homies.*
import com.example.homies.databinding.FragmentAddProjectBinding
import com.example.homies.databinding.FragmentBuyersBinding
import com.example.homies.room.project.Project
import java.util.regex.Matcher
import java.util.regex.Pattern

class AddProjectFragment : Fragment() {
    private val myViewModel : AddProjectViewModel by viewModels()
    private var _binding : FragmentAddProjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        binding.btnAddRegister.setOnClickListener {
            val clientName = binding.etAddName.text.toString().trim()
            val clientFirstname = binding.etAddFirstname.text.toString().trim()
            val clientEmail = binding.etAddMail.text.toString().trim()

            val clientPhone = binding.etAddTelephone.text.toString().trim()
            val regexTel = "^0[1-7]\\d{8}$"
            val patternTel = Pattern.compile(regexTel)
            val matcherTel = patternTel.matcher(clientPhone)
            val telIsOk = matcherTel.find()

            val clientAddress = binding.etAddAddress.text.toString().trim()
            val projectCategory = when{
                binding.rbAddProjectCategoryBuy.isChecked   -> Category.BUY
                binding.rbAddProjectCategorySale.isChecked  -> Category.SALE
                else                                        -> Category.NOCATEGORY
            }
            val projectType = when{
                binding.rbAddTypeAppartment.isChecked       -> Type.APPARTMENT
                binding.rbAddTypeHouse.isChecked            -> Type.HOUSE
                binding.rbAddTypeLand.isChecked             -> Type.LAND
                binding.rbAddTypeLocal.isChecked            -> Type.LOCAL
                else                                        -> Type.NOTYPE
            }
            val projectT = binding.etAddT.text.toString().trim()

            val surface = binding.etAddSurface.text.toString().trim()
            val projectSurface = if(surface.isEmpty()) "0" else surface

            val projectExposition = binding.etAddExposition.text.toString().trim()

            val mainRoomSurface = binding.etAddMainRoom.text.toString().trim()
            val projectMainRoomSurface = if(mainRoomSurface.isEmpty()) "0" else mainRoomSurface

            val kitchenSurface = binding.etAddKitchen.text.toString().trim()
            val projectKitchenSurface = if(kitchenSurface.isEmpty()) "0" else kitchenSurface

            val projectRooms = binding.etAddRooms.text.toString().trim()
            val projectRoomsSurface = binding.etAddRoomsSurface.text.toString().trim()
            val projectBathrooms = binding.etAddBathrooms.text.toString().trim()
            val projectOtherRooms = binding.etAddOtherRooms.text.toString().trim()
            val projectBalcony = binding.etAddBalcony.text.toString().trim()
            val projectGarage = binding.etAddGarage.text.toString().trim()

            val landSurface = binding.etAddLandSurface.text.toString().trim()
            val projectLandSurface = if(landSurface.isEmpty()) "0" else landSurface

            val projectLocation = binding.etAddLocalisation.text.toString().trim()
            val projectCharges = binding.etAddCharges.text.toString().trim()
            val landCharges = binding.etAddLandCharges.text.toString().trim()
            val projectLandCharges = if(landCharges.isEmpty()) "0" else landCharges
            val price = binding.etAddPrice.text.toString().trim()
            val projectPrice = if(price.isEmpty()) "0" else price
            val projectFinancing = when{
                binding.rbAddFinancingBank.isChecked        -> Financing.BANK
                binding.rbAddFinancingCash.isChecked        -> Financing.CASH
                binding.rbAddFinancingMiddleman.isChecked   -> Financing.MIDDLEMAN
                else                                        -> Financing.NOFINANCING
            }
            val comments = binding.etAddComments.text.toString().trim()
            val appointmentDate = binding.etAddAppointmentDate.text.toString().trim()

            if(projectCategory !== Category.NOCATEGORY){
                if(telIsOk || clientPhone == ""){
                    val newProject = Project(
                        0,
                        clientName,
                        clientFirstname,
                        clientEmail,
                        clientPhone,
                        clientAddress,
                        projectCategory,
                        projectType,
                        projectT,
                        projectExposition,
                        projectSurface.toInt(),
                        projectLandSurface.toInt(),
                        projectMainRoomSurface,
                        projectKitchenSurface,
                        projectRooms,
                        projectRoomsSurface,
                        projectBathrooms,
                        projectOtherRooms,
                        projectBalcony,
                        projectGarage,
                        projectLocation,
                        projectPrice.toInt(),
                        projectLandCharges.toInt(),
                        projectCharges,
                        projectFinancing,
                        comments,
                        appointmentDate
                    )
                    myViewModel.addProject(newProject)
                }
                else
                    Toast.makeText(context, R.string.tel_non_valide, Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(context, R.string.choisir_entre_achat_et_vente, Toast.LENGTH_LONG).show()
        }

        myViewModel.isCreated.observe(viewLifecycleOwner, Observer {
            if(it){
                val projectCategory = when{
                    binding.rbAddProjectCategoryBuy.isChecked   -> Category.BUY
                    binding.rbAddProjectCategorySale.isChecked  -> Category.SALE
                    else                                        -> Category.NOCATEGORY
                }
                val fragmentToDisplay =
                    if(projectCategory == Category.BUY)
                        R.id.buyersFragment
                    else
                        R.id.salersFragment
                navController.popBackStack()
                navController.navigate(fragmentToDisplay)
            }
        })

        myViewModel.messageUser.observe(viewLifecycleOwner, Observer {
            it.get()?.let{
                //Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                context?.showMyDialog("Le projet a bien été ajouté")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}