package com.example.farmytesttask

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class LiveCameraFragment : Fragment() {

    var fotoapparat: Fotoapparat? = null
    val filename = "test.png"
    val sd = Environment.getExternalStorageDirectory()
    val dest = File(sd, filename)

    var fotoapparatState : FotoapparatState? = null
    var cameraStatus : CameraState? = null
    var flashState: FlashState? = null

    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_live_camera, container, false)

        createFotoapparat(root)

        cameraStatus = CameraState.BACK
        flashState = FlashState.OFF
        fotoapparatState = FotoapparatState.OFF

        val btnTakePicture = root.findViewById<View>(R.id.fab_camera)
        val btnSwitchCamera = root.findViewById<View>(R.id.fab_switchCamera)
        val btnFlash = root.findViewById<View>(R.id.fab_flash)

        btnTakePicture.setOnClickListener { context?.let { it1 -> takePhoto(it1) } }
        btnSwitchCamera.setOnClickListener { switchCamera() }
        btnFlash.setOnClickListener { changeFlashState() }

        return root
    }

    private fun createFotoapparat(view:View){
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
        }
    }

    override fun onStart() {
        super.onStart()
        if (context?.let { hasNoPermissions(it) }!!) {
            requestPermission()
        }else{
            fotoapparat?.start()
            fotoapparatState = FotoapparatState.ON
        }
    }

    private fun changeFlashState() {
        fotoapparat?.updateConfiguration(
            CameraConfiguration(
                flashMode = if(flashState == FlashState.TORCH) off() else torch()
            )
        )

        if(flashState == FlashState.TORCH){
            flashState = FlashState.OFF
        } else flashState = FlashState.TORCH
    }

    private fun switchCamera() {
        fotoapparat?.switchTo(
            lensPosition =  if (cameraStatus == CameraState.BACK) front() else back(),
            cameraConfiguration = CameraConfiguration()
        )

        if(cameraStatus == CameraState.BACK) {
            cameraStatus = CameraState.FRONT
        } else cameraStatus = CameraState.BACK
    }

    private fun takePhoto(context: Context) {
        if (hasNoPermissions(context)) {

            val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            activity?.let { ActivityCompat.requestPermissions(it, permissions,0) }
        }else{
            println("Has all permissions!")
            fotoapparat
                ?.takePicture()
                ?.saveToFile(dest)
        }
    }


    private fun hasNoPermissions(context:Context): Boolean{
        return context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE)
        } != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(){
        activity?.let { ActivityCompat.requestPermissions(it, permissions,0) }
    }

    override fun onStop() {
        super.onStop()
        fotoapparat?.stop()
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