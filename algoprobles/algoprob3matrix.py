import math
import os
import random
import re
import sys


def diagonalDifference(arr):
    diagonals= len(arr)
    sum=0
    for i in range(diagonals):
        print(i)
        sum = sum+ arr[i][i]-arr[i][diagonals-1-i]
        print("sum "+ str(sum))

    if(sum<0):
      sum = -sum
        
    return sum


arr=[[1,2,3],[4,5,6],[7,8,9]]
arr2=[[11,2,4],
[4,5,6],
[10,8,-12]]

arr3=[[-10,3,0,5,-4],
[2,-1,0,2,-8],
[9,-2,-5,6,0],
[9,-7,4,8,-2],
[3,7,8,-5,0]]

print(diagonalDifference(arr3))