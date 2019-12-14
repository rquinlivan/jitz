#!/bin/bash

sudo docker run --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=jitz -p 5432:5432 -d postgres