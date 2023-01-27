SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE `equipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codeEquipe` text NOT NULL,
  `pays` text NOT NULL,
  `couleur` text,
  `entraineur` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `equipe` (`id`, `codeEquipe`, `pays`, `couleur`, `entraineur`) VALUES
(1,'ENG','Angleterre','Blanc-Rouge','Brian Ashton'),
(2,'AUS','Australie','Jaune-Vert','John Connolly'),
(3,'NZL','Nouvelle-Zelande','Noir','Graham Henry'),
(4,'FRA','France','Bleu','Bernard Laporte'),
(5,'RSA','Republique Sud-Africaine','Vert','Jake White'),
(6,'WAL','Pays de Galles','Rouge','Gareth Jenkins'),
(7,'SCO','Ecosse','Bleu-Blanc','Frank Hadden'),
(8,'IRL','Irlande','Vert','Eddie O''Sullivan'),
(9,'SAM','Samoa','Bleu','Michael Jones'),
(10,'FJI','Fidji','Blanc','Ilie Tabua'),
(11,'ITA','Italie','Azur','Pierre Berbizier'),
(12,'ARG','Argentine','Azur-Blanc','Marcelo Loffreda'),
(13,'USA','Etats-Unis','Rouge-Bleu','Peter Thorburn'),
(14,'CAN','Canada','Rouge-Blanc','Ric Suggitt'),
(15,'ROM','Roumanie','Jaune','Daniel Santamans'),
(16,'GEO','Georgie','Rouge-Blanc','Malkhaz Cheishvili'),
(17,'TGA','Tonga','Rouge-Blanc','Quddus Fielea'),
(18,'JPN','Japon','Rouge-Blanc','John Kirwan'),
(19,'POR','Portugal','Bordeaux-Vert','Tomaz Morais'),
(20,'NAM','Namibie','Bleu-Blanc','Hakkies Husselman');

DROP TABLE IF EXISTS `poste`;
CREATE TABLE `poste` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` text NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `poste` (`numero`, `libelle`) VALUES
(1,'Premiere ligne - Pilier gauche'),
(2,'Premiere ligne - Talonneur'),
(3,'Premiere ligne - Pilier droit'),
(4,'Deuxieme ligne'),
(5,'Deuxieme ligne'),
(6,'Troisieme ligne - Gauche'),
(7,'Troisieme ligne - Droit'),
(8,'Troisieme ligne - Centre'),
(9,'Demi de melee'),
(10,'Demi d''ouverture'),
(11,'Trois-quarts aile gauche'),
(12,'Trois-quarts centre gauche'),
(13,'Trois-quarts centre droit'),
(14,'Trois-quarts aile droit'),
(15,'Arriere');


DROP TABLE IF EXISTS `stade`;
CREATE TABLE `stade` (
  `numStade` int(11) NOT NULL AUTO_INCREMENT,
  `ville` text NOT NULL,
  `nomStade` text NOT NULL,
  `capacite` int(11),
  PRIMARY KEY (`numStade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `stade` (`numStade`, `ville`, `nomStade`, `capacite`) VALUES
(1,'Lens','Felix Bollaert',30000),
(2,'Paris','Parc des Princes',60000),
(3,'Saint-Denis','Stade de France',80000),
(4,'Bordeaux','Chaban-Delmas',40000),
(5,'Lyon','Gerland',40000),
(6,'Marseille','Velodrome',50000),
(7,'Montpellier','Mosson',35000),
(8,'Nantes','Beaujoire',35000),
(9,'Saint-Etienne','Geoffroy-Guichard',37000),
(10,'Toulouse','Stadium',35000);


DROP TABLE IF EXISTS `arbitre`;
CREATE TABLE `arbitre` (
  `numArbitre` int(11) NOT NULL AUTO_INCREMENT,
  `nomArbitre` text NOT NULL,
  `nationalite` text NOT NULL,
  PRIMARY KEY (`numArbitre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `arbitre` (`numArbitre`, `nomArbitre`, `nationalite`) VALUES
(1,'Wayne Barnes','Angleterre'),
(2,'Stuart Dickinson','Australie'),
(3,'Paul Honiss','Nouvelle-Zelande'),
(4,'Marius Jonker' ,'Republique Sud-Africaine' ),
(5,'Joel Jutge' ,'France' ),
(6,'Jonathan Kaplan' ,'Republique Sud-Africaine' ),
(7,'Alan Lewis' ,'Irelande' ),
(8,'Nigel Owens' ,'Pays de Galles' ),
(9,'Alain Rolland' ,'Irelande' ),
(10,'Tony Spreadbury' ,'Angleterre' ),
(11,'Steve Walsh' ,'Nouvelle-Zelande' ),
(12,'Chris White' ,'Angleterre' );


DROP TABLE IF EXISTS `joueur`;
CREATE TABLE `joueur` (
  `numJoueur` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` text NOT NULL,
  `nom` text NOT NULL,
  `numPoste` int(11) NOT NULL,
  `numEquipe` int(11) NOT NULL,
  PRIMARY KEY (`numJoueur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `joueur` (`numJoueur`, `prenom`, `nom`, `numPoste`, `numEquipe`) VALUES
(1,'Tony','Woodcock',1,3),
(2,'Keven','Mealamu',2,3),
(3,'Carl','Hayman',3,3),
(4,'Chris','Jack',4,3),
(5,'Ali','Williams',5,3),
(6,'Jerry','Collins',6,3),
(7,'Richie','McCaw',7,3),
(8,'Rodney','So''oialo',8,3),
(9,'Byron','Kelleher',9,3),
(10,'Dan','Carter',10,3),
(11,'Sitiveni','Sivivatu',11,3),
(12,'Luke','McAlister',12,3),
(13,'Mils','Muliaina',13,3),
(14,'Doug','Howlett',14,3),
(15,'Leon','MacDonald',15,3),
(16,'Neemia','Tialata',1,3),
(17,'Andrew','Hore',2,3),
(18,'Greg','Somerville',3,3),
(19,'Anton','Oliver',2,3),
(20,'Chris','Masoe',7,3),
(21,'Nick','Evans',10,3),
(22,'Joe','Rokocoko',11,3),
(23,'Conrad','Smith',13,3),
(24,'Andrew','Ellis',9,3),
(25,'Sione','Lauaki',5,3),
(26,'Keith','Robinson',1,3),
(27,'Reuben','Thorne',4,3),
(28,'Isaia','Toeava',14,3),
(29,'Aaron','Mauger',10,3),
(30,'Brendon','Leonard',9,3),
(31,'Olivier','Milloud',1,4),
(32,'Raphael','Ibanez',2,4),
(33,'Pieter','de Villiers',3,4),
(34,'Sebastien','Chabal',4,4),
(35,'Jerome','Thion',5,4),
(36,'Serge','Betsen',6,4),
(37,'Thierry','Dusautoir',7,4),
(38,'Julien','Bonnaire',8,4),
(39,'Jean-Baptiste','Elissalde',9,4),
(40,'Frederic','Michalak',10,4),
(41,'Cedric','Heymans',11,4),
(42,'Damien','Traille',12,4),
(43,'David','Marty',13,4),
(44,'Vincent','Clerc',14,4),
(45,'Clement','Poitrenaud',15,4),
(46,'Dimitri','Szarzewski',2,4),
(47,'Jean-Baptiste','Poux',1,4),
(48,'Lionel','Nallet',5,4),
(49,'Yannick','Nyanga',6,4),
(50,'Lionel','Beauxis',10,4),
(51,'Yannick','Jauzion',12,4),
(52,'Aurelien','Rougerie',15,4),
(53,'Sebastien','Bruno',1,4),
(54,'Christophe','Dominici',11,4),
(55,'Imanol','Harinordoquy',8,4),
(56,'Remy','Martin',7,4),
(57,'Nicolas','Mas',3,4),
(58,'Pierre','Mignoni',9,4),
(59,'Fabien','Pelous',4,4),
(60,'David','Skrela',10,4),
(61,'Matt','Dunning',1,2),
(62,'Stephen','Moore',2,2),
(63,'Al','Baxter',3,2),
(64,'Nathan','Sharpe',4,2),
(65,'Daniel','Vickerman',5,2),
(66,'Rocky','Elsom',6,2),
(67,'George','Smith',7,2),
(68,'Wycliff','Palu',8,2),
(69,'George','Gregan',9,2),
(70,'Stephen','Larkham',10,2),
(71,'Lote','Tuqiri',11,2),
(72,'Matt','Giteau',12,2),
(73,'Stirling','Mortlock',13,2),
(74,'Adam','Ashley-Cooper',14,2),
(75,'Chris','Latham',15,2),
(76,'Adam','Freier',2,2),
(77,'Guy','Shepherdson',3,2),
(78,'Hugh','McMeniman',4,2),
(79,'Stephen','Hoiles',8,2),
(80,'Berrick','Barnes',10,2),
(81,'Drew','Mitchell',14,2),
(82,'Mark','Gerrard',13,2),
(83,'Mark','Chisholm',6,2),
(84,'Sam','Cordingley',8,2),
(85,'Sean','Hardman',4,2),
(86,'Greg','Holmes',1,2),
(87,'Julian','Huxley',10,2), 
(88,'David','Lyons',3,2),
(89,'Cameron','Shepherd',12,2),
(90,'Scott','Staniforth',13,2),
(91,'Gavin','Kerr',1,7),
(92,'Ross','Ford',2,7),
(93,'Euan','Murray',3,7),
(94,'Nathan','Hines',4,7),
(95,'Jim','Hamilton',5,7), 
(96,'Jason','White',6,7),
(97,'Allister','Hogg',7,7),
(98,'Simon','Taylor',8,7),
(99,'Mike','Blair',9,7),
(100,'Dan','Parks',10,7),
(101,'Chris','Paterson',11,7),
(102,'Rob','Dewey',12,7),
(103,'Simon','Webster',13,7),
(104,'Sean','Lamont',14,7),
(105,'Rory','Lamont',15,7),
(106,'Scott','Lawson',1,7),
(107,'Craig','Smith',1,7),
(108,'Scott','MacLeod',5,7),
(109,'Kelly','Brown',6,7),
(110,'Chris','Cusiter',9,7),
(111,'Hugo','Southwell',13,7),
(112,'Nikki','Walker',10,7),
(113,'John','Barclay',7,7),
(114,'David','Callam',8,7),
(115,'Marcus','Di Rollo',13,7),
(116,'Alasdair','Dickinson',1,7),
(117,'Andrew','Henderson',12,7),
(118,'Allan','Jacobsen',1,7),
(119,'Rory','Lawson',9,7),
(120,'Euan','Murray',5,7),
(121,'Fergus','Thomson',2,7);




DROP TABLE IF EXISTS `matchs`;
CREATE TABLE `matchs` (
  `numMatch` int(11) NOT NULL AUTO_INCREMENT,
  `dateMatch` date NOT NULL,
  `nbSpect` int(11),
  `numStade` int(11),
  `numEquipeR` int(11),
  `scoreR` int(11),
  `nbEssaisR` int(11),
  `numEquipeD` int(11),
  `scoreD` int(11),
  `nbEssaisD` int(11),
  PRIMARY KEY (`numMatch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `matchs` (`numMatch`, `dateMatch`, `nbSpect`, `numStade`, `numEquipeR`, `scoreR`, `nbEssaisR`,`numEquipeD`,`scoreD`, `nbEssaisD`) VALUES
(1,str_to_date('07/09/2007','%d/%m/%Y'),30000,3,4,12,0,12,17,1),
(2,str_to_date('09/09/2007','%d/%m/%Y'),30000,4,8,32,5,20,17,2),
(3,str_to_date('11/09/2007','%d/%m/%Y'),30000,5,12,33,4,16,3,0),
(4,str_to_date('15/09/2007','%d/%m/%Y'),30000,4,8,14,2,16,10,1),
(5,str_to_date('16/09/2007','%d/%m/%Y'),30000,10,4,87,13,20,10,1),
(6,str_to_date('21/09/2007','%d/%m/%Y'),30000,3,4,25,2,8,3,0),
(7,str_to_date('22/09/2007','%d/%m/%Y'),30000,6,12,63,9,20,3,0),
(8,str_to_date('26/09/2007','%d/%m/%Y'),30000,1,16,30,3,20,0,0),
(9,str_to_date('08/09/2007','%d/%m/%Y'),30000,6,3,76,3,11,14,0),
(10,str_to_date('09/09/2007','%d/%m/%Y'),30000,9,7,56,3,19,10,0),
(11,str_to_date('12/09/2007','%d/%m/%Y'),30000,6,11,24,3,15,18,0),
(12,str_to_date('15/09/2007','%d/%m/%Y'),30000,5,3,108,16,19,13,0),
(13,str_to_date('18/09/2007','%d/%m/%Y'),30000,2,7,42,3,15,0,0),
(14,str_to_date('19/09/2007','%d/%m/%Y'),30000,2,11,31,3,19,5,0),
(15,str_to_date('23/09/2007','%d/%m/%Y'),30000,4,7,0,3,3,40,0),
(16,str_to_date('25/09/2007','%d/%m/%Y'),30000,10,15,14,3,19,10,0),
(17,str_to_date('08/09/2007','%d/%m/%Y'),30000,5,2,91,13,18,3,0),
(18,str_to_date('09/09/2007','%d/%m/%Y'),30000,8,6,42,5,14,17,3),
(19,str_to_date('12/09/2007','%d/%m/%Y'),30000,10,18,31,3,10,35,4),
(20,str_to_date('15/09/2007','%d/%m/%Y'),30000,7,6,20,2,2,32,4),
(21,str_to_date('16/09/2007','%d/%m/%Y'),30000,7,10,29,4,14,16,1),
(22,str_to_date('20/09/2007','%d/%m/%Y'),30000,7,6,72,11,18,18,2),
(23,str_to_date('23/09/2007','%d/%m/%Y'),30000,2,2,55,7,18,12,2),
(24,str_to_date('25/09/2007','%d/%m/%Y'),30000,4,14,12,2,18,12,2),
(25,str_to_date('08/09/2007','%d/%m/%Y'),30000,1,1,28,3,13,10,1),
(26,str_to_date('09/09/2007','%d/%m/%Y'),30000,2,5,59,8,9,7,1),
(27,str_to_date('12/09/2007','%d/%m/%Y'),30000,7,13,15,2,17,25,3),
(28,str_to_date('14/09/2007','%d/%m/%Y'),30000,3,1,0,0,5,36,3),
(29,str_to_date('16/09/2007','%d/%m/%Y'),30000,7,9,15,0,17,19,1),
(30,str_to_date('22/09/2007','%d/%m/%Y'),30000,1,5,30,4,17,25,3),
(31,str_to_date('22/09/2007','%d/%m/%Y'),30000,8,1,44,4,9,22,1),
(32,str_to_date('26/09/2007','%d/%m/%Y'),30000,9,9,25,3,13,21,2);




DROP TABLE IF EXISTS `arbitrer`;
CREATE TABLE `arbitrer` (
  `numMatch` int(11) NOT NULL,
  `numArbitre` int(11) NOT NULL,
  PRIMARY KEY (`numMatch`, `numArbitre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `arbitrer` (`numMatch`, `numArbitre`) VALUES
(1,10),
(2,5),
(3,8),
(4,1),
(5,9),
(6,12),
(7,2),
(8,11),
(9,1),
(10,11),
(11,10),
(12,12),
(13,8),
(14,4),
(15,4),
(16,3),
(17,7),
(18,9),
(19,4),
(20,11),
(21,10),
(22,5),
(23,8),
(24,6),
(25,6),
(26,3),
(27,2),
(28,5),
(29,6),
(30,1),
(31,7),
(32,1);


DROP TABLE IF EXISTS `jouer`;
CREATE TABLE `jouer` (
  `numMatch` int(11) NOT NULL,
  `numJoueur` int(11) NOT NULL,
  `titulaire` boolean,
  `tpsJeu` int(11),
  `nbPoints` int(11),
  `nbEssais` int(11),
  PRIMARY KEY (`numMatch`, `numJoueur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `jouer` (`numMatch`, `numJoueur`, `titulaire`, `tpsJeu`, `nbPoints`, `nbEssais`) VALUES
(15,1,true,80,0,0),
(15,19,true,59,0,0),
(15,3,true,65,0,0),
(15,27,true,80,0,0),
(15,5,true,80,5,1),
(15,20,true,80,0,0),
(15,7,true,61,5,1),
(15,8,true,80,0,0),
(15,9,true,59,5,1),
(15,10,true,80,15,1),
(15,11,true,80,0,0),
(15,12,true,80,0,0),
(15,23,true,65,0,0),
(15,14,true,80,10,2),
(15,15,true,21,0,0),
(15,17,false,21,0,0),
(15,16,false,15,0,0),
(15,4,false,14,0,0),
(15,25,false,19,0,0),
(15,30,false,21,0,0),
(15,21,false,59,0,0),
(15,28,false,15,0,0),
(15,91,true,80,0,0),
(15,109,true,59,0,0),
(15,92,true,65,0,0),
(15,117,true,80,0,0),
(15,95,true,80,5,1),
(15,110,true,80,0,0),
(15,97,true,61,5,1),
(15,98,true,80,0,0),
(15,99,true,59,5,1),
(15,100,true,80,15,1),
(15,101,true,80,0,0),
(15,102,true,80,0,0),
(15,113,true,65,0,0),
(15,104,true,80,10,2),
(15,105,true,21,0,0),
(15,107,false,21,0,0),
(15,106,false,15,0,0),
(15,93,false,14,0,0),
(15,115,false,19,0,0),
(15,120,false,21,0,0),
(15,111,false,59,0,0),
(15,118,false,15,0,0),
(12,16,true,80,0,0),
(12,17,true,57,5,1),
(12,18,true,61,0,0),
(12,4,true,51,0,0),
(12,5,true,54,5,1),
(12,6,true,64,5,1),
(12,20,true,80,5,1),
(12,25,true,80,0,0),
(12,30,true,54,5,1),
(12,21,true,80,33,1),
(12,22,true,80,10,2),
(12,29,true,80,10,2),
(12,23,true,80,10,2),
(12,28,true,80,5,1),
(12,13,true,7,0,0),
(12,19,false,23,0,0),
(12,1,false,19,0,0),
(12,3,false,29,5,1),
(12,8,false,26,0,0),
(12,2,false,16,0,0),
(12,24,false,26,5,1),
(12,15,false,73,5,1),
(9,1,true,55,0,0),
(9,2,true,52,0,0),
(9,3,true,80,0,0),
(9,4,true,80,5,1),
(9,5,true,71,0,0),
(9,6,true,80,10,2),
(9,7,true,60,10,2),
(9,8,true,80,0,0),
(9,9,true,52,0,0),
(9,10,true,60,17,0),
(9,11,true,80,10,2),
(9,12,true,80,4,0),
(9,13,true,53,5,1),
(9,14,true,80,15,3),
(9,15,true,80,0,0),
(9,19,false,28,0,0),
(9,16,false,25,0,0),
(9,20,false,20,0,0),
(9,25,false,9,0,0),
(9,30,false,28,0,0),
(9,29,false,20,0,0),
(9,28,false,27,0,0),
(1,31,true,80,0,0),
(1,32,true,60,0,0),
(1,33,true,80,0,0),
(1,59,true,60,0,0),
(1,35,true,80,0,0),
(1,36,true,80,0,0),
(1,56,true,60,0,0),
(1,55,true,80,0,0),
(1,58,true,73,0,0),
(1,60,true,62,12,0),
(1,54,true,80,0,0),
(1,42,true,80,0,0),
(1,51,true,80,0,0),
(1,52,true,80,0,0),
(1,41,true,80,0,0),
(1,46,false,20,0,0),
(1,34,false,20,0,0),
(1,38,false,20,0,0),
(1,39,false,7,0,0),
(1,40,false,18,0,0),
(5,47,true,80,0,0),
(5,46,true,58,0,0),
(5,33,true,41,0,0),
(5,34,true,58,10,2),
(5,48,true,80,10,2),
(5,49,true,54,0,0),
(5,37,true,80,5,1),
(5,38,true,80,5,1),
(5,39,true,80,27,1),
(5,40,true,63,0,0),
(5,41,true,80,5,1),
(5,42,true,51,0,0),
(5,43,true,80,5,1),
(5,44,true,80,15,3),
(5,45,true,65,0,0),
(5,32,false,22,5,1),
(5,57,false,39,0,0),
(5,59,false,22,0,0),
(5,55,false,26,0,0),
(5,50,false,17,0,0),
(5,51,false,29,0,0),
(5,52,false,15,0,0),
(6,31,true,74,0,0),
(6,32,true,57,0,0),
(6,33,true,80,0,0),
(6,34,true,46,0,0),
(6,35,true,80,0,0),
(6,36,true,62,0,0),
(6,37,true,80,0,0),
(6,38,true,80,0,0),
(6,39,true,74,15,0),
(6,40,true,80,0,0),
(6,41,true,80,0,0),
(6,42,true,76,0,0),
(6,43,true,74,0,0),
(6,44,true,80,10,2),
(6,45,true,72,0,0),
(6,46,false,23,0,0),
(6,47,false,6,0,0),
(6,48,false,34,0,0),
(6,49,false,18,0,0),
(6,50,false,6,0,0),
(6,51,false,6,0,0),
(6,52,false,8,0,0);



