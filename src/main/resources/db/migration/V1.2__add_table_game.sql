CREATE
    TABLE
        game(
            id BIGINT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
            name VARCHAR(255) NOT NULL
        );