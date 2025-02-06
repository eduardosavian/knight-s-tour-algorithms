package main

import (
    "time"
)

func main() {
    boardSize, startX, startY, algorithm := handleMenu()

    board := make([][]int, boardSize)
    for i := range board {
        board[i] = make([]int, boardSize)
    }

    time_start  := time.Now()
    solution    := backtrackSearch(board, 1, startX, startY, boardSize, algorithm)
    time_end    := time.Now()
    time_result := time_end.Sub(time_start)

    options     := PrintOptions{
        Time:    time_result ,
        Solution: solution,
    }

    printBoard(board, options)
}
