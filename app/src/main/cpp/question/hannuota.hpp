//
// Created by sky on 20-12-29.
//
#include "jlog.h"

void hannuota(int n,char start,char help,char end){
    if(n == 1){
        LOGE("把%d 个盘子从 %c 搬到 %c",n,start,end);
    }else{
        hannuota(n-1,start,end,help);
        LOGE("把%d 个盘子从 %c 搬到 %c",n,start,end);
        hannuota(n-1,help,start,end);
    }
}