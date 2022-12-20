package com.example.taskapp.ui.profile
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.taskapp.Extension.loadImage
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.ui.util.Preferences


class profileFragment : Fragment() {


    private lateinit var binding: FragmentProfileBinding

    private lateinit var preferences: Preferences


    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent())

        { uri ->

            binding.ivFoto.loadImage(uri.toString())

            Preferences(requireContext()).saveImageUri(uri.toString())
        }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)

        initListener()
        return binding.root
    }


    fun initListener() {
        binding.ivFoto.setOnClickListener {
            mGetContent.launch("image/*")

        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       preferences = Preferences((requireContext()))
       binding.ivFoto.loadImage(preferences.getImageUri())
            binding.etName.setText(Preferences(requireContext()).getName())
            binding.etName.addTextChangedListener() {
                Preferences(requireContext(),).saveName(binding.etName.text.toString())

            }
            }
        }



