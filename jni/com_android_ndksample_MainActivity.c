/*
 * com_android_ndksample_MainActivity.c
 *
 *  Created on: Nov 27, 2013
 *      Author: davidelizondo
 */

#include "com_android_ndksample_MainActivity.h"
#include "fibonacci.c"

jlong JNICALL
Java_com_android_ndksample_MainActivity_nativeFibonacci(JNIEnv *env,
		jclass clazz, jint n) {
	return recursive(n);
}
