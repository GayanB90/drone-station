INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES ('LW2020AS5578', 0, 0, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('MW2019BC7790', 1, 1, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('CW2019DC8840', 2, 2, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('HW2022RC9920', 3, 2, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('LW2021AS5548', 0, 0, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('MW2016BC7730', 1, 2, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('CW2022DC8850', 2, 0, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('HW2021RC9910', 3, 0, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('LW2017AS5558', 0, 2, NULL, 100);
INSERT INTO drone (serial_no, model, state, current_payload_id, battery_level) VALUES   ('MW2020BC7760', 1, 0, NULL, 100);


INSERT INTO CURRENT_PAYLOAD (PAYLOAD_ID, received_time) VALUES ( 'PLD20211015' ,current_timestamp);
insert into medication (code, name, weight, image, payload_id) values ('MED66671', 'amoxicilin', 0.25, null, 'PLD20211015');
insert into drone_payload (drone, payload_id) values ('HW2022RC9920', 'PLD20211015');


INSERT INTO CURRENT_PAYLOAD (PAYLOAD_ID, received_time) VALUES ( 'PLD20221215' ,current_timestamp);
insert into medication (code, name, weight, image, payload_id) values ('MED88871', 'paracetamol', 0.25, null, 'PLD20221215');
insert into drone_payload (drone, payload_id) values ('MW2016BC7730', 'PLD20221215');


INSERT INTO CURRENT_PAYLOAD (PAYLOAD_ID, received_time) VALUES ( 'PLD20220118' ,current_timestamp);
insert into medication (code, name, weight, image, payload_id) values ('MED55571', 'piriton', 0.25, null, 'PLD20220118');
insert into drone_payload (drone, payload_id) values ('LW2017AS5558', 'PLD20220118');