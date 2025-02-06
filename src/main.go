package main

import (
    "context"
    "fmt"
    "os"
    "strconv"
    "time"
)

func printBoard(board [][]int) {
	fmt.Println("          Board")
    for _, row := range board {
        for _, cell := range row {
            fmt.Printf("%2d ", cell)
        }
        fmt.Println()
    }
	fmt.Println()
}

func main() {
    args := os.Args

    if len(args) < 5 {
        fmt.Println("Usage: <boardSize> <startX> <startY> <algorithm>")
        return
    }

    boardSize, err := strconv.Atoi(args[1])
    if err != nil || boardSize <= 0 {
        fmt.Println("Invalid board size. Must be a positive integer.")
        return
    }

    startX, err := strconv.Atoi(args[2])
    if err != nil || startX < 0 || startX >= boardSize {
        fmt.Println("Invalid startX. Must be within the board size.")
        return
    }

    startY, err := strconv.Atoi(args[3])
    if err != nil || startY < 0 || startY >= boardSize {
        fmt.Println("Invalid startY. Must be within the board size.")
        return
    }

    algorithm := args[4]
    if algorithm != "wd" && algorithm != "bt" && algorithm != "hd" && algorithm != "sf" {
        fmt.Println("Invalid algorithm. Must be either 'wd' (warnsdorff), 'bt' (backtrack), 'hd' (highDegree), or 'sf' (shuffle).")
        return
	}

    board := make([][]int, boardSize)
    for i := range board {
        board[i] = make([]int, boardSize)
    }

    solution := false

    time_start := time.Now()

    if algorithm == "wd" {
		printBoard(board)
        solution = greedySearch(board, startX, startY, boardSize, algorithm)
    }

    if algorithm == "bt" || algorithm == "hd" || algorithm == "sf" {
        resultChan := make(chan bool)
        ctx, cancel := context.WithTimeout(context.Background(), 15*time.Second)
        defer cancel()

        go func() {
            resultChan <- backtrackSearch(board, 1, startX, startY, boardSize, algorithm)
        }()

        select {
        case <-ctx.Done():
            solution = false
        case result := <-resultChan:
            solution = result
        }
    }

    time_end := time.Now()

    time_result := time_end.Sub(time_start)

    fmt.Println("Time: ", time_result)

    if !solution {
        fmt.Println("No Knight's tour solution found:", err)
        return
    }

    printBoard(board)
}
