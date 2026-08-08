package com.example.mymiata

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.sceneview.SceneView
import io.github.sceneview.SurfaceType
import io.github.sceneview.rememberEnvironment
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelInstance
import io.github.sceneview.rememberMainLightNode
import io.github.sceneview.rememberEnvironmentLoader
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberCameraManipulator

@Composable
fun CarModelView(modifier: Modifier = Modifier) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val environmentLoader = rememberEnvironmentLoader(engine)

    val environment = rememberEnvironment(environmentLoader)

    val cameraNode = rememberCameraNode(engine).apply {
        position = Position(x = 0.0f, y = 0.5f, z = 4.0f) 
        lookAt(Position(x = 0.0f, y = 0.0f, z = 0.0f))
    }

    SceneView(
        modifier = modifier.fillMaxSize().background(Color.Red),
        surfaceType = SurfaceType.TextureSurface,
        engine = engine,
        modelLoader = modelLoader,
        environment = environment,
        cameraNode = cameraNode,
        cameraManipulator = rememberCameraManipulator(),
        mainLightNode = rememberMainLightNode(engine) {
            intensity = 150_000.0f
        }
    ) {
        val modelInstance = rememberModelInstance(modelLoader, "models/mazda_miata_mx-5.glb")
        
        if (modelInstance != null) {
            ModelNode(
                modelInstance = modelInstance,
                scaleToUnits = 1.0f,
                // Centering the model origin to its geometric center
                centerOrigin = null, //Position(x = 0.0f, y = 0.0f, z = 0.0f),
                // Now that it's centered, we can use simpler positioning
                position = Position(x = 0.0f, y = 0.0f, z = 0.0f),
                rotation = Rotation(y = 180f)
            )
        } else {
            Log.e("CarModelView", "ModelInstance is null")
        }
    }
}
