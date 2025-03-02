package main

import (
    "fmt"
    "os"
    "strconv"
    "time"
)

type PrintOptions struct {
    Time     time.Duration
    Solution bool
    Count int64
}

func handleMenu() (int, int, int, string) {
    args := os.Args

    if len(args) < 5 {
        fmt.Println("Usage: <boardSize> <startX> <startY> <algorithm>")
        os.Exit(1)
    }

    boardSize, err := strconv.Atoi(args[1])
    if err != nil || boardSize < 5 || boardSize > 30 {
        fmt.Printf("Invalid board size: %s. Must be a positive integer between 5 and 30.\n", args[1])
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
    if algorithm != "wd" && algorithm != "dp" && algorithm != "hd" && algorithm != "sf" && algorithm != "ed" {
        fmt.Println("Invalid algorithm. Must be either 'wd' (warnsdorff), 'dp' (depth-first), 'hd' (highDegree), 'sf' (shuffle) or 'ed' (edge).")
        os.Exit(1)
    }

    return boardSize, startX, startY, algorithm
}

func printBoard(board [][]int, opts ...PrintOptions) {
    fmt.Println("Board:")

    for _, row := range board {
        for _, cell := range row {
            fmt.Printf("%03d ", cell)
        }
        fmt.Println()
    }
    fmt.Println()

    if len(opts) > 0 {
        opt := opts[0]
        fmt.Println(opt.Time)
        if opt.Time > 0 {
            fmt.Printf("Time: %v (%.9f segundos | %d nanosegundos)\n", opt.Time, opt.Time.Seconds(), opt.Time.Nanoseconds())
            }

        fmt.Println("Count: ", opt.Count)

        if opt.Solution {
            fmt.Println("Solution found!")
        } else {
            fmt.Println("No solution found.")
        }
	}
}