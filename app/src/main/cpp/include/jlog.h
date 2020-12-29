//
// Created by sky on 20-12-29.
//

#ifndef ALGORITHMNDK_JLOG_H
#define ALGORITHMNDK_JLOG_H

#include <android/log.h>

#define  LOG_TAG  "native-log"
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__);

#endif //ALGORITHMNDK_JLOG_H
