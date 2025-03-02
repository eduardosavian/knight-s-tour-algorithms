# Knight's Tour Algorithms

Made by Eduardo Savian, Mateus Winter

## Description

Implement two algorithms for the Knight's Walk:

- One algorithm should be trial and error (it should be possible to position the knight in any position on the board);

- Another should be a strategy that minimizes computational performance. Do not use ready-made algorithms such as Warnsdorff. The suggested strategy is to choose the smallest possible move;

- Count the execution time;

- Count the number of steps needed;

- Tell whether the solution is closed or open.

## How to Run

### Prerequisites

- Ensure you have [Go](https://golang.org/dl/) installed.

### Build

1. Open your terminal.

2. Navigate to the project directory where the source files are located.

3. Run the following command to build the executable:

#### Linux

```bash
go build -o knights_tour src/main.go src/methods.go src/utils.go
```

#### Windows

```ps1
go build -o knights_tour.exe src/main.go src/methods.go src/utils.go
```

### Test

```bash
cd src/ && go test && cd ..
```

### Execute

1. Once the build is complete, you can run the executable.

2. Use the following command to run the program:

#### Linux

```ps1
./knights_tour <boardSize> <startX> <startY> <algorithm>
```

#### Windows

```bash
./knights_tour <boardSize> <startX> <startY> <algorithm>
```

#### Example Command

- **boardSize**: Size of the chessboard (e.g., 8 for an 8x8 board, max 30).
- **startX**: Starting X-coordinate of the knight.
- **startY**: Starting Y-coordinate of the knight.
- **algorithm**: The algorithm to use for the Knight’s tour. Options are:

- `wd` (Warnsdorff)
- `hd` (High Degree)
- `sf` (Shuffle)
- `dp` (depth-first)
- `ed` (Edge)

```bash
./knights_tour 8 0 0 wd
```

This command runs the program with an 8x8 board, starting at position (0,0), using the Warnsdorff algorithm.

## Bibliographic References

- [An efficient algorithm for the Knight’s tour problem - Ian Parberry](https://core.ac.uk/download/pdf/81964499.pdf)

- [Knight's tour - Wikipedia](https://en.wikipedia.org/wiki/Knight's_tour)
