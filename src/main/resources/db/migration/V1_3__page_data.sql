INSERT INTO REDEEM_CODE("CODE", "FROM", "TO") VALUES
('0907d8dd-3636-4113-8817-cf27a945a3bd', 'location1', 'location2'),
('161e8cc1-f4b0-430c-b2bf-0ed06a76a243', 'location2', 'location3'),
('ba4fa6eb-f4b6-4939-940d-dbc7c1d57a67', 'location3', 'location4'),
('4cdbe530-3eeb-4201-8d0b-623fcf25e67e', 'location4', 'antgame'),
('6922b95e-0e50-4951-aeb2-e00f862eec70', 'antgame', 'end');

INSERT INTO PAGE("ID", "UNLOCKED", "UNLOCKED_ON", "ORDER") VALUES
('minesweeper', FALSE, NULL, 1),
('location1', FALSE, NULL, 2),
('location2', FALSE, NULL, 3),
('location3', FALSE, NULL, 4),
('location4', FALSE, NULL, 5),
('antgame', FALSE, NULL, 6),
('end', FALSE, NULL, 7);

