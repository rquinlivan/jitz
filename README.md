# jitz

## Backend server

## Dev setup

### Start a database

`./scripts/start_db.sh`

### Run the migrations

Migrations are done with Flyway. Migration files live in `/migrations/sql`.

`sbt flywayMigrate`
