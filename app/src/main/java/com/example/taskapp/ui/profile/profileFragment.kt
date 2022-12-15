package com.example.taskapp.ui.profile

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.example.taskapp.R
import com.example.taskapp.TaskAdapter
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.databinding.FragmentProfileBinding
import kotlinx.android.synthetic.main.fragment_profile.*
import java.lang.Exception

class profileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    var pickedPhoto: Uri? = null
    var pickedBitmap: Bitmap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_foto.setOnClickListener{
            select_image(it)
        }

    }

     fun select_image(view: View) {
         activity?.let {
             if (ContextCompat.checkSelfPermission(it.applicationContext, android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
                 requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
             } else {
                 val Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                 startActivityForResult(Intent, 2)
             }


         }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1 ) {
            if (grantResults.size> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPhoto = data.data
        }
        try {
            context?.let {
                if (pickedPhoto != null) {
                    if (Build.VERSION.SDK_INT >=28) {
                        val source = ImageDecoder.createSource(it.contentResolver, pickedPhoto!!)
                        pickedBitmap = ImageDecoder.decodeBitmap(source)
                        iv_foto.setImageBitmap(pickedBitmap)
                    } else{
                        pickedBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver, pickedPhoto)
                        iv_foto.setImageBitmap(pickedBitmap)
                    }
                }
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
