CREATE TABLE games (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  rating FLOAT(3,1) NOT NULL,
  release_year INT NOT NULL,
  developer VARCHAR(255) NOT NULL,
  platform VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);


INSERT INTO games (title, rating, release_year, developer, platform)
VALUES
('The Legend of Zelda: Breath of the Wild', 9.5, 2017, 'Nintendo EPD', 'Nintendo Switch'),
('Red Dead Redemption 2', 9.5, 2018, 'Rockstar Games', 'PlayStation 4, Xbox One'),
('Half-Life 2', 9.3, 2004, 'Valve Corporation', 'Microsoft Windows'),
('The Last of Us Part II', 9.3, 2020, 'Naughty Dog', 'PlayStation 4, PlayStation 5'),
('God of War', 9.2, 2018, 'Santa Monica Studio', 'PlayStation 4'),
('Portal 2', 9.2, 2011, 'Valve Corporation', 'Microsoft Windows'),
('Grand Theft Auto V', 9.0, 2013, 'Rockstar North', 'PlayStation 3, PlayStation 4, Xbox 360, Xbox One, Microsoft Windows'),
('Super Mario Galaxy', 9.0, 2007, 'Nintendo EAD', 'Wii'),
('BioShock', 9.0, 2007, '2K Boston', 'Microsoft Windows, Xbox 360, PlayStation 3'),
('Minecraft', 9.0, 2011, 'Mojang Studios', 'Microsoft Windows, macOS, Linux, Android, iOS, PlayStation 3, PlayStation 4, PlayStation Vita, Xbox 360, Xbox One, Nintendo Switch');
