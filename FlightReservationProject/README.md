# FlightReservation

1. Insert flightInformation in the database

INSERT INTO flight_information (`flight_code`, `number_of_seat`, `price`) VALUES ('Y1', '3', '1000');
INSERT INTO flight_information (`flight_code`, `number_of_seat`, `price`) VALUES ('Y2', '5', '3000');
INSERT INTO flight_information (`flight_code`, `number_of_seat`, `price`) VALUES ('Y3', '10', '4000');
INSERT INTO flight_information (`flight_code`, `number_of_seat`, `price`) VALUES ('Y4', '50', '2000');
INSERT INTO flight_information (`flight_code`, `number_of_seat`, `price`) VALUES ('Y5', '100', '5000');

2. Insert role in the table

INSERT INTO `role` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `role` (`id`, `name`) VALUES ('2', 'ROLE_ADMIN');

3. Insert admin with bcrypted password
username : admin
password : admin

INSERT INTO `user` (`id`, `password`, `user_name`) VALUES ('1', '$2a$10$3DHbGHZztOv13ggYDTOsMOURWN76QyrJWE4ICleO/O2UN0/pozVVW', 'admin');

4. Assign role to the admin

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES ('1', '2');

5. New user can be registered from the site

6. read application.properties file and replace the setting as indicated inside (....)
