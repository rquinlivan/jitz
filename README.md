# jitz

Jitz aims to be a professional quality software solution for running [Brazilian jiu jitsu tournaments](Brazilian jiu jitsu tournaments).


## Backend server

The backend web server and API for the `jitz` system is written in Scala with [Twitter's Finatra framework](https://twitter.github.io/finatra/).
Migrations are managed with [Flyway](https://flywaydb.org/). It uses the [Slick ORM](https://scala-slick.org/).

## Dev setup

### Start a database

`./scripts/start_db.sh`

### Run the migrations

Migrations are done with Flyway. Migration files live in `/migrations/sql`.

`sbt flywayMigrate`

### Run the tests

`sbt test`

