package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)


var count = 0

func main() {
    router := gin.Default()

    // boardSize, startX, startY, algorithm := handleMenu()

    // board := make([][]int, boardSize)
    // for i := range board {
    //     board[i] = make([]int, boardSize)
    // }

    // time_start  := time.Now()
    // solution    := backtrackSearch(board, 1, startX, startY, boardSize, algorithm)
    // time_result := time.Since(time_start)

    // options     := PrintOptions{
    //     Time:    time_result ,
    //     Solution: solution,
    //     Count: count,
    // }

    // printBoard(board, options)

    router := gin.Default()
    router.GET("/knights_tour", getKnightsTour)
    router.Run("localhost:8585")
}

func getKnightsTour(c * gin.Context) {
    c.IndentedJSON(http.StatusOK, testss)
}