package com.example.homies.ui.detailsProject

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.homies.Category
import com.example.homies.R
import com.example.homies.Type
import com.example.homies.databinding.FragmentDetailsProjectBinding
import com.example.homies.showMyDialog

class DetailsProjectFragment : Fragment() {

    private var _binding : FragmentDetailsProjectBinding? = null
    private val binding get() = _binding!!

    private val args : DetailsProjectFragmentArgs by navArgs()

    private val myViewModel : DetailsProjectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        myViewModel.displayProject(args.project.projectId)

        myViewModel.projectToDisplay.observe(viewLifecycleOwner, Observer {
            if(it != null){
                val firstname = when{
                    it.clientFirstname == ""    -> ""
                    else                        -> "${it.clientFirstname.substring(0,1).uppercase()}${it.clientFirstname.substring(1)}"
                }
                val location = when{
                    it.projectLocation == ""    -> ""
                    else                        -> "${it.projectLocation.substring(0,1).uppercase()}${it.projectLocation.substring(1)}"
                }
                val phoneNumber = when{
                    it.clientPhone == "" -> "Téléphone non communiqué"
                    else -> "${it.clientPhone.substring(0,2)} " +
                            "${it.clientPhone.substring(2,4)} " +
                            "${it.clientPhone.substring(4,6)} " +
                            "${it.clientPhone.substring(6,8)} " +
                            "${it.clientPhone.substring(8,10)}"
                }
                val priceStr = it.projectPrice.toString()
                val price = when{
                    priceStr.length == 5 -> "${priceStr.substring(0,2)} ${priceStr.substring(2,5)}€"
                    priceStr.length == 6 -> "${priceStr.substring(0,3)} ${priceStr.substring(3,6)}€"
                    priceStr.length == 7 -> "${priceStr.substring(0,1)} ${priceStr.substring(1,4)} ${priceStr.substring(4,7)}€"
                    else -> ""
                }

                val imageToShow = when(args.project.projectType){
                    Type.HOUSE -> R.drawable.maison
                    Type.APPARTMENT -> R.drawable.appartement
                    Type.LOCAL -> R.drawable.local
                    Type.LAND -> R.drawable.terrain
                    else -> R.drawable.logo
                }
                binding.ivDetails.setImageResource(imageToShow)
                binding.tvDetailsContact.text = "${it.clientName.uppercase()} $firstname"
                binding.tvDetailsRole.text = if(it.projectCategory == Category.BUY) "Acheteur" else "Vendeur"
                binding.tvDetailsTelephone.text = phoneNumber
                binding.tvDetailsMail.text = if(it.clientEmail == "") "Mail non communiqué" else it.clientEmail
                binding.tvDetailsAddress.text = if(it.clientAddress == "") "Adresse non communiquée" else it.clientAddress
                binding.tvDetailsType.text = if(it.projectT == "") "${it.projectType}" else "${it.projectType} T${it.projectT}"

                if(it.projectLandSurface == 0)
                    binding.tvDetailsLandSurface.visibility = View.GONE
                else
                    binding.tvDetailsLandSurface.text = "Surface du terrain : ${it.projectLandSurface} m²"

                when(it.projectType){
                    Type.LAND   -> {
                                    binding.tvDetailsRooms.visibility = View.GONE
                                    binding.tvDetailsSurface.visibility = View.GONE
                                    binding.tvDetailsRoomsSurface.visibility = View.GONE
                                    binding.tvDetailsMainRoom.visibility = View.GONE
                                    binding.tvDetailsKitchen.visibility = View.GONE
                                    binding.tvDetailsBathrooms.visibility = View.GONE
                                    binding.tvDetailsOtherRooms.visibility = View.GONE
                                    binding.tvDetailsBalcony.visibility = View.GONE
                                    binding.tvDetailsGarage.visibility = View.GONE
                    }
                    Type.LOCAL     ->  {
                                    binding.tvDetailsRooms.visibility = View.GONE
                                    binding.tvDetailsSurface.text = "Surface : ${it.projectSurface} m²"
                                    binding.tvDetailsRoomsSurface.visibility = View.GONE
                                    binding.tvDetailsMainRoom.visibility = View.GONE
                                    binding.tvDetailsKitchen.visibility = View.GONE
                                    binding.tvDetailsBathrooms.visibility = View.GONE
                                    binding.tvDetailsOtherRooms.visibility = View.GONE
                                    binding.tvDetailsBalcony.visibility = View.GONE
                                    binding.tvDetailsGarage.visibility = View.GONE
                    }
                    else        -> {
                                    binding.tvDetailsRooms.text = when(it.projectRooms){
                                        "" -> "Nombre de chambres non renseigné"
                                        "1" -> "1 chambre"
                                        else -> "${it.projectRooms} chambres"
                                    }
                                    binding.tvDetailsSurface.text =
                                        if(it.projectSurface == 0)
                                            "Surface non renseignée"
                                        else
                                            "Surface : ${it.projectSurface} m²"
                                    binding.tvDetailsRoomsSurface.text =
                                        if(it.projectRoomsSurface == "")
                                            "Surface des chambres non renseignée"
                                        else
                                            "Surface des chambres : ${it.projectRoomsSurface}"
                                    binding.tvDetailsMainRoom.text =
                                        if(it.projectMainRoomSurface == "")
                                            "Surface pièce à vivre non renseignée"
                                        else
                                            "Surface pièce à vivre : ${it.projectMainRoomSurface}"
                                    binding.tvDetailsKitchen.text =
                                        if(it.projectKitchenSurface == "")
                                            "Surface cuisine non renseignée"
                                        else
                                            "Surface cuisine : ${it.projectKitchenSurface}"
                                    binding.tvDetailsBathrooms.text =
                                        if(it.projectBathrooms == "")
                                            "Surface salle de bains non renseignée"
                                        else
                                            "Salle d'eau / Salle de bains : ${it.projectBathrooms}"
                                    binding.tvDetailsOtherRooms.text =
                                        if(it.projectOtherRooms == "")
                                            "Pas d'autres pièces"
                                        else
                                            "Autres pièces : ${it.projectOtherRooms}"
                                    binding.tvDetailsBalcony.text =
                                        if(it.projectBalcony == "")
                                            "Balcon / Terrasse : non renseigné"
                                        else
                                            "Balcon / Terrasse : ${it.projectBalcony}"
                                    binding.tvDetailsGarage.text =
                                        if(it.projectGarage == "")
                                            "Cave / Garage : non renseigné"
                                        else
                                            "Cave / Garage : ${it.projectGarage}"
                    }
                }
                binding.tvDetailsExposition.text =
                    if(it.projectExposition == "")
                        "Exposition non renseignée"
                    else
                        "Exposition : ${it.projectExposition}"
                binding.tvDetailsLocalization.text =
                    if(it.projectCategory == Category.BUY)
                        if(it.projectLocation =="") "Zone de recherche non renseignée" else "Zone de recherche : $location"
                    else
                        "Localisation du bien : $location"
                binding.tvDetailsPrice.text =
                    if(it.projectCategory == Category.BUY){
                        if(it.projectPrice == 0)
                            "Budget à définir"
                        else
                            "Budget : $price"
                    }
                    else{
                        when{
                            it.projectPrice == 0                              -> "Estimation à réaliser"
                            it.projectPrice !== 0 && it.projectSurface !==0   -> "Estimation : $price (${it.projectPrice / it.projectSurface}€/m²)"
                            it.projectSurface == 0                            -> "Prix au m² indisponible"
                            else                                              -> "Information non disponible"
                        }
                    }
                when(it.projectCategory){
                    Category.BUY -> {
                        binding.tvDetailsCharges.visibility = View.GONE
                        binding.tvDetailsLandCharges.visibility = View.GONE
                    }
                    Category.SALE -> {
                        binding.tvDetailsCharges.text =
                            if(it.charges == "")
                                "Charges non renseignées"
                            else
                                "Charges de copropriété : ${it.charges}"
                        binding.tvDetailsLandCharges.text =
                            if(it.landCharges == 0)
                                "Taxe foncière non renseignée"
                            else
                                "Taxe Foncière : ${it.landCharges}€"
                    }
                }
                binding.tvDetailsGarage.text =
                    if(it.projectGarage == "")
                        "Cave / Garage : non renseigné"
                    else
                        "Cave / Garage : ${it.projectGarage}"
                binding.tvDetailsFinancing.text = "Financement : ${it.projectFinancing}"
                binding.tvDetailsComments.text =
                    if(it.comments =="")
                        "Informations complémentaires à compléter"
                    else
                        "Informations complémentaires :\n${it.comments}"
                binding.tvDetailsDate.text =
                    if(it.appointmentDate =="")
                        "Pas de RDV"
                    else
                        "Date de RDV : ${it.appointmentDate}"

            }
        })

        binding.btnDetailsUpdate.setOnClickListener {
            navController.navigate(DetailsProjectFragmentDirections.actionDetailsProjectFragmentToUpdateProjectFragment(args.project))
        }

        binding.btnDetailsDelete.setOnClickListener {
            fun Context.confirmDelete() {
                //val msg = message ?: this.getString(resId)
                AlertDialog.Builder(this).run {
                    setTitle("Suppression d'un projet")
                    setMessage("Etes-vous sûr de vouloir supprimer ce projet ?")
                    setPositiveButton("Oui") { dialog, _ ->
                        myViewModel.deleteProject(args.project)
                        dialog.cancel()
                    }
                    setNegativeButton("Non") { dialog, _ -> dialog.cancel() }
                    show()
                }
            }
            context?.confirmDelete()
        }

        myViewModel.isDeleted.observe(viewLifecycleOwner, Observer {
            if(it){
                val fragmentToDisplay =
                    if(args.project.projectCategory == Category.BUY)
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
                context?.showMyDialog("Le projet a bien été supprimé")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}