#include <jni.h>

#include "Calculator.h"
using namespace std;

JNIEXPORT jdouble JNICALL Java_Calculator_add(JNIEnv *, jobject, jdouble n1, jdouble n2){
	return n1+n2;
}

JNIEXPORT jdouble JNICALL Java_Calculator_sub(JNIEnv *, jobject, jdouble n1, jdouble n2){
	return n1-n2;
}

JNIEXPORT jdouble JNICALL Java_Calculator_mul(JNIEnv *, jobject, jdouble n1, jdouble n2){
	return n1*n2;
}

JNIEXPORT jdouble JNICALL Java_Calculator_div(JNIEnv *, jobject, jdouble n1, jdouble n2){
	if(n2==0){
		return 0;
	}
	return n1/n2;
}