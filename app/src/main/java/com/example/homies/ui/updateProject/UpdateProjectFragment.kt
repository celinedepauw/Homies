package com.example.homies.ui.updateProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homies.*
import com.example.homies.databinding.FragmentUpdateProjectBinding
import com.example.homies.ui.detailsProject.DetailsProjectFragmentArgs
import java.util.regex.Pattern

class UpdateProjectFragment : Fragment() {

    private var _binding : FragmentUpdateProjectBinding? = null
    private val binding get() = _binding!!

    private val myViewModel : UpdateProjectViewModel by viewModels()

    private val args : DetailsProjectFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        myViewModel.displayProject(args.project.projectId)

        val projectToUpdate = args.project

        val firstname = when{
            args.project.clientFirstname == ""    -> ""
            else                        -> "${args.project.clientFirstname.substring(0,1).uppercase()}${args.project.clientFirstname.substring(1)}"
        }
        val location = when{
            args.project.projectLocation == ""    -> ""
            else                        -> "${args.project.projectLocation.substring(0,1).uppercase()}${args.project.projectLocation.substring(1)}"
        }

        binding.apply{
            etUpdateName.setText(args.project.clientName.uppercase())
            etUpdateFirstname.setText(firstname)
            etUpdateTelephone.setText(args.project.clientPhone)
            etUpdateMail.setText(args.project.clientEmail)
            etUpdateAddress.setText(args.project.clientAddress)
            etUpdateSurface.setText(args.project.projectSurface.toString())
            etUpdateT.setText(args.project.projectT)
            etUpdateExposition.setText(args.project.projectExposition)
            etUpdateLandSurface.setText(args.project.projectLandSurface.toString())
            etUpdateMainRoom.setText(args.project.projectMainRoomSurface.toString())
            etUpdateKitchen.setText(args.project.projectKitchenSurface.toString())
            etUpdateRooms.setText(args.project.projectRooms)
            etUpdateRoomsSurface.setText(args.project.projectRoomsSurface)
            etUpdateBathrooms.setText(args.project.projectBathrooms)
            etUpdateOtherRooms.setText(args.project.projectOtherRooms)
            etUpdateBalcony.setText(args.project.projectBalcony)
            etUpdateGarage.setText(args.project.projectGarage)
            etUpdateLocalisation.setText(location)
            etUpdatePrice.setText(args.project.projectPrice.toString())
            etUpdateLandCharges.setText(args.project.landCharges.toString())
            etUpdateCharges.setText(args.project.charges)
            etUpdateComments.setText(args.project.comments)
            etUpdateAppointmentDate.setText(args.project.appointmentDate)
        }

        when(args.project.projectCategory){
            Category.BUY -> binding.rbUpdateProjectCategoryBuy.isChecked = true
            Category.SALE -> binding.rbUpdateProjectCategorySale.isChecked = true
        }
        when(args.project.projectType){
            Type.APPARTMENT -> binding.rbUpdateTypeAppartment.isChecked = true
            Type.HOUSE -> binding.rbUpdateTypeHouse.isChecked = true
            Type.LOCAL -> binding.rbUpdateTypeLocal.isChecked = true
            Type.LAND -> binding.rbUpdateTypeLand.isChecked = true
        }
        when(args.project.projectFinancing){
            Financing.BANK -> binding.rbUpdateFinancingBank.isChecked = true
            Financing.CASH -> binding.rbUpdateFinancingCash.isChecked = true
            Financing.MIDDLEMAN -> binding.rbUpdateFinancingMiddleman.isChecked = true
        }

        binding.btnUpdateRegister.setOnClickListener {
            val clientName = binding.etUpdateName.text.toString().trim()
            val clientFirstname = binding.etUpdateFirstname.text.toString().trim()
            val clientEmail = binding.etUpdateMail.text.toString().trim()

            val clientPhone = binding.etUpdateTelephone.text.toString().trim()
            val regexTel = "^0[1-7]\\d{8}$"
            val patternTel = Pattern.compile(regexTel)
            val matcherTel = patternTel.matcher(clientPhone)
            val telIsOk = matcherTel.find()

            val clientAddress = binding.etUpdateAddress.text.toString().trim()
            val projectCategory = when{
                binding.rbUpdateProjectCategoryBuy.isChecked    -> Category.BUY
                binding.rbUpdateProjectCategorySale.isChecked   -> Category.SALE
                else                                            -> Category.NOCATEGORY
            }
            val projectType = when{
                binding.rbUpdateTypeAppartment.isChecked        -> Type.APPARTMENT
                binding.rbUpdateTypeHouse.isChecked             -> Type.HOUSE
                binding.rbUpdateTypeLand.isChecked              -> Type.LAND
                binding.rbUpdateTypeLocal.isChecked             -> Type.LOCAL
                else                                            -> Type.NOTYPE
            }
            val projectT = binding.etUpdateT.text.toString().trim()
            val projectExposition = binding.etUpdateExposition.text.toString().trim()

            val projectSurface = binding.etUpdateSurface.text.toString().trim()

            val mainRoomSurface = binding.etUpdateMainRoom.text.toString().trim()
            val projectMainRoomSurface = if(mainRoomSurface.isEmpty()) "0" else mainRoomSurface

            val kitchenSurface = binding.etUpdateKitchen.text.toString().trim()
            val projectKitchenSurface = if(kitchenSurface.isEmpty()) "0" else kitchenSurface

            val projectRooms = binding.etUpdateRooms.text.toString().trim()
            val projectRoomsSurface = binding.etUpdateRoomsSurface.text.toString().trim()
            val projectBathrooms = binding.etUpdateBathrooms.text.toString().trim()
            val projectOtherRooms = binding.etUpdateOtherRooms.text.toString().trim()
            val projectBalcony = binding.etUpdateBalcony.text.toString().trim()
            val projectGarage = binding.etUpdateGarage.text.toString().trim()

            val landSurface = binding.etUpdateLandSurface.text.toString().trim()
            val projectLandSurface = if(landSurface.isEmpty()) "0" else landSurface

            val projectLocation = binding.etUpdateLocalisation.text.toString().trim()
            val projectCharges = binding.etUpdateCharges.text.toString().trim()

            val landCharges = binding.etUpdateLandCharges.text.toString().trim()
            val projectLandCharges = if(landCharges.isEmpty()) "0" else landCharges

            val price = binding.etUpdatePrice.text.toString().trim()
            val projectPrice = if(price.isEmpty()) "0" else price
            val projectFinancing = when{
                binding.rbUpdateFinancingBank.isChecked         -> Financing.BANK
                binding.rbUpdateFinancingCash.isChecked         -> Financing.CASH
                binding.rbUpdateFinancingMiddleman.isChecked    -> Financing.MIDDLEMAN
                else                                            -> Financing.NOFINANCING
            }
            val comments = binding.etUpdateComments.text.toString().trim()
            val appointmentDate = binding.etUpdateAppointmentDate.text.toString().trim()

            if(telIsOk || clientPhone == ""){
                projectToUpdate.let { project ->
                    myViewModel.updateProject(project.copy(
                        clientName = clientName,
                        clientFirstname = clientFirstname,
                        clientEmail = clientEmail,
                        clientPhone = clientPhone,
                        clientAddress = clientAddress,
                        projectCategory = projectCategory,
                        projectType = projectType,
                        projectT = projectT,
                        projectExposition = projectExposition,
                        projectSurface = projectSurface.toInt(),
                        projectLandSurface = projectLandSurface.toInt(),
                        projectMainRoomSurface = projectMainRoomSurface,
                        projectKitchenSurface = projectKitchenSurface,
                        projectRooms = projectRooms,
                        projectRoomsSurface = projectRoomsSurface,
                        projectBathrooms = projectBathrooms,
                        projectOtherRooms = projectOtherRooms,
                        projectBalcony = projectBalcony,
                        projectGarage = projectGarage,
                        projectLocation = projectLocation,
                        projectPrice = projectPrice.toInt(),
                        landCharges = projectLandCharges.toInt(),
                        charges = projectCharges,
                        projectFinancing = projectFinancing,
                        comments = comments,
                        appointmentDate = appointmentDate
                    ))
                }
            }
            else
                Toast.makeText(context, R.string.tel_non_valide, Toast.LENGTH_LONG).show()
        }

        myViewModel.isUpdated.observe(viewLifecycleOwner, Observer {
            if(it){
                val projectCategory = when{
                    binding.rbUpdateProjectCategoryBuy.isChecked    -> Category.BUY
                    binding.rbUpdateProjectCategorySale.isChecked   -> Category.SALE
                    else                                            -> Category.NOCATEGORY
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
                context?.showMyDialog("Le projet a bien été modifié")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}