package main


import (
    "testing"
	"time"
)

func TestBacktrackSearchWithAlgorithms(t *testing.T) {
    boardSize := 8
    startX, startY := 0, 0
    algorithms := []string{"wd", "dp", "hd", "sf", "ed"}

    for _, algorithm := range algorithms {
        t.Run("TestBacktrackSearch_"+algorithm, func(t *testing.T) {
            board := make([][]int, boardSize)
            for i := range board {
                board[i] = make([]int, boardSize)
            }

            start := time.Now()
            solutionFound := backtrackSearch(board, 1, startX, startY, boardSize, algorithm)
            elapsed := time.Since(start)

            options := PrintOptions{
                Time:     elapsed,
                Solution: solutionFound,
                Count:    count,
            }

            printBoard(board, options)

            if !solutionFound {
                t.Errorf("No solution found for the given board and starting position using algorithm %s", algorithm)
            }
        })
    }
}