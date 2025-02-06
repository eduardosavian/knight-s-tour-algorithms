package main

import (
	"context"
	"fmt"
	"os"
	"strconv"
	"time"
)

func main() {
	args := os.Args

	if len(args) < 5 {
		fmt.Println("Usage: <startX> <startY> <boardSize> <algorithm>")
		return
	}

	startX, err := strconv.Atoi(args[1])
	if err != nil {
		fmt.Println("Invalid startX:", err)
		return
	}

	startY, err := strconv.Atoi(args[2])
	if err != nil {
		fmt.Println("Invalid startY:", err)
		return
	}

	boardSize, err := strconv.Atoi(args[3])
	if err != nil || boardSize <= 0 {
		fmt.Println("Invalid board size. Must be a positive integer.")
		return
	}

	algorithm := args[4]
	if algorithm != "wd" && algorithm != "bd" && algorithm != "hd" && algorithm != "sf" {
		fmt.Println("Invalid algorithm. Must be either 'wd'(warnsdorff), 'bd'(backtrack), 'hd'(highDegree), or 'sf'(shuffle).")
		return
	}

    board := make([][]int, boardSize)
	for i := range board {
		board[i] = make([]int, boardSize)
	}

	solution := false

	time_start := time.Now()

	if (algorithm == "wd") {
		solution = greedySearch(board, startX, startY, boardSize, algorithm)
	}

	if(algorithm == "bt" || algorithm == "hd" || algorithm == "sf") {
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

	fmt.Println(time_result)

	if(!solution) {
		fmt.Println("No Knight's tour solution found:", err)
        return
	}

    boardJson, err := convertBoardToJSON(board)
    if err != nil {
        fmt.Println("Error converting board to JSON:", err)
        return
    }

    fmt.Println(string(boardJson))
}