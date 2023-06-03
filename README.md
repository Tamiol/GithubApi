# GithubApi
***

This is a project that provides a RESTful API for interacting with the GitHub platform.

### Installing

#### Prerequisites

* Docker: Install Docker from the official website - https://docs.docker.com/desktop/

#### Local Setup

1. Clone the repository using Git:
    ````
    git clone https://github.com/Tamiol/GithubApi.git
    ````
2. Change into the project directory:
    ````
    cd githubapi
    ````
3. Build and run the Docker container:
   ````
   docker build -t githubapi .
   docker run -p 8080:8080 githubapi
   ````

### Endpoints

Base URL: http://localhost:8080

- `/{userName}`
  - #### GET
    - Retrieves repository information for the specified user.
    - #### Parameters:
      - `userName`: The GitHub username of the user.
    - #### Response:
      - JSON object `Repository` with the following properties:
        - `name`: Repository name
        - `login`: Owner login
        - `branches`: Array of branch names and their last commit SHA.
   - Sample request :
     - `GET http://localhost:8080/Tamiol`