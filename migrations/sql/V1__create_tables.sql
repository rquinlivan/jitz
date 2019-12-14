create table competitor
(
  id         serial  not null
    constraint competitor_pk
      primary key,
  first_name text not null,
  last_name  text not null
);

alter table competitor
  owner to postgres;

create unique index competitor_id_uindex
  on competitor (id);


---

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table tournament
(
  id   serial not null
    constraint tournament_pk
      primary key,
  uuid uuid default uuid_generate_v4(),
  name text   not null
);

alter table tournament
  owner to postgres;

create unique index tournament_id_uindex
  on tournament (id);

create index tournament_uuid_index
  on tournament (uuid);

---

create table match
(
  id            serial  not null
    constraint match_pk_2
      primary key
    constraint match_pk
      unique,
  comp_a        integer not null
    constraint match_comp_a_fkey
      references competitor,
  comp_b        integer not null
    constraint match_comp_b_fkey
      references competitor,
  tournament_id integer
    constraint "match__tournament(id)_fk"
      references tournament(id)
);

alter table match
  owner to postgres;

create unique index match_id_uindex
  on match (id);


---

create table match_score
(
  match_id integer not null
    constraint match_score_match_id_fkey
      references match(id)
);

alter table match_score
  owner to postgres;

create index match_score_match_id_index
  on match_score (match_id);

