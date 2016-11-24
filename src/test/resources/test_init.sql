INSERT INTO `make_test_test_db`.`session` (`session_id`, `session_expire`, `session_token`) VALUES ('1', '2016-12-20 15:54:14', '3f5126ef-af3a-4600-b546-f30f59bd8b3d');
INSERT INTO `make_test_test_db`.`session` (`session_id`, `session_expire`, `session_token`) VALUES ('2', '2016-12-25 05:54:14', '3f5126ef-afff-5500-b546-f30f59bd8b3d');
-- USERS
INSERT INTO `make_test_test_db`.`user` (`user_id`, `activated`, `email`, `first_name`, `last_name`, `password`, `profile_photo_relative_path`, `token`, `token_expire_date`, `user_session`) VALUES ('1', TRUE, 'test@gmail.com', 'John', 'Testing', '123123', 'img/defaultUserPhoto.png', '1a34070b-972c-4156-8f0b-d73598e484f9', '2016-11-05 11:06:03', '1');
INSERT INTO `make_test_test_db`.`user` (`user_id`, `activated`, `email`, `first_name`, `last_name`, `password`, `profile_photo_relative_path`, `token`, `token_expire_date`, `user_session`) VALUES ('2', TRUE, 'sara@gmail.com', 'Sara', 'Sarti', '99999', 'img/defaultUserPhoto.png', '1a34070b-972c-4156-8804-d73598e484f9', '2016-11-15 11:06:03', '2');
-- TESTS
INSERT INTO `make_test_test_db`.`test` (`test_id`, `category`, `creating_date`, `description`, `test_name`, `user`) VALUES ('1', 'Biology', '2016-11-09 11:10:15', 'Biology test', 'Animals', '1');
-- QUESTIONS
INSERT INTO `make_test_test_db`.`question` (`question_id`, `correct_answer`, `question_text`, `test_questions`) VALUES ('1', 'False', 'Can pinguins fly?', '1');
-- ANSWERS
INSERT INTO `make_test_test_db`.`answer` (`answer_id`, `answer_text`, `question_answers`) VALUES ('1', 'True', '1');
INSERT INTO `make_test_test_db`.`answer` (`answer_id`, `answer_text`, `question_answers`) VALUES ('2', 'False', '1');
-- RESULTS
INSERT INTO `make_test_test_db`.`result` (`result_id`, `percentage`, `result_date`, `test_result`) VALUES ('1', '100', '2016-11-09 16:10:15', '1');
-- USER ANSWERS
INSERT INTO `make_test_test_db`.`user_answer` (`user_answer_id`, `user_answer`, `user_answer_result`) VALUES ('1', '2', '1');
