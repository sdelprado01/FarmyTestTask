package com.example.farmytesttask.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.farmytesttask.R
import io.fotoapparat.Fotoapparat
import io.fotoapparat.configuration.CameraConfiguration
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.back
import io.fotoapparat.selector.front
import io.fotoapparat.selector.off
import io.fotoapparat.selector.torch
import io.fotoapparat.view.CameraView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class LiveCameraFragment : Fragment() {

    //Fotoapparat object
    lateinit var fotoapparat: Fotoapparat

    //Fotoapparat variables
    private var fotoapparatState : FotoapparatState? = null
    private var cameraStatus : CameraState? = null
    private var flashState: FlashState? = null

    //Permissions needed
    private var permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

    lateinit var flashImageView: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_live_camera, container, false)

        //Create the camera view
        createFotoapparat(root)
        cameraStatus = CameraState.BACK
        flashState = FlashState.OFF
        fotoapparatState = FotoapparatState.OFF

        flashImageView = root.findViewById(R.id.fab_flash)

        //Buttons
        val btnTakePicture = root.findViewById<View>(R.id.fab_camera)
        val btnSwitchCamera = root.findViewById<View>(R.id.fab_switchCamera)
        val btnFlash = root.findViewById<View>(R.id.fab_flash)

        btnTakePicture.setOnClickListener { takePhoto() }
        btnSwitchCamera.setOnClickListener { switchCamera() }
        btnFlash.setOnClickListener { changeFlashState() }

        //Return button
        val btnReturn = root.findViewById<View>(R.id.fab_ReturnLiveCamera)
        btnReturn.setOnClickListener{
            NavHostFragment.findNavController(this).popBackStack()
        }

        return root
    }

    //Create camera view
    private fun createFotoapparat(view: View){
        val cameraView = view.findViewById<CameraView>(R.id.camera_view)

        fotoapparat = context?.let {
            Fotoapparat(
                    context = it,
                    view = cameraView,
                    scaleType = ScaleType.CenterCrop,
                    lensPosition = back(),
                    logger = loggers(
                            logcat()
                    ),
                    cameraErrorCallback = { error ->
                        println("Recorder errors: $error")
                    }
            )
        }!!
    }



    //Turn on/off flash
    private fun changeFlashState() {
        fotoapparat.updateConfiguration(
                CameraConfiguration(
                        flashMode = if (flashState == FlashState.TORCH) off() else torch()
                )
        )

        if(flashState == FlashState.TORCH){
            flashState = FlashState.OFF
            flashImageView.setImageResource(R.drawable.ic_flash_on_24px)
        } else {
            flashState = FlashState.TORCH
            flashImageView.setImageResource(R.drawable.ic_flash_off_24px)

        }
    }

    //Switch to frontal camera
    private fun switchCamera() {
        fotoapparat.switchTo(
                lensPosition = if (cameraStatus == CameraState.BACK) front() else back(),
                cameraConfiguration = CameraConfiguration()
        )

        if(cameraStatus == CameraState.BACK) {
            cameraStatus = CameraState.FRONT
        } else cameraStatus = CameraState.BACK
    }

    //Take photo and save it
    private fun takePhoto() {
        if (context?.let { hasNoPermissions(it) }!!) {

            val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            activity?.let { ActivityCompat.requestPermissions(it, permissions, 0) }
        }else{

            //Get default file route and save on Farmy directory
            val dir = File(context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Farmy")
            if(!dir.exists()) dir.mkdirs()
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) //To identify the pictures

            fotoapparat.autoFocus().takePicture().saveToFile(File(dir, "$timeStamp.jpg"))
            Toast.makeText(this.context, "Photo taken!", Toast.LENGTH_SHORT).show()

        }
    }

    //Are necessary permissions granted?
    private fun hasNoPermissions(context: Context): Boolean{
        return context?.let {
            ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
        } != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    //Request permissions
    private fun requestPermission(){
        activity?.let { ActivityCompat.requestPermissions(it, permissions, 0) }
    }

    override fun onStart() {
        super.onStart()
        if (context?.let { hasNoPermissions(it) }!!) {
            requestPermission()
        }else{
            fotoapparat.start()
            fotoapparatState = FotoapparatState.ON
        }
    }

    override fun onStop() {
        super.onStop()
        fotoapparat.stop()
        FotoapparatState.OFF
    }

    override fun onPause() {
        super.onPause()
        println("OnPause")
    }

enum class CameraState{
    FRONT, BACK
}

enum class FlashState{
    TORCH, OFF
}

enum class FotoapparatState{
    ON, OFF
}
}
