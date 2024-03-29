package com.example.pft


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.pft.models.ApiClient
import com.example.pft.models.ReclamoCompleto
import com.exception.MyException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMenuEstudiante : Fragment() {
    var callReclamos: Call<List<ReclamoCompleto>>? = null
    val apiService = ApiClient.apiService
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).enableBackButton()
        val view = inflater.inflate(R.layout.fragment_menu_estudiante, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity

        val textViewEstudiante = view.rootView.findViewById<TextView>(R.id.textViewTituloEstudiante)
        textViewEstudiante.text = "Estudiante - "+ mainActivity.usuarioLogueado?.nombreUsuario
        cargarReclamos()
        crearReclamo()
        verReclamo()
        volver()
        facebookUtec()
        instagramUtec()
        xUtec()
        cerrarSesion()
    }

    fun crearReclamo() {
        val buttonCrearReclamos = requireActivity().findViewById<Button>(R.id.buttonCrearReclamo)
        buttonCrearReclamos.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenuEstudiante2_to_fragmentCrearReclamo2)
        }
    }

    fun verReclamo() {
        val buttonVerReclamos = requireActivity().findViewById<Button>(R.id.buttonVerReclamo)
        buttonVerReclamos.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenuEstudiante2_to_fragmentVerReclamos3)
        }
    }

    fun cargarReclamos(){
        callReclamos = apiService.getAllReclamos("Bearer "+(activity as MainActivity).tokenJWT)

        callReclamos?.enqueue(object : Callback<List<ReclamoCompleto>> {
            override fun onResponse(call: Call<List<ReclamoCompleto>>, response: Response<List<ReclamoCompleto>>) {
                if (response.isSuccessful) {
                    val reclamos: List<ReclamoCompleto> = response.body() ?: emptyList()

                    mainActivity.listaReclamos = reclamos.toMutableList()
                } else {
                    println("Error trayendo los reclamos ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<ReclamoCompleto>>, t: Throwable) {
                println(t.message)
            }
        })
    }

    fun facebookUtec() {
        try {
            val facebookUtec = requireActivity().findViewById<ImageView>(R.id.imageViewFacebookUtec)

            facebookUtec.setOnClickListener {
                val urlRedSocial = "https://www.facebook.com/utecuy/?locale=es_LA"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlRedSocial))
                intent.resolveActivity(requireActivity().packageManager)
                startActivity(intent)
            }

        } catch (exception: MyException) {
            val mensaje = "No se encontró una aplicación para abrir la red social"
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    fun instagramUtec() {
        try {
            val instagramUtec =
                requireActivity().findViewById<ImageView>(R.id.imageViewIntagramUtec)

            instagramUtec.setOnClickListener {

                val urlRedSocial = "https://www.instagram.com/utecuy/?hl=es"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlRedSocial))
                intent.resolveActivity(requireActivity().packageManager)
                startActivity(intent)
            }

        } catch (exception: MyException) {
            val mensaje = "No se encontró una aplicación para abrir la red social"
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }

    }

    fun xUtec() {
        try {
            val xUtec = requireActivity().findViewById<ImageView>(R.id.imageViewXUtec)

            xUtec.setOnClickListener {

                val urlRedSocial = "https://twitter.com/UTECuy"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlRedSocial))
                intent.resolveActivity(requireActivity().packageManager)
                startActivity(intent)
            }

        } catch (exception: MyException) {
            val mensaje = "No se encontró una aplicación para abrir la red social"
            Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    fun volver() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mostrarFrameCerrarSesion()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        callback.isEnabled =
            true
    }

    fun cerrarSesion() {
        val cerrarSesion = requireActivity().findViewById<ImageView>(R.id.cerrarSesionEstudiante)
        cerrarSesion.setOnClickListener {
            mostrarFrameCerrarSesion()
        }
    }

    fun mostrarFrameCerrarSesion() {
        val miniFrameCloseEstu =
            requireActivity().findViewById<ConstraintLayout>(R.id.miniFrameCloseAnali)
        miniFrameCloseEstu.visibility = View.VISIBLE

        val buttonAceptarMiniFrameEstu =
            requireActivity().findViewById<Button>(R.id.buttonAceptarMiniFrameAnali)
        val buttonCancelarMiniFrameEstu =
            requireActivity().findViewById<Button>(R.id.buttonCancelarMiniFrameAnali)

        buttonAceptarMiniFrameEstu.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMenuEstudiante2_to_fragmentLogin)
            miniFrameCloseEstu.visibility = View.GONE
        }

        buttonCancelarMiniFrameEstu.setOnClickListener {
            miniFrameCloseEstu.visibility = View.GONE
        }
    }


}