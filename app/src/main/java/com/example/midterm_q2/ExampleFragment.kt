package com.example.midterm_q2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ExampleFragment : Fragment() {

    private lateinit var spinnerOptions: Spinner
    private lateinit var buttonSubmit: Button
    private lateinit var textViewResult: TextView
    private var selectedOption: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_example, container, false)

        spinnerOptions = view.findViewById(R.id.spinnerOptions)
        buttonSubmit = view.findViewById(R.id.buttonSubmit)
        textViewResult = view.findViewById(R.id.textViewResult)

        // Set up the spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.options_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerOptions.adapter = adapter
        }

        spinnerOptions.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedOption = parent.getItemAtPosition(position) as String
                textViewResult.text = "Selected: $selectedOption"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedOption = null
                textViewResult.text = "Nothing selected"
            }
        }

        buttonSubmit.setOnClickListener {
            if (selectedOption != null) {
                Toast.makeText(requireContext(), "Submitted: $selectedOption", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please select an option", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
