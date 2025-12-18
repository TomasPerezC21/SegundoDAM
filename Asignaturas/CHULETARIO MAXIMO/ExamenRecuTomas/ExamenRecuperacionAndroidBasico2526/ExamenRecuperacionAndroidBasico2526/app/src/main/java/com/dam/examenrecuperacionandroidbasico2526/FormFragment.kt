package com.dam.examenrecuperacionandroidbasico2526

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment

class FormFragment : Fragment() {

    private lateinit var btnComprobar: Button

    private lateinit var miSpinner: Spinner

    private lateinit var editCorrecto: EditText

    private lateinit var miCheckBox: CheckBox

    private lateinit var miRadioGroup: RadioGroup

    private lateinit var rbSeleccionado: RadioButton

    private lateinit var boton: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //bindeo
        btnComprobar = view.findViewById(R.id.btnComprobar)
        miSpinner = view.findViewById(R.id.spinner1)
        editCorrecto = view.findViewById(R.id.editText)
        miCheckBox = view.findViewById(R.id.checkBox)
        miRadioGroup = view.findViewById(R.id.radioGroup)
        rbSeleccionado = view.findViewById(R.id.radioCorrecto)
        boton = view.findViewById(R.id.btnComprobar)

        // botón deshabilitado por defecto
        btnComprobar.isEnabled = false

        // Datos del spinner
        val opciones = listOf("Selecciona...", "correcto", "incorrecto", "otra")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, opciones)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        miSpinner.adapter = adapter

        //oyente del spinner
        miSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //val temaSeleccionado = parent.getItemAtPosition(position).toString()
                validarFormulario()

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //oyende del editText
        editCorrecto.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable) {
                validarFormulario()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
        })

        //oyente del checkBox
        miCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                validarFormulario()
            } else {
                //validarFormulario()
            }
        }

        //oyende del radioGroup
        miRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            validarFormulario()
        }

        //oyente del boton
        boton.setOnClickListener {
            Toast.makeText(requireContext(), "!!Correcto!!", Toast.LENGTH_SHORT).show()
        }
    }


    fun validarFormulario() {

        if (miSpinner.selectedItem == "correcto" && editCorrecto.text.toString() == "correcto" && miCheckBox.isChecked && rbSeleccionado.isChecked){
            btnComprobar.isEnabled = true
        }else{
            btnComprobar.isEnabled = false
        }

    }
}
