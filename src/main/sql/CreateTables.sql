CREATE TABLE Programme(
	acronymProgramme VARCHAR(25) NOT NULL,
	name VARCHAR(50) NOT NULL,
	numberSemester INTEGER NOT NULL,
	PRIMARY KEY(acronymProgramme)
	)

CREATE TABLE Student(
	studentID INTEGER NOT NULL,
	nameStudent VARCHAR(100) NOT NULL,
	emailStudent VARCHAR(100) UNIQUE NOT NULL,
	acronymProgramme VARCHAR(25) NOT NULL,
	PRIMARY KEY(studentID),
	FOREIGN KEY(acronymProgramme) REFERENCES Programme(acronymProgramme),
	CHECK(studentID > 0)
	)


CREATE TABLE Teacher(
	teacherID INTEGER NOT NULL,
	nameTeacher VARCHAR(100) NOT NULL,
	emailTeacher VARCHAR(100) UNIQUE NOT NULL,
	PRIMARY KEY(teacherID),
	CHECK(teacherID > 0)
	)

CREATE TABLE Course(
	nameCourse VARCHAR(100) NOT NULL,
	acronym VARCHAR(25) NOT NULL,
	teacherID INTEGER NOT NULL,
	PRIMARY KEY(acronym),
	FOREIGN KEY(teacherID) REFERENCES Teacher(teacherID)
	)
	
CREATE TABLE AcademicSemester(
	yearSemester VARCHAR(25) NOT NULL,
	semester VARCHAR(50) NOT NULL,
	PRIMARY KEY(yearSemester, semester),
	CHECK(semester = 'winter' OR semester = 'summer')
	)


CREATE TABLE Class(
	identifier VARCHAR(25) NOT NULL,
	acronym VARCHAR(25) NOT NULL,
	yearSemester VARCHAR(25) NOT NULL,
	semester VARCHAR(50) NOT NULL,
	PRIMARY KEY(identifier, acronym, yearSemester, semester),
	FOREIGN KEY(acronym) REFERENCES Course(acronym),
	FOREIGN KEY(yearSemester, semester) REFERENCES AcademicSemester(yearSemester, semester),
	CHECK(semester = 'winter' OR semester = 'summer')
	)


CREATE TABLE CurricularSemester(
	id INTEGER NOT NULL,
	PRIMARY KEY(id)
	)

CREATE TABLE Teach(
	identifier VARCHAR(25) NOT NULL,
	acronym VARCHAR(25) NOT NULL,
	yearSemester VARCHAR(25) NOT NULL,
	semester VARCHAR(50) NOT NULL,
	teacherID INTEGER NOT NULL,
	PRIMARY KEY(identifier, acronym, yearSemester, semester, teacherID),
	FOREIGN KEY(identifier, acronym, yearSemester, semester) REFERENCES Class(identifier, acronym, yearSemester, semester),
	CHECK(semester = 'winter' OR semester = 'summer')
	)

CREATE TABLE Obrigation(
	acronymProgramme VARCHAR(25) NOT NULL,
	acronym VARCHAR(25) NOT NULL,
	mandatory BIT NOT NULL,
	id INTEGER NOT NULL,
	PRIMARY KEY(acronymProgramme, acronym, mandatory, id),
	FOREIGN KEY(acronymProgramme) REFERENCES Programme(acronymProgramme),
	FOREIGN KEY(acronym) REFERENCES Course(acronym),
	FOREIGN KEY(id) REFERENCES CurricularSemester(id)
	)

CREATE TABLE Attends(
	identifier VARCHAR(25) NOT NULL,
	acronym VARCHAR(25) NOT NULL,
	yearSemester VARCHAR(25) NOT NULL,
	semester VARCHAR(50) NOT NULL,
	studentID INTEGER NOT NULL,
	PRIMARY KEY(identifier, acronym, yearSemester, semester, studentID),
	FOREIGN KEY(identifier, acronym, yearSemester, semester) REFERENCES Class(identifier, acronym, yearSemester, semester),
	FOREIGN KEY(studentID) REFERENCES Student(studentID),
	CHECK(semester = 'winter' OR semester = 'summer')
	)
