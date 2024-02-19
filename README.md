# Gaming Catalog

This service provides basic CRUD operations to manage master data for games, developers and publishers.

## Requirements

1. [Docker](https://www.docker.com/) or [Podman](https://podman.io/) to run the application in a containerized environment
2. [HashiCorp Vault](https://hub.docker.com/r/hashicorp/vault) to manage environment-specific variables
3. [PostgreSQL](https://www.postgresql.org/) for persisting data

## Building the container image

1. Create the Dockerfile with `gradle dockerfileNative`
2. Create the image with `gradle dockerBuildNative`
    - ℹ️ can't use Micronaut's automatic docker build task for Podman because of a [bug](https://github.com/containers/buildah/issues/4325)
    - 

## Run the application

See [compose file](./docker/docker-compose.yaml) for example config.
1. Using Docker `docker-compose up -d gaming-catalog`
2. Using Podman `podman compose up -d gaming-catalog`

## Use the server operations

You may [generate](https://openapi-generator.tech/) a REST-Client using [this specification file](openapi.yaml) to get access to the service operations.
