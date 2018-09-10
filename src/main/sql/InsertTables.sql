insert into Programme values( 'LEC', 'Licenciatura Engenharia Civil', 6),
							( 'LEIC', 'Licenciatura Engenharia Inform�tica Computadores', 6),
							( 'LEQ', 'Licenciatura Engenharia Qu�mica', 6)

insert into Student values( 1, 'Jo�o Antunes', 'ja@isel.pt', 'LEIC'),
							( 2, 'Bernardo Fernandes', 'bf@isel.pt', 'LEIC'),
							( 3, 'Maria Joana', 'mj@isel.pt', 'LEIC'),
							( 4, 'Tiago Pinto', 'tp@isel.pt', 'LEIC'),
							( 5, 'Lu�s Ant�nio', 'la@isel.pt', 'LEIC'),
							( 6, 'Beatriz Nunes', 'bn@isel.pt', 'LEQ'),
							( 7, 'M�rio Lemos', 'ml@isel.pt', 'LEC'),
							( 8, 'Nuno Martins', 'nm@isel.pt', 'LEC'),
							( 9, 'Rosa Antunes', 'ra@isel.pt', 'LEQ'),
							( 10, 'Maria Teresa', 'mt@isel.pt', 'LEQ')


insert into Teacher values( 100, 'Ant�nio Campos', 'ac@isel.pt'),
							( 101, 'Andr� Pinto', 'ap@isel.pt'),
							( 102, 'Guilherme Bruno', 'gb@isel.pt'),
							( 103, 'Jo�o Fernandes', 'jf@isel.pt'),
							( 104, 'Lu�s Manuel', 'lm@isel.pt'),
							( 105, 'Ana Teresa', 'at@isel.pt')


insert into Course values ('Laborat�rio Software', 'LS', 100),
							('Modela��o Padr�es de Desenho', 'MPD', 101),
							('Programa��o', 'PG', 103)

insert into AcademicSemester values('1617', 'summer'),
									('1617', 'winter')

insert into CurricularSemester values (1),(2),(3)

insert into Class values('D1', 'LS', '1617', 'summer'),
						('D1', 'LS', '1617', 'winter'),
						('D1', 'MPD', '1617', 'summer')

insert into Teach values ('D1' ,'LS', '1617', 'summer', 100),
						('D1' ,'LS', '1617', 'winter', 100),
						('D1' ,'MPD', '1617', 'summer', 102)

insert into Attends values('D1', 'LS', '1617', 'summer', 1),
							('D1', 'LS', '1617', 'summer', 2),
							('D1', 'LS', '1617', 'summer', 3),
							('D1', 'LS', '1617', 'summer', 4),
							('D1', 'LS', '1617', 'summer', 5),
							('D1', 'MPD', '1617', 'summer', 1),
							('D1', 'MPD', '1617', 'summer', 2),
							('D1', 'MPD', '1617', 'summer', 3)

