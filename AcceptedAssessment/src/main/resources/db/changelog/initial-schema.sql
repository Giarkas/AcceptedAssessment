
-- changeset iarkadianos:1
-- Comment: Initial schema creation - Match table
-- preconditions onFail=MARK_RAN onError=HALT
-- precondition not
-- precondition tableExists tableName:Match
CREATE TABLE match (
                       id BIGSERIAL PRIMARY KEY,
                       description VARCHAR(255),
                       match_date DATE,
                       match_time TIME,
                       team_a VARCHAR(255),
                       team_b VARCHAR(255),
                       sport INTEGER NOT NULL -- <-- Κρίσιμο: Πρέπει να είναι INTEGER
);

---
-- changeset iarkadianos:2
-- Comment: Initial schema creation - MatchOdds table and foreign key
-- preconditions onFail=MARK_RAN onError=HALT
-- precondition not
-- precondition tableExists tableName:MatchOdds
CREATE TABLE MatchOdds (
                           id SERIAL PRIMARY KEY,
                           match_id INTEGER NOT NULL,
                           specifier VARCHAR(255),
                           odd NUMERIC(10, 2),
                           CONSTRAINT fk_match
                               FOREIGN KEY (match_id)
                                   REFERENCES Match (id)
                                   ON DELETE CASCADE
);