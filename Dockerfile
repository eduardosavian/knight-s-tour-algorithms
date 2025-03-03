FROM golang:1.22

WORKDIR /app

COPY go.mod ./

RUN go mod download

COPY /src/*.go ./

RUN go build -o /knights_tour

CMD ["/knights_tour"]
