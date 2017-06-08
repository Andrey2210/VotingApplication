INSERT INTO voting_app.t_user (f_email, f_password) VALUES ('andrey@gamil.com', '12345');

INSERT INTO voting_app.t_voting (f_question, f_state, f_user_id)  VALUES ('Как дела?', 'ACTIVE', 1);

INSERT INTO voting_app.t_answer (f_text, f_voting_id) VALUES ('Хорошо', 1);
INSERT INTO voting_app.t_answer (f_text, f_voting_id) VALUES ('Нормально', 1);
INSERT INTO voting_app.t_answer (f_text, f_voting_id) VALUES ('Плохо', 1);
