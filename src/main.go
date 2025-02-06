package main

import (
    "fmt"
    "os"
    "strconv"
    "time"
)

func printBoard(board [][]int, print_time ...time.Duration) {
    fmt.Println("Board:")

    for _, row := range board {
        for _, cell := range row {
            fmt.Printf("%02d ", cell)
        }
        fmt.Println()
    }
    fmt.Println()

    if len(print_time) > 0 && print_time[0] > 0 {
        fmt.Println("Time: ", print_time[0])
    }
}

func handleMenu() (int, int, int, string) {
    args := os.Args

    if len(args) < 5 {
        fmt.Println("Usage: <boardSize> <startX> <startY> <algorithm>")
        os.Exit(1)
    }

    boardSize, err := strconv.Atoi(args[1])
    if err != nil || boardSize <= 0 {
        fmt.Println("Invalid board size. Must be a positive integer.")
        os.Exit(1)
    }

    startX, err := strconv.Atoi(args[2])
    if err != nil || startX < 0 || startX >= boardSize {
        fmt.Println("Invalid startX. Must be within the board size.")
        os.Exit(1)
    }

    startY, err := strconv.Atoi(args[3])
    if err != nil || startY < 0 || startY >= boardSize {
        fmt.Println("Invalid startY. Must be within the board size.")
        os.Exit(1)
    }

    algorithm := args[4]
    if algorithm != "wd" && algorithm != "bt" && algorithm != "hd" && algorithm != "sf" {
        fmt.Println("Invalid algorithm. Must be either 'wd' (warnsdorff), 'bt' (backtrack), 'hd' (highDegree), or 'sf' (shuffle).")
        os.Exit(1)
    }

    return boardSize, startX, startY, algorithm
}

func main() {
    boardSize, startX, startY, algorithm := handleMenu()

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
        solution = backtrackSearch(board, 1, startX, startY, boardSize, algorithm)
    }

    time_end := time.Now()

    time_result := time_end.Sub(time_start)

    if !solution {
        fmt.Println("No Knight's tour solution found.")
        return
    }

    printBoard(board, time_result)
}
