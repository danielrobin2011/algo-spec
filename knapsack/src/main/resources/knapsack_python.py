# -*- coding: utf-8 -*-
"""
Created on Sun Aug 22 19:17:06 2021

@author: Daniel Robin K
"""
import pandas as pd
# values array containing the "profits" of each item
# weights array containing the "weight" of each item
# memo_pad is a list used to memoize recursive results

info = pd.read_csv("knapsack_big.txt", sep=" ", header=0, skiprows=[1], names=["value", "weight"])

values[], weights[], memo_pad[] = list(info["value"]), list(info["weight"]), memo_pad[]

knapsack_memoized(i, w):
    // i is the current item
    // w is the remaining weight allowed in the knapsack

    if memo_pad[i][w] < 0: // if value not memoized
        if w < weights[i]:
            memo_pad[i][w] = knapsack_memoized(i-1, w)
        else:
            memo_pad[i][w] = max{values[i] + memo_pad[i-1][w-weights[i]], memo_pad[i-1][w]}

    return memo_pad[i][w]
end