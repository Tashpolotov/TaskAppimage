package com.example.taskapp.ui.profile
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.taskapp.databinding.FragmentProfileBinding
class profileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent(), { uri -> binding.ivFoto.setImageURI(uri)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)

        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.ivFoto.setOnClickListener{
            mGetContent.launch("image/*")
        }

    }
}