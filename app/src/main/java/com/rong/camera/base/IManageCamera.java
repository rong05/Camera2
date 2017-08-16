package com.rong.camera.base;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.media.Image;
import android.support.annotation.NonNull;
import android.util.Size;

import java.io.File;

/**
 * Created by Administrator on 2017/8/15.
 */

public interface IManageCamera {
    void openCamera(@NonNull String cameraId);
    void setPreviewTexture(@NonNull SurfaceTexture texture);
    void setDesktop(@NonNull Desktop desktop);
    void setPreviewSize(@NonNull Size mPreviewSize);
    void setOnPreviewFrameCallback(@NonNull PreviewFrameCallback callback);
    boolean closeCamera();
    void startCameraThread();
    void stopCameraThread();
    void takePicture();
    /**
     * Sets up member variables related to camera.
     *
     * @param width  The width of available size for camera preview
     * @param height The height of available size for camera preview
     */
    void setUpCameraOutputs(int width, int height,String cameraId);
    /**
     * The {@link android.util.Size} of camera preview.
     */
    Size getPreviewSize();
    /**
     * Configures the necessary {@link android.graphics.Matrix} transformation to `mTextureView`.
     * This method should be called after the camera preview size is determined in
     * setUpCameraOutputs and also the size of `mTextureView` is fixed.
     *
     * @param viewWidth  The width of `mTextureView`
     * @param viewHeight The height of `mTextureView`
     */
    Matrix configureTransform(int viewWidth, int viewHeight);

    /**
     *  Whether to save the default save path
     * @param isSave
     */
    void isSaveDefaultPath(boolean isSave);

    class Desktop{
        float resolution;//宽高比
        int minPreviewWidth;
        int minPictureWidth;
        int width;
        int height;
    }

    interface PreviewFrameCallback{
        void onLackPermission();
        void onPreviewFrameCallback(@NonNull byte[] buffers,int width,int height);
        void onPreviewFrameCallback(@NonNull File imgFile, int width, int height);
        void onPreviewFrameCallback(@NonNull Image image, int width, int height);
        void onError(@NonNull CameraDevice cameraDevice,int error);
        void onDisconnected(@NonNull CameraDevice cameraDevice);
        void onOpened(@NonNull CameraDevice cameraDevice);
    }


}
