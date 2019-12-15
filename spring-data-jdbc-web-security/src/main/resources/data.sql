INSERT INTO users (username, password, enabled)
VALUES
  (
    'admin1',
    '{bcrypt}$2a$10$DsbKD92SrYqAudoIVyUK8.HvBp7RK6soNOx35DgmIacaeynCzt5iS',
    true
  ),
  (
    'user1',
    '{bcrypt}$2a$10$DsbKD92SrYqAudoIVyUK8.HvBp7RK6soNOx35DgmIacaeynCzt5iS',
    true
  ),
  (
    'user2',
    '{bcrypt}$2a$10$DsbKD92SrYqAudoIVyUK8.HvBp7RK6soNOx35DgmIacaeynCzt5iS',
    true
  );
INSERT INTO authorities (username, authority)
VALUES
  ('admin1', 'ROLE_ADMIN'),
  ('user1', 'ROLE_USER'),
  ('user2', 'ROLE_USER');
