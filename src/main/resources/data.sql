INSERT INTO voting_app.t_user (f_email, f_password) VALUES ('andrey@gmail.com', '$2a$11$uwn.hHQwgLtJ8qUB6AuLaOzHdDavpcibxyXg7AO8AvekGbC0tILiy');

INSERT INTO voting_app.t_voting (f_question, f_state, f_user_id)  VALUES ('Как дела?', 'ACTIVE', 1);

INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Хорошо', 50, 1);
INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Нормально', 18, 1);
INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Плохо', 5, 1);

INSERT INTO voting_app.t_voting (f_question, f_state, f_user_id)  VALUES ('Кого больше?', 'ACTIVE', 1);

INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Я Парень', 1300, 2);
INSERT INTO voting_app.t_answer (f_text, f_voters_number, f_voting_id) VALUES ('Я Девушка', 800, 2);
