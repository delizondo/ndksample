LOCAL_PATH := $(call my-dir)
  
include $(CLEAR_VARS)
  
LOCAL_MODULE    := fibonacci
  
LOCAL_SRC_FILES := com_android_ndksample_MainActivity.c
  
include $(BUILD_SHARED_LIBRARY)  