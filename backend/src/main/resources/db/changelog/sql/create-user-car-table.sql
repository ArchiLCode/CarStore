DROP TABLE IF EXISTS user_pizza;
CREATE TABLE user_pizza
(
    user_id BIGINT REFERENCES _user (id),
    car_id  BIGINT REFERENCES car (id),
    PRIMARY KEY (user_id, car_id)
)