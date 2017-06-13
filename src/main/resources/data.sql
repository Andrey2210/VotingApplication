INSERT INTO voting_app.t_user (f_email, f_password) VALUES ('andrey@gmail.com', '$2a$11$uwn.hHQwgLtJ8qUB6AuLaOzHdDavpcibxyXg7AO8AvekGbC0tILiy');

INSERT INTO voting_app.t_voting (f_question, f_state, f_user_id)  VALUES ('How are you?', 'ACTIVE', 1);

INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Fine', 50, 1);
INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Good', 18, 1);
INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Not bad', 5, 1);

INSERT INTO voting_app.t_voting (f_question, f_state, f_user_id)  VALUES ('Who is more?', 'ACTIVE', 1);

INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('I am man', 1300, 2);
INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('I am woman', 800, 2);
