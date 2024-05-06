DROP TABLE IF EXISTS user_car;
CREATE TABLE user_car
(
    user_id BIGINT REFERENCES _user (id),
    car_id  BIGINT REFERENCES car (id),
    PRIMARY KEY (user_id, car_id)
)