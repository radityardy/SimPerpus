# SimPerpus

SimPerpus is a library management system built with Spring Boot. It provides functionalities to manage books, members, and borrowing records.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage books: Add, update, delete, and view books.
- Manage members: Add, update, delete, and view members.
- Manage borrowings: Record borrowing and returning of books.
- View borrowing history and statistics.

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/simperpus.git
    cd simperpus
    ```

2. Build the project using Maven:
    ```sh
    ./mvnw clean install
    ```

3. Run the application:
    ```sh
    ./mvnw spring-boot:run
    ```

## Usage

Once the application is running, you can access the API at `http://localhost:8080`.

## API Endpoints

### Books

- **GET /api/books**: Get all books
- **GET /api/books/{id}**: Get a book by ID
- **POST /api/books**: Create a new book
- **PUT /api/books/{id}**: Update a book by ID
- **DELETE /api/books/{id}**: Delete a book by ID

### Members

- **GET /api/members**: Get all members
- **GET /api/members/{id}**: Get a member by ID
- **POST /api/members**: Create a new member
- **PUT /api/members/{id}**: Update a member by ID
- **DELETE /api/members/{id}**: Delete a member by ID

### Borrowings

- **GET /api/borrowings**: Get all borrowings
- **GET /api/borrowings/{id}**: Get a borrowing by ID
- **GET /api/borrowings/months/{month}**: Get borrowings by month
- **POST /api/borrowings**: Create a new borrowing
- **PUT /api/borrowings/{id}**: Update a borrowing by ID
- **DELETE /api/borrowings/{id}**: Delete a borrowing by ID

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.