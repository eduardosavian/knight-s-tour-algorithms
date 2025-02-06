# Knight's tour algorithms

Madde by Eduardo Savian, Mateus Winter

## Description

Implement two algorithms for the Knight's Walk:

- One algorithm should be trial and error (it should be possible to position the knight in any position on the board);

- Another should be a strategy that minimizes computational performance. Do not use ready-made algorithms such as Warnsdorff. The suggested strategy is to choose the smallest possible move.

## Run

### Linux

#### Go build

```bash
go build -o libs/knight_tour src/main.go src/methods.go
```

#### Run executable

```bash
./knights_tour
```

## Bibliographic references

[An efficient algorithm for the Knight’s tour problem - Ian Parberry](https://core.ac.uk/download/pdf/81964499.pdf)

[Knight's tour - Wikipedia](https://en.wikipedia.org/wiki/Knight's_tour)
