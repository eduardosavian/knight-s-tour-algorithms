package main

import (
    "time"
)

var count int64;

func main() {
    boardSize, startX, startY, algorithm := handleMenu()

    board := make([][]int, boardSize)
    for i := range board {
        board[i] = make([]int, boardSize)
    }

    time_start  := time.Now()
    solution    := backtrackSearch(board, 1, startX, startY, boardSize, algorithm)
    time_result := time.Since(time_start)

    options     := PrintOptions{
        Time:    time_result ,
        Solution: solution,
        Count: count,
    }

    printBoard(board, options)
}
