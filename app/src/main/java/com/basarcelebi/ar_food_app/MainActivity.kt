package com.basarcelebi.ar_food_app

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.ar.core.Config
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.AugmentedImageNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.material.setExternalTexture
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation




class MainActivity : AppCompatActivity() {

    lateinit var sceneView: ArSceneView
    lateinit var placeButton: ExtendedFloatingActionButton
    lateinit var modelNode: ArModelNode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sceneView = findViewById(R.id.sceneView)

        placeButton = findViewById(R.id.place)

        placeButton.setOnClickListener {
            placeModel()
        }

        modelNode = ArModelNode().apply {
            loadModelGlbAsync(
                glbFileLocation = "models/fast-food.glb",
                scaleToUnits = 0.4f,
                centerOrigin = Position(-0.5f)

            ){
                sceneView.planeRenderer.isVisible = true
            }
            onAnchorChanged = {
                placeButton.isGone
            }
        }
        sceneView.addChild(modelNode)


    }

    private fun placeModel()
    {
        modelNode?.anchor()
        sceneView.planeRenderer.isVisible = false

    }


}
