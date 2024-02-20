package com.ced.plugins.mycustomplugin;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.R;


import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.ced.plugins.mycustomplugin.MyCustomPluginPlugin.*;
import  com.getcapacitor.*;






@CapacitorPlugin(name = "MyCustomPlugin")
public class MyCustomPluginPlugin extends Plugin {

    private MyCustomPlugin implementation = new MyCustomPlugin();
    //private static final int CAMERA_REQUEST_CODE = 101;
    private static final int CAMERA_REQUEST = 1888;


    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    @PluginMethod
    public void testPluginMethod (PluginCall call) {
        String value= call.getString("msg");
        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }



    /*  @PluginMethod
    public void takePhoto(PluginCall call) {
        Camera.getPhoto(call, options -> {
            JSObject result = new JSObject();
            result.put("success", true);
            result.put("path", options.getString("path"));
            call.resolve(result);
        }, error -> {
            call.reject("Failed to take photo", error);
        }, "uri"); // Utilisation du type "uri" pour spécifier le résultat comme une URI
    }*/

    

    

    /*@PluginMethod
    public void takePhoto(PluginCall call) {
        // Vérifiez que l'activité actuelle est une activité Android
        if (getContext() instanceof Activity) {
            // Créez une intention pour capturer une photo
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Vérifiez si une application de caméra est disponible pour gérer cette intention
            if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                // Lancez l'activité pour capturer la photo
                startActivityForResult(call, takePictureIntent, "onActivityResultCallback");
            } else {
                // Gérez le cas où aucune application de caméra n'est disponible
                call.reject("No camera app available");
            }
        } else {
            // Gérez le cas où l'activité actuelle n'est pas une activité Android
            call.reject("No valid activity found");
        }
    }*/

    // Méthode pour gérer le résultat de l'activité de la caméra
    /*protected void onActivityResultCallback(PluginCall call, int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // La photo a été capturée avec succès, vous pouvez gérer le résultat ici
                JSObject result = new JSObject();
                result.put("success", true);
                result.put("message", "Photo captured successfully");
                call.resolve(result);
            } else {
                // Gérez le cas où la capture de la photo a échoué
                call.reject("Photo capture failed");
            }
        }
    }*/

    /*public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("openCamera")) {
            openCamera(callbackContext);
            return true;
        }
        return false;
    }
 
    private void openCamera(CallbackContext callbackContext) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cordova.getActivity().startActivityForResult(cameraIntent, CAMERA_REQUEST);
        callbackContext.success("Camera opened successfully");
    }*/


    @PluginMethod
    public void openCamera(PluginCall call) {
        saveCall(call);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(call, cameraIntent, "cameraResult");
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST) {
            PluginCall call = null;
            if (resultCode == getActivity().RESULT_OK && data != null) {
                // Photo captured successfully
                JSObject result = new JSObject();
                result.put("success", true);
                result.put("imageUri", data.getData().toString());
                call.resolve(result);
            } else {
                // Failed to capture photo
                call.reject("Failed to capture photo");
            }
        }
    }





    

    //@PluginMethod
    //public void captureImage(PluginCall call) {
        //String imagePath = // Your image capture logic here
        //JSObject result = new JSObject();
      //  result.put("imagePath", imagePath);
       // call.resolve(result);

       // @PluginMethod
        //public void captureImage(PluginCall call) {
            // Implement image capture logic using Android Camera API
            // For simplicity, returning a mock image path
           // String imagePath = "/path/to/captured/image.jpg";
    
            //JSObject result = new JSObject();
            //result.put("imagePath", imagePath);
            //call.resolve(result);
        //}
        
    //}


    /*@PluginMethod()
        public void captureImage(PluginCall call) {
        call.resolve();
    }

    @PluginMethod()
    public void detectEdges(PluginCall call) {
        call.resolve();
    }

    @PluginMethod()
    public void convertToPDF(PluginCall call) {
        call.resolve();
    }

    @PluginMethod()
    public void uploadDocument(PluginCall call) {
        call.resolve();
    }*/


}
