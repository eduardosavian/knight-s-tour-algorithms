package main

import (
	"math/rand"
	"sort"
)

type Board struct {
	Rows [][]int `json:"board"`
}

type Move struct {
	X, Y     int
	Priority int
}

var possibleMoves = [][]int{
	{-2, -1}, {-1, -2}, {1, -2}, {2, -1},
	{2, 1}, {1, 2}, {-1, 2}, {-2, 1},
}

func isMoveValid(x, y, boardSize int) bool {
	return x >= 0 && x < boardSize && y >= 0 && y < boardSize
}

func findNextMoves(x, y, boardSize int, board [][]int, searchType string) []Move {
	validMoves := []Move{}

	for _, move := range possibleMoves {
		nextX := x + move[0]
		nextY := y + move[1]

		if isMoveValid(nextX, nextY, boardSize) && board[nextX][nextY] == 0 {
			validMoves = append(validMoves, Move{nextX, nextY, 0})
		}
	}

	switch searchType {
	case "wd":
		for i := range validMoves {
			move := &validMoves[i]
			move.Priority = len(findNextMoves(move.X, move.Y, boardSize, board, "default"))
		}
		sort.Slice(validMoves, func(i, j int) bool {
			return validMoves[i].Priority < validMoves[j].Priority
		})
	case "hd":
		for i := range validMoves {
			move := &validMoves[i]
			move.Priority = len(findNextMoves(move.X, move.Y, boardSize, board, "default"))
		}
		sort.Slice(validMoves, func(i, j int) bool {
			return validMoves[i].Priority > validMoves[j].Priority
		})
	case "sf":
		rand.Shuffle(len(validMoves), func(i, j int) {
			validMoves[i], validMoves[j] = validMoves[j], validMoves[i]
		})
	case "dp":
		// dp
	case "ed":
		for i := range validMoves {
			move := &validMoves[i]

			distX := min(move.X, boardSize-1-move.X)
			distY := min(move.Y, boardSize-1-move.Y)

			move.Priority = distX + distY
		}

		sort.Slice(validMoves, func(i, j int) bool {
			return validMoves[i].Priority < validMoves[j].Priority
		})

	}
	return validMoves
}

func backtrackSearch(board [][]int, moveNum, x, y, boardSize int, backtrackType string) bool {
	count++

	board[x][y] = moveNum

	if moveNum == boardSize*boardSize {
		return true
	}

	nextMoves := findNextMoves(x, y, boardSize, board, backtrackType)

	for _, move := range nextMoves {
		if backtrackSearch(board, moveNum+1, move.X, move.Y, boardSize, backtrackType) {
			return true
		}
	}

	board[x][y] = 0

	return false
}
